package com.example.ortografiamariamel.ui.screens.unidad4

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.example.ortografiamariamel.ui.game.Dictado
import com.example.ortografiamariamel.ui.screens.MenuLateral
import com.example.ortografiamariamel.ui.screens.unidad1.LottieAnimationScreen
import com.example.ortografiamariamel.ui.theme.OrtografiaMariamelTheme


@Composable
fun Actividad3U4(
    viewModel: AppViewModel,
    modifier: Modifier = Modifier,
    onPrevButtonClicked: () -> Unit,
    onNextButtonClicked: () -> Unit,
    onItemMenuButtonClicked: () -> Unit
) {
    // Define snackbarHostState
    val snackbarHostState = remember { SnackbarHostState() }

    MenuLateral(
        title = R.string.blank,
        content = {
            Column(
                modifier = Modifier
                    .padding(4.dp)
                    .verticalScroll(rememberScrollState())
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
                            .fillMaxWidth(.4f)
                            .fillMaxHeight(.07f)
                    ) {
                        LottieAnimationScreen()
                    }
                }
                Text(
                    text = "Escucha los audios y escribe la oración en los espacios en blanco.",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Justify,
                    fontFamily = FontFamily.SansSerif,
                    letterSpacing = 1.sp,
                    color = Color(230, 170, 75),
                    modifier = modifier
                        .align(Alignment.CenterHorizontally)
                )
                Dictado()
//                Column(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .padding(0.dp),
//                    horizontalAlignment = Alignment.CenterHorizontally,
//                    verticalArrangement = Arrangement.Bottom
//                ) {
//                    Row(
//                        modifier = modifier
//                            .fillMaxWidth()
//                            .padding(dimensionResource(R.dimen.padding_small)),
//                        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
//                        verticalAlignment = Alignment.Bottom
//                    ) {
//                        OutlinedButton(
//                            modifier = Modifier.weight(1f),
//                            onClick = onPrevButtonClicked
//                        ) {
//                            Text(stringResource(R.string.atras))
//                        }
//                    }
//                }
            }
        },
        viewModel = viewModel,
        onItemMenuButtonClicked = onItemMenuButtonClicked,
        modifier = modifier
    )
    // Add SnackbarHost at the top level of your composable hierarchy
    SnackbarHost(hostState = snackbarHostState)
}

@Preview(showBackground = true, widthDp = 325, heightDp = 967)
@Composable
fun Actividad3U4ScreenPreview() {
    OrtografiaMariamelTheme {
        Actividad3U4(
            viewModel = viewModel(factory = AppViewModelProvider.Factory),
            onPrevButtonClicked = { /*TODO*/ },
            onItemMenuButtonClicked = {},
            onNextButtonClicked = { /*TODO*/ })
    }
}