package com.example.ortografiamariamel.ui.screens.unidad4

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ortografiamariamel.AppScreen
import com.example.ortografiamariamel.R
import com.example.ortografiamariamel.ui.AppViewModel
import com.example.ortografiamariamel.ui.AppViewModelProvider
import com.example.ortografiamariamel.ui.screens.MenuLateral
import com.example.ortografiamariamel.ui.theme.OrtografiaMariamelTheme


@Composable
fun UnidadIV(
    viewModel: AppViewModel,
    onPrevButtonClicked: () -> Unit,
    onNextButtonClicked: () -> Unit,
    onItemMenuButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    MenuLateral(
        title = AppScreen.Unidad4.title,
        content = {
            val imagenTema = painterResource(R.drawable.imagen_unidad4)
            Column(
                modifier = modifier
                    .padding(4.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Spacer(modifier = Modifier.weight(1f)) // Espacio flexible para centrar el texto
                    Text(
                        text = stringResource(id = R.string.recordemos),
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                        modifier = Modifier.padding(start = 64.dp) // Espacio al final del texto
                    )
                    Spacer(modifier = Modifier.weight(1f)) // Espacio flexible para centrar el texto
                }
                Text(
                    text = stringResource(id = R.string.tema_unidad_4),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 25.sp,
                    textAlign = TextAlign.Center,
                    fontFamily = FontFamily.SansSerif,
                    letterSpacing = 3.sp,
                    color = Color(230, 170, 75),
                    modifier = modifier
                        .align(Alignment.CenterHorizontally)
                )
                Image(
                    painter = imagenTema, contentDescription = "Unidad IV",
                    modifier = modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 16.dp),
                    contentScale = ContentScale.FillBounds
                )
                Text(
                    text = stringResource(id = R.string.parrafo1_unidad4),
                    style = TextStyle(
                        textAlign = TextAlign.Justify,
                        fontFamily = FontFamily.SansSerif,
                        letterSpacing = 2.sp
                    ),
                    modifier = Modifier.padding(16.dp)
                )
                Text(
                    "Ejemplo:", style = TextStyle(
                        textAlign = TextAlign.Justify,
                        fontFamily = FontFamily.SansSerif,
                        letterSpacing = 1.sp
                    ),
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Text(
                    text = "- ${stringResource(id = R.string.ejemplo1_unidad4)}",
                    style = TextStyle(
                        textAlign = TextAlign.Justify,
                        fontFamily = FontFamily.SansSerif,
                        letterSpacing = 1.sp
                    ),
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Text(
                    text = stringResource(id = R.string.parrafo2_unidad4),
                    style = TextStyle(
                        textAlign = TextAlign.Justify,
                        fontFamily = FontFamily.SansSerif,
                        letterSpacing = 2.sp
                    ),
                    modifier = Modifier.padding(16.dp)
                )
                Text(
                    "Ejemplo:", style = TextStyle(
                        textAlign = TextAlign.Justify,
                        fontFamily = FontFamily.SansSerif,
                        letterSpacing = 1.sp
                    ),
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Text(
                    text = "- ${stringResource(id = R.string.ejemplo2_unidad4)}",
                    style = TextStyle(
                        textAlign = TextAlign.Justify,
                        fontFamily = FontFamily.SansSerif,
                        letterSpacing = 1.sp
                    ),
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
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
                    Button(
                        modifier = Modifier.weight(1f),
                        // the button is enabled when the user makes a selection
                        onClick = onNextButtonClicked
                    ) {
                        Text(stringResource(R.string.siguiente))
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
fun UnidadIVScreenPreview() {
    OrtografiaMariamelTheme {
        UnidadIV(
            viewModel = viewModel(factory = AppViewModelProvider.Factory),
            onPrevButtonClicked = { /*TODO*/ },
            onNextButtonClicked = { /*TODO*/ },
            onItemMenuButtonClicked = {})
    }
}
