package com.example.ortografiamariamel.service

import android.content.Context
import android.media.MediaPlayer

class SoundManager(private val context: Context) {
    private var mediaPlayer: MediaPlayer? = null


    fun playSound(soundResId: Int){
        mediaPlayer?.release()
        mediaPlayer = MediaPlayer.create(context, soundResId)
        mediaPlayer?.start()
    }

    fun release(){
        mediaPlayer?.release()
        mediaPlayer = null
    }

}