package com.example.ortografiamariamel.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ortografiamariamel.AppScreen
import com.example.ortografiamariamel.R
import com.example.ortografiamariamel.ui.AppViewModel
import com.example.ortografiamariamel.ui.theme.OrtografiaMariamelTheme


@Composable
fun MenuScreen(
    viewModel: AppViewModel,
    modifier: Modifier = Modifier,
    onPrevButtonClicked: () -> Unit,
    onNextButtonClicked: () -> Unit,
    onItemMenuButtonClicked: () -> Unit
) {
    MenuLateral(
        viewModel = viewModel,
        onItemMenuButtonClicked = onItemMenuButtonClicked,
        content =
        {
            MenuContent(
                modifier.padding(top = 4.dp),
                viewModel,
                onPrevButtonClicked,
                onNextButtonClicked
            )
        },
        modifier = modifier,
    )
}

/**
 * Contenido del menu de esta seccion de la app
 */
@Composable
fun MenuContent(
    modifier: Modifier = Modifier,
    viewModel: AppViewModel,
    onPrevButtonClicked: () -> Unit,
    onNextButtonClicked: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = R.drawable.fondo_menu),
            contentDescription = null,
            modifier = Modifier.size(340.dp)
        )
//        Spacer(modifier = Modifier.height(40.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            MenuItem(
                pantallaActual = AppScreen.Unidad1,
                onClick = onNextButtonClicked,
                R.string.tema_unidad_1,
                viewModel = viewModel,
                modifier = Modifier.weight(1f) // Asigna el mismo peso a este botón
            )
            MenuItem(
                pantallaActual = AppScreen.Unidad2,
                onClick = onNextButtonClicked,
                R.string.tema_unidad_2,
                viewModel = viewModel,
                modifier = Modifier.weight(1f) // Asigna el mismo peso a este botón
            )
        }
//        Spacer(modifier = Modifier.height(40.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            MenuItem(
                pantallaActual = AppScreen.Unidad3,
                onClick = onNextButtonClicked,
                viewModel = viewModel,
                textButton = R.string.tema_unidad_3,
                modifier = Modifier.weight(1f) // Asigna el mismo peso a este botón
            )
            MenuItem(
                pantallaActual = AppScreen.Unidad4,
                onClick = onNextButtonClicked,
                R.string.tema_unidad_4,
                viewModel = viewModel,
                modifier = Modifier.weight(1f) // Asigna el mismo peso a este botón
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Bottom
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier


            ) {
//            Boton Atrás
                BotonAtras(onClick = onPrevButtonClicked, imagen = R.drawable.back)
            }
        }
    }
}


@Composable
fun MenuItem(
    pantallaActual: AppScreen,
    onClick: () -> Unit,
    textButton: Int,
    viewModel: AppViewModel,
    modifier: Modifier
) {
    Button(
        modifier = modifier.height(80.dp),
        border = BorderStroke(4.dp, Color(244, 225, 220)),
        shape = MaterialTheme.shapes.extraLarge,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(240, 150, 55), contentColor = Color.White
        ),
        onClick = {
            viewModel.setPantallaActual(pantallaActual)
            onClick()
        }) {
        Text(text = stringResource(id = textButton), textAlign = TextAlign.Center)
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

