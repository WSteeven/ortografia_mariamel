package com.example.ortografiamariamel


import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.ortografiamariamel.ui.navigation.AppNavHost
import com.example.ortografiamariamel.ui.theme.OrtografiaMariamelTheme

// Screens
enum class AppScreen(@StringRes val title: Int) {
    Inicio(R.string.inicio),
    DatosJugador(R.string.datos_jugador),
    Menu(R.string.menu),
    Unidad1(R.string.unidad1),
    Actividad1(R.string.actividad1),
    Unidad2(R.string.unidad2),
    Actividad2(R.string.actividad2),
    Unidad3(R.string.unidad3),
    Actividad3(R.string.actividad3),
    Unidad4(R.string.unidad4),
    Actividad4(R.string.actividad4),
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
fun App(navController: NavHostController = rememberNavController()){
    AppNavHost(navController = navController)
}
@Preview(showBackground = true)
@Composable
fun MenuScreenPreview() {
    OrtografiaMariamelTheme {
        App()
    }
}