package com.example.ortografiamariamel.ui.screens.unidad3

import androidx.compose.foundation.Image
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
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
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
import com.example.ortografiamariamel.R
import com.example.ortografiamariamel.service.SoundManager
import com.example.ortografiamariamel.ui.AppViewModel
import com.example.ortografiamariamel.ui.AppViewModelProvider
import com.example.ortografiamariamel.ui.screens.MenuLateral
import com.example.ortografiamariamel.ui.theme.OrtografiaMariamelTheme


@Composable
fun UnidadIII(
    viewModel: AppViewModel,
    modifier: Modifier = Modifier,
    onPrevButtonClicked: () -> Unit,
    onNextButtonClicked: () -> Unit,
    onItemMenuButtonClicked: () -> Unit,
) {
    MenuLateral(
        title =R.string.blank, // AppScreen.Unidad2.title,
        content = {
            val tildeDiacrita = painterResource(R.drawable.imagen_unidad_2)
            val soundManager = SoundManager(LocalContext.current)
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
                            text = "TEMA",
                            fontWeight = FontWeight.Bold,
                            fontSize = 30.sp,
                            modifier = Modifier.padding(start = 64.dp) // Espacio al final del texto
                        )
                        Spacer(modifier = Modifier.weight(1f)) // Espacio flexible para centrar el texto
//                        Row(
//                            verticalAlignment = Alignment.Bottom // Alineación de los botones al fondo
//                        ) {
//                            IconButton(
//                                onClick = { soundManager.playSound(R.raw.audio_tema1) },
//                                modifier = Modifier.padding(end = 0.dp) // Espacio entre los botones
//                            ) {
//                                Icon(
//                                    painter = painterResource(id = R.drawable.speaker_filled),
//                                    contentDescription = "audio_speaker",
//                                    modifier = Modifier
//                                        .padding(end = 20.dp) // Espacio a la derecha de la imagen
//                                )
//                                Image(
//                                    painter = painterResource(id = R.drawable.lapiz6),
//                                    contentDescription = "Audio",
//                                    modifier = Modifier
//                                        .padding(start= 20.dp) // Espacio a la derecha de la imagen
//                                        .size(48.dp) // Tamaño deseado de la imagen
//                                )
//
//                            }
//                        }
                    }

                    Text(
                        text = "El uso de la z en verbos terminados en-ecer, ucir",
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
                        painter = tildeDiacrita, contentDescription = "Unidad III",
                        modifier = modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(top = 16.dp),
                        contentScale = ContentScale.FillBounds
                    )
                    Text(
                        text = stringResource(id = R.string.parrafo1_unidad3),
                        style = TextStyle(
                            textAlign = TextAlign.Justify,
                            fontFamily = FontFamily.SansSerif,
                            letterSpacing = 2.sp
                        ),
                        modifier = Modifier.padding(16.dp)
                    )
                    Text(
                        text = stringResource(id = R.string.parrafo2_unidad3),
                        style = TextStyle(
                            textAlign = TextAlign.Justify,
                            fontFamily = FontFamily.SansSerif,
                            letterSpacing = 2.sp
                        ),
                        modifier = Modifier.padding(16.dp)
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
            /*TODO*/
        },
        viewModel = viewModel,
        onItemMenuButtonClicked = onItemMenuButtonClicked,
        modifier = modifier
    )
}


@Preview(showBackground = true)
@Composable
fun UnidadIIIScreenPreview() {
    OrtografiaMariamelTheme {
        UnidadIII(
            viewModel = viewModel(factory = AppViewModelProvider.Factory),
            onPrevButtonClicked = { /*TODO*/ },
            onNextButtonClicked = { /*TODO*/ },
            onItemMenuButtonClicked = {})
    }
}
