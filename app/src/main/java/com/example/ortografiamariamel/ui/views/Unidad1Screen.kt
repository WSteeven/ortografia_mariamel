package com.example.ortografiamariamel.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ortografiamariamel.R
import com.example.ortografiamariamel.ui.theme.OrtografiaMariamelTheme

@Composable
fun UnidadI(
    onPrevButtonClicked: () -> Unit,
    onNextButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    val tildeDiacrita = painterResource(R.drawable.imagen_tema_unidad1)
//    val tildeDiacrita = painterResource(R.drawable.tilde_diacritica)
    Column(modifier = modifier.padding(top = 16.dp)) {
        Text(
            text = "TEMA:", fontWeight = FontWeight.Bold, fontSize = 30.sp, modifier = modifier
                .align(Alignment.CenterHorizontally)
        )
        Text(
            text = "Tílde Diacrítica en los Monosílabos",
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
            painter = tildeDiacrita, contentDescription = "Unidad I",
            modifier = modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 16.dp),
            contentScale = ContentScale.FillBounds
        )
        Text(
            text = stringResource(id = R.string.parrafo1_unidad1),
            style = TextStyle(textAlign = TextAlign.Justify, fontFamily = FontFamily.SansSerif, letterSpacing = 2.sp),
            modifier = Modifier.padding(16.dp)
        )
        Text(
            text = stringResource(id = R.string.parrafo2_unidad1),
            style = TextStyle(textAlign = TextAlign.Justify, fontFamily = FontFamily.SansSerif, letterSpacing = 2.sp),
            modifier = Modifier.padding(16.dp)
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
}


@Preview(showBackground = true)
@Composable
fun UnidadIScreenPreview() {
    OrtografiaMariamelTheme {
        UnidadI(onPrevButtonClicked = { /*TODO*/ }, onNextButtonClicked = { /*TODO*/ })
    }
}
