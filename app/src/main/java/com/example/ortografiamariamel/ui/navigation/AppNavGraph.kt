package com.example.ortografiamariamel.ui.navigation

import android.annotation.SuppressLint
import android.media.browse.MediaBrowser.MediaItem
import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ortografiamariamel.AppScreen
import com.example.ortografiamariamel.R
import com.example.ortografiamariamel.service.AudioController
import com.example.ortografiamariamel.service.GlobalAudioPlayer
import com.example.ortografiamariamel.service.SoundManager
import com.example.ortografiamariamel.ui.AppViewModel
import com.example.ortografiamariamel.ui.AppViewModelProvider
import com.example.ortografiamariamel.ui.theme.OrtografiaMariamelTheme
import com.example.ortografiamariamel.ui.views.DatosJugadorScreen
import com.example.ortografiamariamel.ui.views.InicioScreen
import com.example.ortografiamariamel.ui.views.MenuScreen
import com.example.ortografiamariamel.ui.views.unidad1.Actividad1
import com.example.ortografiamariamel.ui.views.unidad1.UnidadI
import com.example.ortografiamariamel.ui.views.unidad2.UnidadII
import com.example.ortografiamariamel.ui.views.unidad3.UnidadIII
import com.example.ortografiamariamel.ui.views.unidad4.UnidadIV
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val viewModel: AppViewModel = viewModel(factory = AppViewModelProvider.Factory)
    val uiState by viewModel.uiState.collectAsState()

//    val audioController = AudioController(context= LocalContext.current)
//    audioController.startPlaying(R.raw.merengue)
//    GlobalAudioPlayer(audioController = audioController)
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val soundManager = remember {SoundManager(context)}

    // Observar el ciclo de vida para liberar recursos apropiadamente
    lifecycleOwner.lifecycleScope.launch {
        lifecycleOwner.lifecycle.addObserver(object : LifecycleObserver{
            @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
            fun onDestroy(){
                soundManager.release()
            }
        })
    }



    NavHost(
        navController = navController,
        startDestination = AppScreen.Inicio.name,
        modifier = modifier
    ) {
        // pantalla de inicio
        composable(route = AppScreen.Inicio.name) {
            InicioScreen(
                onNextButtonClicked = {
                    soundManager.playSound(R.raw.merengue)
                    navController.navigate(AppScreen.DatosJugador.name) },
                modifier = Modifier
                    .fillMaxWidth()

            )
        }
        // 2da pantalla
        composable(route = AppScreen.DatosJugador.name) {
            DatosJugadorScreen(
                onNextButtonClicked = {
                    navController.navigate(AppScreen.Menu.name)
                },
                modifier = Modifier
            )
        }
        // 3ra pantalla
        composable(route = AppScreen.Menu.name) {
            MenuScreen(
                onPrevButtonClicked = {
                    navController.navigateUp()
                },
                onNextButtonClicked = {
                    navController.navigate(uiState.menu.name)
                },
                onItemMenuButtonClicked = {
                    Log.d("AppNavGraph","composable Menu: ${uiState.menu.name}")
                    navController.navigate(uiState.menu.name)
                },
                modifier = Modifier
            )
        }
        // 4ta pantalla - UNIDAD I
        composable(route = AppScreen.Unidad1.name) {
            UnidadI(
                onPrevButtonClicked = {
                    navController.navigateUp()
                },
                onNextButtonClicked = {
                    navController.navigate(AppScreen.Actividad1.name)
                },
                onItemMenuButtonClicked = {
                    Log.d("AppNavGraph","composable UnidadI: ${uiState.menu.name}")
                    navController.navigate(uiState.menu.name)
                    Log.d("AppNavGraph","composable UnidadI after: ${uiState.menu.name}")
                },
                modifier = Modifier
            )
        }
        // 5ta pantalla - ACTIVIDAD UNIDAD I
        composable(route = AppScreen.Actividad1.name) {
            Actividad1(
                onPrevButtonClicked = {
                    navController.navigateUp()
                },
                onNextButtonClicked = {
                    navController.navigate(AppScreen.Menu.name)
                },
                onItemMenuButtonClicked = {
                    Log.d("AppNavGraph","composable ActividadI: ${uiState.menu.name}")
                    navController.navigate(uiState.menu.name)
                },
                modifier = Modifier
            )
        }
        // 6ta pantalla - UNIDAD II
        composable(route = AppScreen.Unidad2.name) {
            UnidadII(
                onPrevButtonClicked = {
                    navController.navigateUp()
                },
                onNextButtonClicked = {
                    navController.navigateUp()
                },
                onItemMenuButtonClicked = {
                    Log.d("AppNavGraph","composable UnidadII: ${uiState.menu.name}")
                    navController.navigate(uiState.menu.name)
                },
                modifier = Modifier
            )
        }
        // 7ma pantalla - UNIDAD III
        composable(route = AppScreen.Unidad3.name) {
            UnidadIII(
                onPrevButtonClicked = {
                    navController.navigateUp()
                },
                onNextButtonClicked = {
                    navController.navigateUp()
                },
                onItemMenuButtonClicked = {
                    Log.d("AppNavGraph","composable UnidadIII: ${uiState.menu.name}")
                    navController.navigate(uiState.menu.name)
                },
                modifier = Modifier
            )
        }
        // 8va pantalla - UNIDAD IV
        composable(route = AppScreen.Unidad4.name) {
            UnidadIV(
                onPrevButtonClicked = {
                    navController.navigateUp()
                },
                onNextButtonClicked = {
                    navController.navigateUp()
                },
                onItemMenuButtonClicked = {
                    Log.d("AppNavGraph","composable UnidadIV: ${uiState.menu.name}")
                    navController.navigate(uiState.menu.name)
                },
                modifier = Modifier
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MenuScreenPreview() {
    OrtografiaMariamelTheme {
        AppNavHost(navController = rememberNavController())
    }
}
