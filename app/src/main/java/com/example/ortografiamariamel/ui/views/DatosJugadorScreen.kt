package com.example.ortografiamariamel.ui.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ortografiamariamel.AppScreen
import com.example.ortografiamariamel.R
import com.example.ortografiamariamel.ui.AppViewModel
import com.example.ortografiamariamel.ui.AppViewModelProvider
import com.example.ortografiamariamel.ui.navigation.NavigationDestination
import com.example.ortografiamariamel.ui.theme.OrtografiaMariamelTheme

object DatosJugadorDestination: NavigationDestination {
    override val route= "datos"
    override val title = "Datos del Jugador"
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatosJugadorScreen(
    viewModel: AppViewModel = viewModel(factory = AppViewModelProvider.Factory),
    onNextButtonClicked: () -> Unit,
    modifier: Modifier
) {
    val uiState by viewModel.uiState.collectAsState()
    val imageLogo = painterResource(R.drawable.lapiz8)
    var playerName by remember { mutableStateOf(uiState.nombreJugador) }
    var sliderValue by remember { mutableFloatStateOf(8f) }
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            com.example.ortografiamariamel.ui.screens.AppTopBar(
                puedeNavegarAtras = false,
                modifier = modifier,
                mostrarEncabezado = false,
                mostrarMenu = false
            )
        }
    ) { innerPadding ->

        Column(modifier = modifier.padding(innerPadding)) {
            ContinuousSlideAnimation(
                modifier = Modifier
                    .fillMaxSize(.6f)
                    .align(alignment = Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "INGRESA TU NOMBRE",

                modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = playerName,
                onValueChange = { playerName = it },
                singleLine = true,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .border(BorderStroke(width = 2.dp, color = Color(255, 168, 0))),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                ),
            )
            Spacer(modifier = Modifier.height(16.dp))
            Slider(
                value = sliderValue,
                onValueChange = { sliderValue = it },
                valueRange = 4f..12f,
                steps = 7,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                buildAnnotatedString {
                    append("Tu Edad:")
                    withStyle(
                        style = SpanStyle(
                            color = Color(240, 150, 55),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.SansSerif
                        )
                    ) {
                        append(sliderValue.toInt().toString())
                    }
                },
                modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                enabled = playerName.isNotEmpty(),
                onClick = {
                    viewModel.setNombreJugador(playerName)
                    viewModel.setEdad(sliderValue.toInt())
                    onNextButtonClicked()
                },
                modifier = Modifier.align(Alignment.CenterHorizontally),
                border = BorderStroke(4.dp, Color(244, 225, 220)),
                shape = MaterialTheme.shapes.extraLarge,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(240, 150, 55), contentColor = Color.White
                ),
            ) {
                Text(text = stringResource(R.string.siguiente), fontSize = 30.sp)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DatosJugadorPreview() {
    OrtografiaMariamelTheme {
        DatosJugadorScreen(viewModel(),{},modifier = Modifier)
    }
}

