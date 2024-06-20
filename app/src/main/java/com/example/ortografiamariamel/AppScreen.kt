package com.example.ortografiamariamel


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.ortografiamariamel.ui.AppViewModel
import com.example.ortografiamariamel.ui.views.Actividad1
import com.example.ortografiamariamel.ui.views.DatosJugadorScreen
import com.example.ortografiamariamel.ui.views.InicioScreen
import com.example.ortografiamariamel.ui.views.MenuScreen
import com.example.ortografiamariamel.ui.views.UnidadI

// Screens
//enum class AppScreen(@StringRes val title: Int) {
//    Inicio(title = R.string.caratula),
//    DatosJugador(title = R.string.datos_usuario),
//    Menu(title = R.string.menu),
//    Unidad1(title = R.string.unidad1),
//    Unidad2(title = R.string.unidad2),
//    Unidad3(title = R.string.unidad3),
//    Unidad4(title = R.string.unidad4),
//}
enum class AppScreen {
    Inicio,
    DatosJugador,
    Menu,
    Unidad1,
    Actividad1,
    Evaluacion1,
    Unidad2,
    Unidad3,
    Unidad4,
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    puedeNavegarAtras: Boolean,
    modifier: Modifier = Modifier,
    mostrarEncabezado: Boolean = true,
    navigateUp: () -> Unit,
) {
    TopAppBar(
        title = {
            if (mostrarEncabezado) {
                Row(
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(end = 8.dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.logo_colegio),
                        contentDescription = "Logo colegio",
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .size(48.dp)
                    )
                    Column {
                        Text(
                            text = "ORTOGRAFIA",
                            fontSize = 14.sp,
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.SansSerif
                            )
                        )
                        Text(
                            buildAnnotatedString {
                                withStyle(style = SpanStyle(color = Color.White)) {
                                    append("MARIA")
                                }

                                withStyle(
                                    style = SpanStyle(
                                        fontWeight = FontWeight.Bold,
                                        color = Color.Red
                                    )
                                ) {
                                    append("MEL")
                                }
                            },
                            fontSize = 20.sp,
                            style = TextStyle(
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.SansSerif
                            )
                        )

                    }
                }
            } else Text("")
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
//            containerColor = MaterialTheme.colorScheme.primaryContainer
            containerColor = Color(255, 168, 0)
        ),
        modifier = modifier,
        navigationIcon = {
            if (puedeNavegarAtras) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Atrás"
                    )
                }
            }
        }
    )
}


@Composable
fun App(
    viewModel: AppViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    // variables
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen =
        AppScreen.valueOf(backStackEntry?.destination?.route ?: AppScreen.Inicio.name)



    Scaffold(
        topBar = {
            AppTopBar(
//                currentScreen = currentScreen,
                mostrarEncabezado = currentScreen.toString() != AppScreen.Inicio.name,
                puedeNavegarAtras = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() })
        },
        modifier = Modifier.fillMaxSize(),
    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()

        // navegacion
        NavHost(
            navController = navController,
            startDestination = AppScreen.Inicio.name,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
        ) {
            // pantalla de inicio
            composable(route = AppScreen.Inicio.name) {
                InicioScreen(
                    onNextButtonClicked = { navController.navigate(AppScreen.DatosJugador.name) },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(dimensionResource(R.dimen.padding_medium))
                )
            }
            // 2da pantalla
            composable(route = AppScreen.DatosJugador.name) {
                DatosJugadorScreen(
                    viewModel,
                    onNextButtonClicked = {
                        navController.navigate(AppScreen.Menu.name)
                    },
                    modifier = Modifier
                )
            }
            // 3ra pantalla
            composable(route = AppScreen.Menu.name) {
                MenuScreen(
                    viewModel = viewModel,
                    onPrevButtonClicked = {
                        navController.navigateUp()
                    },
                    onNextButtonClicked = {
                        navController.navigate(AppScreen.Unidad1.name)
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
                    modifier = Modifier
                )
            }
            // 5ta pantalla - ACTIVIDAD UNIDAD I
            composable(route = AppScreen.Actividad1.name) {
                Actividad1(
                    viewModel = viewModel,
                    onPrevButtonClicked = {
                        navController.navigateUp()
                    },
                    onNextButtonClicked = {
                        navController.navigateUp()
                    },
                    modifier = Modifier
                )
            }
            // 6ta pantalla - UNIDAD II
            composable(route = AppScreen.Unidad2.name) {
                UnidadI(
                    onPrevButtonClicked = {
                        navController.navigateUp()
                    },
                    onNextButtonClicked = {
                        navController.navigate(uiState.menu.name)
                    },
                    modifier = Modifier
                )
            }
        }
    }
}
