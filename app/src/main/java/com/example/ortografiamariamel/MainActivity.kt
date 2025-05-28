package com.example.ortografiamariamel

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker.PERMISSION_GRANTED
import com.example.ortografiamariamel.service.SoundManager
import com.example.ortografiamariamel.ui.theme.OrtografiaMariamelTheme
import com.pusher.client.Pusher
import com.pusher.client.PusherOptions

class MainActivity : ComponentActivity() {

    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()){isGranted->
        if(isGranted){
            Log.d("FCM", "Permiso de notificaciones concedido ")
        }else Log.d("FCM", "Permiso de notificaciones denegado ")
    }
    private lateinit var soundManager: SoundManager
//    private var isMusicPlaying by mutableStateOf(true)
//    private var volume by mutableFloatStateOf(1f) // Volumen inicial maximo
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // escuchar pusher
        listenToPusher()

        //permiso de notificaciones
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PERMISSION_GRANTED
        ) {
            requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }
    }

        enableEdgeToEdge()
        setContent {
            OrtografiaMariamelTheme {
                Surface(modifier = Modifier.fillMaxSize()) {

                    OrtografiaMariamelApp()
                }
            }
        }
        // Initializing the sound manager
        soundManager = SoundManager(this)
    }
    private fun listenToPusher() {
        val options = PusherOptions().setCluster("sa1") // ej. "us2"
        val pusher = Pusher("d89058df49cac8d282ea", options)

        val channel = pusher.subscribe("tickets-tracker-24")
        channel.bind("ticket-event") { event ->
            Log.d("PUSHER", "Evento recibido: $event")
        }

        pusher.connect()
    }

    override fun onStop() {
        super.onStop()
        soundManager.release()
    }

    override fun onDestroy() {
        super.onDestroy()
        soundManager.release()
    }

    fun playMusicBasedOnScreen(route:String){
        when(route){
            AppScreen.Inicio.name,
                AppScreen.DatosJugador.name,
                AppScreen.Menu.name -> soundManager.playSound(R.raw.musica_fondo_app, true)
            else -> soundManager.stopSound()
        }
    }
}
