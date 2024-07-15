package com.example.ortografiamariamel.ui.screens.unidad1

import android.annotation.SuppressLint
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
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
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieAnimatable
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.ortografiamariamel.AppScreen
import com.example.ortografiamariamel.R
import com.example.ortografiamariamel.ui.AppViewModel
import com.example.ortografiamariamel.ui.AppViewModelProvider
import com.example.ortografiamariamel.ui.screens.MenuLateral
import com.example.ortografiamariamel.ui.theme.OrtografiaMariamelTheme

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun FinJuegoUnidad1(
    viewModel: AppViewModel,
    modifier: Modifier = Modifier,
    onItemMenuButtonClicked: () -> Unit = {},
    onClick: () -> Unit = {}
) {
    MenuLateral(
        title = R.string.blank,
        viewModel = viewModel,
        onItemMenuButtonClicked = onItemMenuButtonClicked,
        modifier = modifier,
        content = {
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
                    text = "Felicitaciones ${viewModel.uiState.value.nombreJugador}",
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
                Text("Has acertado 4/4 respuestas correctas")
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
                                viewModel.setPantallaActual(AppScreen.Menu)
                                onClick()
                            }
                        ) {
                            Text(stringResource(R.string.siguiente))
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun LottieAnimationStar() {
//    val context = LocalContext.current

    // Cargar la animación desde assets
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.start_motion))

    // Controlar la animación
    val animatable = rememberLottieAnimatable()

    LaunchedEffect(composition) {
        animatable.animate(composition)
    }

    LottieAnimation(
        composition = composition,
        modifier = Modifier.fillMaxSize()
    )
}


@Preview(showBackground = true, widthDp = 352, heightDp = 500)
@Composable
fun FinJuegoUnidadIScreenPreview() {
    OrtografiaMariamelTheme {
        FinJuegoUnidad1(
            viewModel = viewModel(factory = AppViewModelProvider.Factory),
            onClick = { /*TODO*/ },
            onItemMenuButtonClicked = {})
    }
}
