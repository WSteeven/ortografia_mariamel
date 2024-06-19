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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.ortografiamariamel.R

@Composable
fun UnidadI(
    onPrevButtonClicked: ()->Unit,
    onNextButtonClicked: ()->Unit,
    modifier: Modifier = Modifier) {
    val tildeDiacrita = painterResource(R.drawable.tilde_diacritica)
    Column(modifier = modifier) {

        Image(
            painter = tildeDiacrita, contentDescription = "Unidad I", modifier = modifier
                .fillMaxSize(),
            contentScale = ContentScale.FillBounds
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
