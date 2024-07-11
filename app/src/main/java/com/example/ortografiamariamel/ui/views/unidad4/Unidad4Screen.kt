package com.example.ortografiamariamel.ui.views.unidad4

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
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
import com.example.ortografiamariamel.ui.screens.AppTopBar
import com.example.ortografiamariamel.ui.theme.OrtografiaMariamelTheme
import com.example.ortografiamariamel.ui.views.DrawerState



@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun UnidadIV(onPrevButtonClicked: () -> Unit,
             onNextButtonClicked: () -> Unit,
             viewModel: AppViewModel = viewModel(factory = AppViewModelProvider.Factory),
             onItemMenuButtonClicked: ()->Unit,
             modifier: Modifier = Modifier
){
    DrawerState(content = {
        val tildeDiacrita = painterResource(R.drawable.imagen_tema_unidad1)
        val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
        Scaffold(
            modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                AppTopBar(
                    puedeNavegarAtras = false,
                    modifier = modifier,
                    mostrarEncabezado = false,
                    mostrarMenu = false
                )
            }
        ) { innerPadding ->
            Column(modifier = modifier.padding(innerPadding)) {
                Text(
                    text = "UNIDAD IV - TEMA:",
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp,
                    modifier = modifier
                        .align(Alignment.CenterHorizontally)
                )
                Text(
                    text = "Tema Unidad IV",
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
                    painter = tildeDiacrita, contentDescription = "Unidad IV",
                    modifier = modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 16.dp),
                    contentScale = ContentScale.FillBounds
                )
                Text("Aquí va el texto de la unidad IV", modifier = Modifier
                    .fillMaxHeight(.5f)
                    .padding(16.dp))
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
    }, viewModel = viewModel, onItemMenuButtonClicked = onItemMenuButtonClicked, modifier = modifier)
}


@Preview(showBackground = true)
@Composable
fun UnidadIVScreenPreview() {
    OrtografiaMariamelTheme {
        UnidadIV(onPrevButtonClicked = { /*TODO*/ }, onNextButtonClicked = { /*TODO*/ }, onItemMenuButtonClicked = {})
    }
}
