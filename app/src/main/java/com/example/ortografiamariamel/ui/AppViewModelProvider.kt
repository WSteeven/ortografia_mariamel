package com.example.ortografiamariamel.ui

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.ortografiamariamel.OrtografiaMariamelApplication

object AppViewModelProvider {
    val Factory = viewModelFactory {

        initializer {
            AppViewModel()
//            AppViewModel(ortografiaMariamelApplication().container.jugadorRepository)
        }
        // mas initializers segun sea necesario
        initializer {
            JugadorViewModel(ortografiaMariamelApplication().container.jugadorRepository)
        }
    }
}

fun CreationExtras.ortografiaMariamelApplication(): OrtografiaMariamelApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as OrtografiaMariamelApplication)