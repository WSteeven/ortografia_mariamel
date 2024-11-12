package com.example.ortografiamariamel.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ortografiamariamel.R
import com.example.ortografiamariamel.ui.AppViewModel
import com.example.ortografiamariamel.ui.AppViewModelProvider
import com.example.ortografiamariamel.ui.theme.OrtografiaMariamelTheme

@Composable
fun EvaluacionScreen(
    viewModel: AppViewModel,
    modifier: Modifier = Modifier,
    onPrevButtonClicked: () -> Unit,
    onItemMenuButtonClicked: () -> Unit,
) {
    MenuLateral(
        title = R.string.blank, // AppScreen.Unidad2.title,
        content = {
            val tildeDiacrita = painterResource(R.drawable.imagen_unidad_2)
            Column(modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight(.85f)
                        .padding(4.dp)
                        .verticalScroll(rememberScrollState()) // Habilitar desplazamiento vertical
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Spacer(modifier = Modifier.weight(1f)) // Espacio flexible para centrar el texto
                        Text(
                            text = "EVALUACION",
                            fontWeight = FontWeight.Bold,
                            fontSize = 30.sp,
                            modifier = Modifier.padding(start = 64.dp) // Espacio al final del texto
                        )
                        Spacer(modifier = Modifier.weight(1f)) // Espacio flexible para centrar el texto
                    }

                    Text(
                        text = "Aquí va la evaluación",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 25.sp,
                        textAlign = TextAlign.Center,
                        fontFamily = FontFamily.SansSerif,
                        letterSpacing = 3.sp,
                        color = Color(230, 170, 75),
                        modifier = modifier
                            .align(Alignment.CenterHorizontally)
                    )

                }
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Row(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(dimensionResource(R.dimen.padding_medium)),
                        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
                        verticalAlignment = Alignment.Bottom
                    ) {
                        OutlinedButton(
                            modifier = Modifier.weight(1f),
                            onClick = onPrevButtonClicked
                        ) {
                            Text(stringResource(R.string.atras))
                        }

                    }

                }
            }
        },
        viewModel = viewModel,
        onItemMenuButtonClicked = onItemMenuButtonClicked,
        modifier = modifier
    )
}


@Preview(showBackground = true)
@Composable
fun EvaluacionScreenPreview() {
    OrtografiaMariamelTheme {
        EvaluacionScreen(
            viewModel = viewModel(factory = AppViewModelProvider.Factory),
            onPrevButtonClicked = { /*TODO*/ }, onItemMenuButtonClicked = {})
    }
}
