package com.example.ortografiamariamel.ui.screens.unidad1

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ortografiamariamel.R
import com.example.ortografiamariamel.repository.FirebaseRepository
import com.example.ortografiamariamel.ui.theme.OrtografiaMariamelTheme

@Composable
fun FinJuegoGanador(
    respuestasCorrectas: Int,
    totalRespuestas: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    val firebase = FirebaseRepository(LocalContext.current)
    val localNombre = firebase.leerNombreLocalmente()
    Column(
        modifier = Modifier
            .padding(4.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.End,
                modifier = modifier
                    .fillMaxWidth()
                    .fillMaxHeight(.6f)
            ) {
                LottieAnimationStar()
            }
        }
        Text(
            text = "Felicitaciones $localNombre",
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            fontFamily = FontFamily.SansSerif,
            letterSpacing = 1.sp,
            color = Color(230, 170, 75),
            modifier = modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
        )
        Text("Has acertado $respuestasCorrectas/$totalRespuestas respuestas correctas")
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.padding_small)),
                horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
                verticalAlignment = Alignment.Bottom
            ) {
                Button(
                    modifier = Modifier.weight(1f),
                    // the button is enabled when the user makes a selection
                    onClick = {
                        onClick()
                    }
                ) {
                    Text(stringResource(R.string.siguiente))
                }
            }
        }
    }
}


@Preview(showBackground = true, widthDp = 352, heightDp = 500)
@Composable
fun FinJuegoGanadorPreview() {
    OrtografiaMariamelTheme {
        FinJuegoGanador(
            5, 5,
            onClick = { /*TODO*/ },
        )
    }
}
