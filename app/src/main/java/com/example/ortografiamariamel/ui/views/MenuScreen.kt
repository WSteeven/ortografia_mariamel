package com.example.ortografiamariamel.ui.views

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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ortografiamariamel.AppScreen
import com.example.ortografiamariamel.R
import com.example.ortografiamariamel.ui.AppViewModel
import com.example.ortografiamariamel.ui.theme.OrtografiaMariamelTheme

@Composable
fun MenuScreen(
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
            modifier = Modifier.size(300.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(modifier = Modifier) {
            MenuItem(onClick = {
                viewModel.setUnidadActual(AppScreen.Unidad1)
                onNextButtonClicked()
            }, R.drawable.unidad1, R.string.unidad1)
            MenuItem(
                onClick = { viewModel.setUnidadActual(AppScreen.Unidad2)
                    onNextButtonClicked()},
                R.drawable.unidad2,
                R.string.unidad2
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row {
            MenuItem(
                onClick = { viewModel.setUnidadActual(AppScreen.Unidad3)
                    onNextButtonClicked()},
                R.drawable.unidad3,
                R.string.unidad3
            )
            MenuItem(
                onClick = { viewModel.setUnidadActual(AppScreen.Unidad4)
                    onNextButtonClicked()},
                R.drawable.unidad4,
                R.string.unidad4
            )

        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
        ) {
//            Boton Atrás
            BotonAtras(onClick = onPrevButtonClicked, imagen = R.drawable.back)
        }
    }

}

@Composable
fun MenuItem(onClick: () -> Unit, imagen: Int, contentDescription: Int) {
    Box(
        modifier = Modifier
            .size(150.dp)
            .padding(8.dp)
            .width(100.dp)
            .height(50.dp)
            .clickable(onClick = onClick)
    ) {
        Image(
            painter = painterResource(id = imagen),
            contentDescription = stringResource(contentDescription)
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
fun ButtonComponent(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        IconButton(onClick = onClick) {
            Icon(
                imageVector = Icons.Filled.Menu,
                contentDescription = "Menú"
            )
        }
    }
}
@Composable
fun MenuLateral(modifier: Modifier = Modifier) {
    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet(modifier = modifier, drawerContainerColor = Color(235, 179, 72, 255)) {
                Text("Ortografía Mariamel", style = TextStyle(fontWeight = FontWeight.Bold), modifier = Modifier.padding(16.dp))
                Divider()
                NavigationDrawerItem(
                    label = { Text(text = "Unidad 1", style = TextStyle(fontWeight = FontWeight.Bold)) },
                    selected = false,
                    onClick = { /*TODO*/ },
                    modifier = modifier
                )
                NavigationDrawerItem(
                    label = { Text(text = "Unidad 2", style = TextStyle(fontWeight = FontWeight.Bold)) },
                    selected = false,
                    onClick = { /*TODO*/ },
                    modifier = modifier
                )
                NavigationDrawerItem(
                    label = { Text(text = "Unidad 3", style = TextStyle(fontWeight = FontWeight.Bold)) },
                    selected = false,
                    onClick = { /*TODO*/ },
                    modifier = modifier
                )
                NavigationDrawerItem(
                    label = { Text(text = "Unidad 4", style = TextStyle(fontWeight = FontWeight.Bold)) },
                    selected = false,
                    onClick = { /*TODO*/ },
                    modifier = modifier
                )
                // ...other drawer items
            }
        }
    ) {}
}

@Preview(showBackground = true)
@Composable
fun MenuScreenPreview() {
    OrtografiaMariamelTheme {
        MenuScreen(viewModel = viewModel(), onPrevButtonClicked = {}, onNextButtonClicked =  {}, modifier = Modifier.fillMaxSize())
    }
}

