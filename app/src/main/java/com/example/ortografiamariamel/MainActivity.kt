package com.example.ortografiamariamel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.ortografiamariamel.service.SoundManager
import com.example.ortografiamariamel.ui.theme.OrtografiaMariamelTheme

class MainActivity : ComponentActivity() {
    private lateinit var soundManager: SoundManager
//    private var isMusicPlaying by mutableStateOf(true)
//    private var volume by mutableFloatStateOf(1f) // Volumen inicial maximo
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
