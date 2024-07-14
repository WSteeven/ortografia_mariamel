package com.example.ortografiamariamel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.ortografiamariamel.service.SoundManager
import com.example.ortografiamariamel.ui.theme.OrtografiaMariamelTheme

class MainActivity : ComponentActivity() {
    private lateinit var soundManager: SoundManager
    private var isMusicPlaying by mutableStateOf(true)
    private var volume by mutableFloatStateOf(1f) // Volumen inicial maximo
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OrtografiaMariamelTheme {
                Surface(modifier = Modifier.fillMaxSize()) {

                    /**
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        // Botón para controlar la música
                        Button(onClick = {
                            if (isMusicPlaying) {
                                soundManager.release() // Detener música
                                isMusicPlaying = false
                            } else {
                                soundManager.playSound(R.raw.merengue) // Reiniciar música
                                isMusicPlaying = true
                            }
                        }) {
                            Text(if (isMusicPlaying) "Detener Música" else "Reanudar Música")
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        // Control deslizante para ajustar el volumen
                        Text("Ajustar Volumen")
                        Slider(
                            value = volume,
                            onValueChange = { newVolume ->
                                volume = newVolume
                                soundManager.setVolume(volume) // Ajustar volumen
                            },
                            valueRange = 0f..1f,
                            steps = 10
                        )
                    }
*/

                    App()
                }
            }
        }
        // Initializing the sound manager
        soundManager = SoundManager(this)
    }

//    override fun onStart() {
//        super.onStart()
//        soundManager.playSound(R.raw.merengue, true)
//    }

//    override fun onStop() {
//        super.onStop()
//        soundManager.release()
//    }

//    override fun onDestroy() {
//        super.onDestroy()
//        soundManager.release()
//    }
}


