package com.example.ortografiamariamel.service

import android.content.Context
import androidx.annotation.OptIn
import androidx.compose.runtime.Composable
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.MediaSource
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import androidx.media3.extractor.DefaultExtractorsFactory

// Controlador de audio global
class AudioController(private val context: Context) {

    private val exoPlayer = ExoPlayer.Builder(context).build()

    // Métodos para controlar la reproducción
    @OptIn(UnstableApi::class)
    fun startPlaying(audioUrl: String) {
        // ... prepare and play audio
        val mediaSource = buildMediaSource(audioUrl)
        
        exoPlayer.prepare()
        
        // Reproducir el audio una vez preparado
        exoPlayer.playWhenReady = true

    }

    fun pausePlaying() {
        exoPlayer.pause()
    }

    fun resumePlaying() {
        exoPlayer.play()
    }

    fun stopPlaying() {
        exoPlayer.stop()
    }

    // ... otros métodos de control
    @OptIn(UnstableApi::class)
    private fun buildMediaSource(audioUrl: String): MediaSource{
        val dataSourceFactory = DefaultHttpDataSource.Factory()
        val extractorsFactory = DefaultExtractorsFactory()

        return ProgressiveMediaSource.Factory(dataSourceFactory, extractorsFactory)
            .createMediaSource(MediaItem.fromUri(audioUrl))

//            .createMediaSource(MediaSource.MediaSourceCaller(audioUrl))
    }
}

// Composable que reproduce audio global
@Composable
fun GlobalAudioPlayer(audioController: AudioController) {
    // ... Controles de usuario y lógica de reproducción
    // ... Invoca métodos del controlador de audio
}
