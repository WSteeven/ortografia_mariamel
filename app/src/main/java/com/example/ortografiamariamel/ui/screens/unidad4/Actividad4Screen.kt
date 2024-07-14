package com.example.ortografiamariamel.ui.screens.unidad4

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ortografiamariamel.AppScreen
import com.example.ortografiamariamel.ui.AppViewModel
import com.example.ortografiamariamel.ui.screens.MenuLateral


@Composable
fun Actividad4(
    viewModel: AppViewModel,
    modifier: Modifier = Modifier,
    onItemMenuButtonClicked: () -> Unit
) {
    MenuLateral(
        title = AppScreen.Actividad4.title,
        content = {
            Column(
                modifier = modifier
                    .padding(top = 120.dp)
                    .padding(horizontal = 8.dp)
            ) {
                Text(
                    text = "ACTIVIDAD - UNIDAD IV",
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp,
                    modifier = modifier
                        .align(Alignment.CenterHorizontally)
                )
                Text(
                    text = "Aquí va el juego o la actividad de la unidad 4"
                )
            }
        },
        viewModel = viewModel,
        onItemMenuButtonClicked = onItemMenuButtonClicked,
        modifier = modifier
    )
}

