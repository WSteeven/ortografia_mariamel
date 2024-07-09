package com.example.ortografiamariamel.ui

import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory

object  AppViewModelProvider{
    val Factory = viewModelFactory {
        initializer {
            AppViewModel()
        }
        // mas initializers segun sea necesario
    }
}