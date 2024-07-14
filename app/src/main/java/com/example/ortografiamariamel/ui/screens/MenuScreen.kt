package com.example.ortografiamariamel.ui.screens

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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
                pantallaActual = AppScreen.Unidad1,
                onClick = onNextButtonClicked, R.drawable.unidad1, R.string.unidad1,
                viewModel = viewModel
            )
            MenuItem(
                pantallaActual = AppScreen.Unidad2,
                onClick = onNextButtonClicked,
                R.drawable.unidad2,
                R.string.unidad2,
                viewModel = viewModel,
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Row {
            MenuItem(
                pantallaActual = AppScreen.Unidad3,
                onClick = onNextButtonClicked,
                viewModel = viewModel,
                imagen = R.drawable.unidad3,
                contentDescription = R.string.unidad3
            )
            MenuItem(
                pantallaActual = AppScreen.Unidad4,
                onClick = onNextButtonClicked,
                R.drawable.unidad4,
                R.string.unidad4,
                viewModel = viewModel
            )

        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Bottom
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.Bottom
                ,
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
    imagen: Int,
    contentDescription: Int,
    viewModel: AppViewModel
) {
    Box(
        modifier = Modifier
            .size(150.dp)
            .padding(8.dp)
            .width(100.dp)
            .height(50.dp)
            .clickable(onClick = {
                viewModel.setPantallaActual(pantallaActual)
//                Log.d("MenuItem", "Ruta inicial en viewModel: ${viewModel.uiState.value.menu}")
                onClick()
//                Log.d("MenuItem", "Ruta despues en viewModel: ${viewModel.uiState.value.menu}")
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

