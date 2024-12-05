package com.example.ortografiamariamel.service

import android.content.Context
import android.health.connect.datatypes.units.Volume
import android.media.MediaPlayer

class SoundManager(private val context: Context) {
    private var mediaPlayer: MediaPlayer? = null


    fun playSound(soundResId: Int, repeat: Boolean = false) {
        mediaPlayer?.release()
        mediaPlayer = MediaPlayer.create(context, soundResId).apply {
            isLooping = repeat //Si repeat is true, the sound is repeated ever
            start()
        }
    }

    fun setVolume(volume: Float){
        mediaPlayer?.setVolume(volume, volume)
    }
    // metodo para detener la musica
    fun stopSound(){
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer=null
    }

    // metodo para liberar recursos
    fun release() {
        mediaPlayer?.release()
        mediaPlayer = null
    }

}