package com.example.ortografiamariamel.ui.views.unidad1

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
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
import com.example.ortografiamariamel.ui.game.MatchPairs
import com.example.ortografiamariamel.ui.theme.OrtografiaMariamelTheme
import com.example.ortografiamariamel.ui.views.DrawerState

@Composable
fun Actividad1(
    modifier: Modifier = Modifier,
    viewModel: AppViewModel = viewModel(factory = AppViewModelProvider.Factory),
    onPrevButtonClicked: () -> Unit,
    onNextButtonClicked: () -> Unit,
    onItemMenuButtonClicked: ()->Unit
) {
DrawerState(content = {
    Column(
        modifier = modifier
            .padding(top = 120.dp)
            .padding(horizontal = 8.dp)
    ) {
        Text(
            text = "ACTIVIDAD", fontWeight = FontWeight.Bold, fontSize = 30.sp, modifier = modifier
                .align(Alignment.CenterHorizontally)
        )
        Text(
            text = "Elija la carta correcta de acuerdo al monosílabo",
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            textAlign = TextAlign.Justify,
            fontFamily = FontFamily.SansSerif,
            letterSpacing = 1.sp,
            color = Color(230, 170, 75),
            modifier = modifier
                .align(Alignment.CenterHorizontally)
        )
        MatchPairs()
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
                onClick = {
                    viewModel.setPantallaActual(AppScreen.Menu)
                    onNextButtonClicked()
                }
            ) {
                Text(stringResource(R.string.siguiente))
            }
        }
    }
}, viewModel = viewModel, onItemMenuButtonClicked = onItemMenuButtonClicked, modifier = modifier)
}


@Preview(showBackground = true)
@Composable
fun ActividadScreenPreview() {
    OrtografiaMariamelTheme {
        Actividad1(
            viewModel = viewModel(),
            onPrevButtonClicked = { /*TODO*/ },
            onItemMenuButtonClicked = {},
            onNextButtonClicked = { /*TODO*/ })
    }
}