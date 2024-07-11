package com.example.ortografiamariamel.ui.views

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ortografiamariamel.AppScreen
import com.example.ortografiamariamel.R
import com.example.ortografiamariamel.ui.AppViewModel
import com.example.ortografiamariamel.ui.theme.OrtografiaMariamelTheme
import com.example.ortografiamariamel.ui.theme.scrimLight
import kotlinx.coroutines.launch


@Composable
fun MenuScreen(
    modifier: Modifier = Modifier,
    viewModel: AppViewModel = viewModel(),
    onPrevButtonClicked: () -> Unit,
    onNextButtonClicked: () -> Unit,
    onItemMenuButtonClicked: () -> Unit
) {
    DrawerState(
        viewModel = viewModel,
        onItemMenuButtonClicked = onItemMenuButtonClicked,
        content =
        {
            MenuContent(
                modifier.padding(top = 120.dp),
                viewModel,
                onPrevButtonClicked,
                onNextButtonClicked
            )
        },
        modifier = modifier,
    )
}

@Composable
fun MenuContent(
    modifier: Modifier = Modifier,
    viewModel: AppViewModel = viewModel(),
    onPrevButtonClicked: () -> Unit,
    onNextButtonClicked: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = R.drawable.caratula_libro_literatura),
            contentDescription = null,
            modifier = Modifier.size(240.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Row(modifier = Modifier) {
            MenuItem(
                onClick = {
                    viewModel.setPantallaActual(AppScreen.Unidad1)
                    onNextButtonClicked()
                }, R.drawable.unidad1, R.string.unidad1,
                viewModel = viewModel
            )
            MenuItem(
                onClick = {
                    viewModel.setPantallaActual(AppScreen.Unidad2)
                    onNextButtonClicked()
                },
                R.drawable.unidad2,
                R.string.unidad2,
                viewModel = viewModel,
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Row {
            MenuItem(
                onClick = {
                    viewModel.setPantallaActual(AppScreen.Unidad3)
                    onNextButtonClicked()
                },
                viewModel = viewModel,
                imagen = R.drawable.unidad3,
                contentDescription = R.string.unidad3
            )
            MenuItem(
                onClick = {
                    viewModel.setPantallaActual(AppScreen.Unidad4)
                    onNextButtonClicked()
                },
                R.drawable.unidad4,
                R.string.unidad4,
                viewModel = viewModel
            )

        }
//        Spacer(modifier = Modifier.height(4.dp))
        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(48.dp)
        ) {
//            Boton Atrás
            BotonAtras(onClick = onPrevButtonClicked, imagen = R.drawable.back)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerState(
    viewModel: AppViewModel,
    onItemMenuButtonClicked: () -> Unit,
    content: @Composable () -> Unit,
    modifier: Modifier = Modifier,
) {
    // [START android_compose_layout_material_modal_drawer_programmatic]
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            MenuItems(viewModel = viewModel, onItemMenuButtonClicked = onItemMenuButtonClicked)
        },
    ) {
        Scaffold(
            modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                AppTopBar(
                    mostrarEncabezado = true,
                    showMenu = {
                        ExtendedFloatingActionButton(
                            text = { Text("") },
                            icon = { Icon(Icons.Filled.Menu, contentDescription = "") },
                            containerColor = Color.Transparent,
                            contentColor = Color.White,
                            elevation = FloatingActionButtonDefaults.elevation(0.dp),
                            onClick = {
                                scope.launch {
                                    drawerState.apply {
                                        if (isClosed) open() else close()
                                    }
                                }
                            }
                        )
                    })
            },

            ) { contentPadding ->
            // Screen content
            content()
            // [START_EXCLUDE silent]
            Box(modifier = Modifier.padding(contentPadding)) { /* ... */ }
            // [END_EXCLUDE]
        }
    }
    // [END android_compose_layout_material_modal_drawer_programmatic]
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    modifier: Modifier = Modifier,
    mostrarEncabezado: Boolean = true,
    showMenu: @Composable () -> Unit,
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
                            text = "ORTOGRAFÍA",
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
            showMenu()
        }
    )
}


@Composable
fun MenuItem(onClick: () -> Unit, imagen: Int, contentDescription: Int, viewModel: AppViewModel) {
    Box(
        modifier = Modifier
            .size(150.dp)
            .padding(8.dp)
            .width(100.dp)
            .height(50.dp)
            .clickable(onClick = {
                Log.d(
                    "RutaNavegacion",
                    "Ruta en viewModel: ${viewModel.uiState.value.menu}"
                )
                onClick()
            })
    ) {
        Image(
            painter = painterResource(id = imagen),
            contentDescription = stringResource(contentDescription),
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun BotonAtras(onClick: () -> Unit, imagen: Int) {
    Box(
        modifier = Modifier
            .size(80.dp)
            .padding(8.dp)
            .width(100.dp)
            .height(50.dp)
            .clickable(onClick = onClick)
    ) {
        Image(
            painter = painterResource(id = imagen),
            contentDescription = stringResource(R.string.atras)
        )
    }
}


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
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(R.drawable.lapiz8),
                contentDescription = "Logo colegio",
                modifier = Modifier
                    .padding(8.dp)
                    .size(32.dp)
            )
            Text(
                "Ortografía Mariamel",
                modifier = Modifier.padding(16.dp),
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
        Divider()
        Spacer(modifier = Modifier.padding(4.dp))
//        NavigationDrawerItem(
//            colors = colors,
//            label = { Text(text = "Portada", fontWeight = FontWeight.Bold) },
//            selected = false,
//            onClick = {
//                viewModel.setPantallaActual(AppScreen.Inicio)
//                onItemMenuButtonClicked()
//            }
//        )
        Spacer(modifier = Modifier.padding(4.dp))
        NavigationDrawerItem(
            colors = colors,
            label = { Text(text = "Unidad 1", fontWeight = FontWeight.Bold) },
            selected = false,
            onClick = {
                viewModel.setPantallaActual(AppScreen.Unidad1)

                onItemMenuButtonClicked()
            }
        )
        Spacer(modifier = Modifier.padding(4.dp))
        NavigationDrawerItem(
            colors = colors,
            label = { Text(text = "Unidad 2", fontWeight = FontWeight.Bold) },
            selected = false,
            onClick = {
                viewModel.setPantallaActual(AppScreen.Unidad2)
                Log.d("RutaNavegacion","Ruta en viewModel: ${viewModel.uiState.value.menu}")
                onItemMenuButtonClicked()
                Log.d(
                    "RutaNavegacion",
                    "Despues Ruta en viewModel: ${viewModel.uiState.value.menu}"
                )
            }
        )
        Spacer(modifier = Modifier.padding(4.dp))
        NavigationDrawerItem(
            colors = colors,
            label = { Text(text = "Unidad 3", fontWeight = FontWeight.Bold) },
            selected = false,
            onClick = {
                viewModel.setPantallaActual(AppScreen.Unidad3)
                onItemMenuButtonClicked()
            }
        )
        Spacer(modifier = Modifier.padding(4.dp))
        NavigationDrawerItem(
            colors = colors,
            label = { Text(text = "Unidad 4", fontWeight = FontWeight.Bold) },
            selected = false,
            onClick = {
                viewModel.setPantallaActual(AppScreen.Unidad4)
                onItemMenuButtonClicked()
            }
        )

    }
}

@Preview(showBackground = true)
@Composable
fun MenuScreenPreview() {
    OrtografiaMariamelTheme {
        MenuScreen(
            viewModel = viewModel(),
            onPrevButtonClicked = {},
            onNextButtonClicked = {},
            onItemMenuButtonClicked = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}

