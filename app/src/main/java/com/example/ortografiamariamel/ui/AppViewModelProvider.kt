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
        }
        // mas initializers segun sea necesario
    }
}

fun CreationExtras.ortografiaMariamelApplication(): OrtografiaMariamelApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as OrtografiaMariamelApplication)