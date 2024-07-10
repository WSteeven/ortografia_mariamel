package com.example.ortografiamariamel.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.ortografiamariamel.R
import com.example.ortografiamariamel.ui.navigation.AppNavHost
import com.example.ortografiamariamel.ui.theme.onPrimaryLight
import com.example.ortografiamariamel.ui.theme.primaryContainerLight
import com.example.ortografiamariamel.ui.theme.scrimLight
import com.example.ortografiamariamel.ui.views.InicioDestination
import com.example.ortografiamariamel.ui.views.unidad1.UnidadIDestination
import com.example.ortografiamariamel.ui.views.unidad2.UnidadIIDestination
import com.example.ortografiamariamel.ui.views.unidad3.UnidadIIIDestination
import com.example.ortografiamariamel.ui.views.unidad4.UnidadIVDestination
import kotlinx.coroutines.launch

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun AppTopBar(
    puedeNavegarAtras: Boolean,
    modifier: Modifier = Modifier,
    mostrarMenu: Boolean = false,
    mostrarEncabezado: Boolean = true,
    navigateUp: () -> Unit = {}
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

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
            containerColor = Color(255, 168, 0)
        ),
        modifier = modifier,
        navigationIcon = {
            if (puedeNavegarAtras && !mostrarMenu) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Atrás"
                    )
                }
            }
            if (mostrarMenu) {
                IconButton(onClick = {
                    scope.launch {
                        drawerState.apply { if (isClosed) open() else close() }
                    }
                }) {
                    Icon(
                        imageVector = Icons.Filled.Menu,
                        contentDescription = "Menú",
                    )
                }
            }
        }
    )

}



@Composable
fun MenuItems(){
    val colors =  NavigationDrawerItemDefaults.colors(
        unselectedContainerColor = Color(253, 233, 59),
        unselectedTextColor = scrimLight
    )
    val navController: NavHostController= rememberNavController()
    ModalDrawerSheet(drawerContainerColor = Color(255, 168, 0),
        drawerContentColor = Color.Yellow) { /* Drawer content */
        Text("Ortografía Mariamel", modifier = Modifier.padding(16.dp), fontWeight = FontWeight.Bold, color = Color.White)
        Divider()
        Spacer(modifier = Modifier.padding(4.dp))
        NavigationDrawerItem(
            colors = colors,
            label = { Text(text = "Portada", fontWeight = FontWeight.Bold) },
            selected = false,
            onClick = { navController.navigate(InicioDestination.route) }
        )
        Spacer(modifier = Modifier.padding(4.dp))
        NavigationDrawerItem(
            colors = colors,
            label = { Text(text = "Unidad 1", fontWeight = FontWeight.Bold) },
            selected = false,
            onClick = { navController.navigate(UnidadIDestination.route)}
        )
        Spacer(modifier = Modifier.padding(4.dp))
        NavigationDrawerItem(
            colors = colors,
            label = { Text(text = "Unidad 2", fontWeight = FontWeight.Bold) },
            selected = false,
            onClick = { navController.navigate(UnidadIIDestination.route) }
        )
        Spacer(modifier = Modifier.padding(4.dp))
        NavigationDrawerItem(
            colors = colors,
            label = { Text(text = "Unidad 3",fontWeight = FontWeight.Bold) },
            selected = false,
            onClick = { navController.navigate(UnidadIIIDestination.route) }
        )
        Spacer(modifier = Modifier.padding(4.dp))
        NavigationDrawerItem(
            colors = colors,
            label = { Text(text = "Unidad 4",fontWeight = FontWeight.Bold) },
            selected = false,
            onClick = { navController.navigate(UnidadIVDestination.route) }
        )

    }
}