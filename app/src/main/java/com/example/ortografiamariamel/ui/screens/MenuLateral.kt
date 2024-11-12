package com.example.ortografiamariamel.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ortografiamariamel.AppScreen
import com.example.ortografiamariamel.R
import com.example.ortografiamariamel.ui.AppViewModel
import com.example.ortografiamariamel.ui.AppViewModelProvider
import com.example.ortografiamariamel.ui.theme.OrtografiaMariamelTheme
import com.example.ortografiamariamel.ui.theme.scrimLight
import kotlinx.coroutines.launch

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun AppTopBar(
    modifier: Modifier = Modifier,
    title: Int = AppScreen.Inicio.title,
    puedeNavegarAtras: Boolean,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    mostrarMenu: Boolean = false,
    mostrarEncabezado: Boolean = true,
    navigateUp: () -> Unit = {}
) {

//    val windowSize = calculateWindowSizeClass(this)

    TopAppBar(
        scrollBehavior = scrollBehavior,
        title = {
            if (mostrarEncabezado) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp)
                ) {
                    Text(
                        text = stringResource(title),
                        fontSize = 18.sp,
                        modifier = Modifier.fillMaxWidth(.3f),
                        textAlign = TextAlign.Center
                    )
                    Row(
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Image(
                            painter = painterResource(R.drawable.logo_colegio),
                            contentDescription = "Logo colegio",
                            modifier = Modifier
                                .padding(end = 8.dp)
                                .size(48.dp)
                        )
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "APP",
                                fontSize = 14.sp,
                                style = TextStyle(
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    fontFamily = FontFamily.SansSerif
                                ),
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
                }
            } else Text("")
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Color(255, 168, 0)
        ),
        modifier = modifier.fillMaxWidth(),
        navigationIcon = {
            if (puedeNavegarAtras && !mostrarMenu) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Atrás"
                    )
                }
            }
            if (mostrarMenu) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.Menu,
                        contentDescription = "Menú",
                    )
                }
            }
        }
    )

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuLateral(
    viewModel: AppViewModel,
    modifier: Modifier = Modifier,
    title: Int = AppScreen.Menu.title,
    onItemMenuButtonClicked: () -> Unit,
    content: @Composable () -> Unit,
) {
    // [START android_compose_layout_material_modal_drawer_programmatic]
//    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val uiState by viewModel.uiState.collectAsState()
    val drawerState = rememberDrawerState(initialValue = uiState.drawer)
    val scope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val snackbarHostState = remember { SnackbarHostState() }  // Añadir ScaffoldState


    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            MenuItems(viewModel = viewModel, onItemMenuButtonClicked = onItemMenuButtonClicked)
        },
    ) {
        Scaffold(
            modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                AppTopBar(puedeNavegarAtras = false,
                    mostrarMenu = true,
                    mostrarEncabezado = true,
                    scrollBehavior = scrollBehavior,
                    title = title,
                    navigateUp = {
                        scope.launch {
                            drawerState.apply {
                                if (isClosed) open() else close()
                            }
                        }
                    }
                )
            },
            snackbarHost = { SnackbarHost(snackbarHostState) }

        ) { contentPadding ->
            // Screen content
            // [START_EXCLUDE silent]
            Box(modifier = Modifier.padding(contentPadding)) { /* ... */
                content()
            }
            // [END_EXCLUDE]
        }
    }
    // [END android_compose_layout_material_modal_drawer_programmatic]
}

/**
 * All side menu items are built here.
 * Put here other menu items
 */
@Composable
fun MenuItems(viewModel: AppViewModel, onItemMenuButtonClicked: () -> Unit) {
    val colors = NavigationDrawerItemDefaults.colors(
        unselectedContainerColor = Color(253, 233, 59),
        unselectedTextColor = scrimLight
    )
    ModalDrawerSheet(
        drawerContainerColor = Color(255, 168, 0),
        modifier = Modifier.fillMaxWidth(.5f),
        drawerContentColor = Color.Yellow
    ) { /* Drawer content */
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = {
                    viewModel.setPantallaActual(AppScreen.Menu)
                    viewModel.closeMenuLateral()
                    onItemMenuButtonClicked()
                })
        ) {
            Image(
                painter = painterResource(R.drawable.lapiz8),
                contentDescription = "Logo colegio",
                modifier = Modifier
                    .padding(8.dp)
                    .size(32.dp)
            )
            Text(
                "App Mariamel",
                modifier = Modifier.padding(16.dp),
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
        HorizontalDivider()
        Spacer(modifier = Modifier.padding(4.dp))
        NavigationDrawerItem(
            colors = colors,
            label = { Text(text = "Portada", fontSize=10.sp, fontWeight = FontWeight.Bold) },
            selected = false,
            onClick = {
                viewModel.setPantallaActual(AppScreen.Inicio)
                viewModel.closeMenuLateral()
                onItemMenuButtonClicked()
            }
        )
        Spacer(modifier = Modifier.padding(4.dp))
        NavigationDrawerItem(
            colors = colors,
            label = {Text(text = stringResource(id = R.string.tema_unidad_1), fontSize = 8.sp, fontWeight = FontWeight.Bold) },
            selected = false,
            onClick = {
                viewModel.setPantallaActual(AppScreen.Unidad1)
                viewModel.closeMenuLateral()
                onItemMenuButtonClicked()
            }
        )
        Spacer(modifier = Modifier.padding(4.dp))
        NavigationDrawerItem(
            colors = colors,
            label = {Text(text = stringResource(id = R.string.tema_unidad_2), fontSize = 8.sp, fontWeight = FontWeight.Bold) },
            selected = false,
            onClick = {
                viewModel.setPantallaActual(AppScreen.Unidad2)
                viewModel.closeMenuLateral()
//                Log.d("RutaNavegacion", "Ruta en viewModel: ${viewModel.uiState.value.menu}")
                onItemMenuButtonClicked()
//                Log.d("RutaNavegacion","Despues Ruta en viewModel: ${viewModel.uiState.value.menu}")
            }
        )
        Spacer(modifier = Modifier.padding(4.dp))
        NavigationDrawerItem(
            colors = colors,
            label = {Text(text = stringResource(id = R.string.tema_unidad_3), fontSize = 8.sp, fontWeight = FontWeight.Bold) },
            selected = false,
            onClick = {
                viewModel.setPantallaActual(AppScreen.Unidad3)
                viewModel.closeMenuLateral()
                onItemMenuButtonClicked()
            }
        )
        Spacer(modifier = Modifier.padding(4.dp))
        NavigationDrawerItem(
            colors = colors,
            label = {Text(text = stringResource(id = R.string.tema_unidad_4), fontSize = 8.sp, fontWeight = FontWeight.Bold) },
            selected = false,
            onClick = {
                viewModel.setPantallaActual(AppScreen.Unidad4)
                viewModel.closeMenuLateral()
                onItemMenuButtonClicked()
            }
        )
        Spacer(modifier = Modifier.padding(4.dp))
        NavigationDrawerItem(
            colors = colors,
            label = { Text(text = "Evaluación", fontSize=10.sp, fontWeight = FontWeight.Bold) },
            selected = false,
            onClick = {
                viewModel.setPantallaActual(AppScreen.Evaluacion)
                viewModel.closeMenuLateral()
                onItemMenuButtonClicked()
            }
        )
        Spacer(modifier = Modifier.padding(4.dp))
        NavigationDrawerItem(
            colors = colors,
            label = {
                Text(
                    text = stringResource(id = R.string.administracion),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            },
            selected = false,
            onClick = {
                viewModel.setPantallaActual(AppScreen.Administracion)
                viewModel.closeMenuLateral()
                onItemMenuButtonClicked()
            }
        )

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, widthDp = 305)
@Composable
fun AppTopBarPreview() {
    OrtografiaMariamelTheme {
        val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
        AppTopBar(
            title = AppScreen.DatosJugador.title,
            mostrarMenu = false,
            mostrarEncabezado = true,
            puedeNavegarAtras = true,
            scrollBehavior = scrollBehavior
        )
    }
}

@Preview(showBackground = true, widthDp = 305)
@Composable
fun MenuLateralPreview() {
    OrtografiaMariamelTheme {
        MenuLateral(
            title = AppScreen.DatosJugador.title,
            viewModel = viewModel(factory = AppViewModelProvider.Factory),
            onItemMenuButtonClicked = {},
            content = { Text("Hello") }
        )
    }
}
