package com.example.ortografiamariamel.ui.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.example.ortografiamariamel.AppScreen
import com.example.ortografiamariamel.R
import com.example.ortografiamariamel.data.Carta

import com.example.ortografiamariamel.ui.AppViewModel
import com.example.ortografiamariamel.ui.AppViewModelProvider
import com.example.ortografiamariamel.ui.game.MatchPairs
import com.example.ortografiamariamel.ui.navigation.NavigationDestination
import com.example.ortografiamariamel.ui.theme.OrtografiaMariamelTheme
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

object ActividadIDestination : NavigationDestination {
    override val route = "actividad1"
    override val title = "Actividad I"
}
@Composable
fun Actividad1(
    viewModel: AppViewModel = viewModel(factory = AppViewModelProvider.Factory),
    onPrevButtonClicked: () -> Unit,
    onNextButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
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
                    viewModel.setUnidadActual(AppScreen.Menu)
                    onNextButtonClicked()
                }
            ) {
                Text(stringResource(R.string.siguiente))
            }
        }
    }
}, modifier = modifier)
}

@Composable
fun Actividad1Old(
    viewModel: AppViewModel,
    onPrevButtonClicked: () -> Unit,
    onNextButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    val imagen = painterResource(R.drawable.actividad_unidad1)
    Column(
        modifier = modifier
            .padding(top = 16.dp)
            .padding(horizontal = 8.dp)
    ) {
        Text(
            text = "ACTIVIDAD", fontWeight = FontWeight.Bold, fontSize = 30.sp, modifier = modifier
                .align(Alignment.CenterHorizontally)
        )
        Text(
            text = "Completa las siguientes oraciones con la respuesta correcta",
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            textAlign = TextAlign.Justify,
            fontFamily = FontFamily.SansSerif,
            letterSpacing = 1.sp,
            color = Color(230, 170, 75),
            modifier = modifier
                .align(Alignment.CenterHorizontally)

        )
        SentenceCompletionList(onNextButtonClicked)
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
                    viewModel.setUnidadActual(AppScreen.Menu)
                    onNextButtonClicked()
                }
            ) {
                Text(stringResource(R.string.siguiente))
            }
        }
    }
}

@Composable
fun MatchPairsItems(onNextButtonClicked: () -> Unit) {
    CardContent(onClick = { /*TODO*/ })
}

@Composable
fun CardContent(onClick: () -> Unit, modifier: Modifier = Modifier) {
    var color by remember {
        mutableStateOf(Color.White)
    }
    Card(
        modifier = Modifier
            .padding(16.dp)
            .clickable {
                color = if (color == Color.White) {
                    Color.Yellow
                } else {
                    Color.White
                }
            },
        colors = CardDefaults.cardColors(containerColor = color),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Box(
            modifier = Modifier
        ) {
            Text(text = "Hola")
        }
    }
}

@Composable
fun CardItem(
    card: Carta,
    onClick: (Carta) -> Unit
) {
    var backgroundColor = remember {
        mutableStateOf(Color.White)
    }
    when {
        card.isMatched -> Color.Green // Si el par ha sido encontrado
        card.isSelected -> Color.Yellow // Si la carta está seleccionada
        else -> Color.White // Color por defecto
    }
    Card(
        border = BorderStroke(2.dp, Color.Black),
        modifier = Modifier.fillMaxSize(.4f)
            .padding(8.dp)
            .clickable {

                onClick(card)
                backgroundColor = mutableStateOf(
                    when {
                        card.isMatched -> Color.Green
                        card.isSelected -> Color.Yellow
                        else -> Color.White
                    }
                )
            },
        colors = CardDefaults.cardColors(containerColor = backgroundColor.value)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(text = card.number.toString() + " " + card.isSelected.toString(), fontSize = 24.sp)
        }
    }
}

@Composable
fun MemoryGame() {
    // Lista de cartas en el juego
    val cards = remember {
        mutableStateListOf(
            Carta(2, "2", number = 2),
            Carta(3, "1", number = 1),
            Carta(4, "2", number = 2),
            Carta(5, "3", number = 3),
            Carta(6, "3", number = 3),
            Carta(1, "1", number = 1),
        )
    }

    // Estado para mantener el id de la última carta seleccionada
    var lastSelectedCardId by remember { mutableStateOf(-1) }

    // Lógica para manejar el clic en la carta
    val onCardClick: (Carta) -> Unit = { clickedCard ->
        if (!clickedCard.isSelected && !clickedCard.isMatched) {
            // Marcar la carta como seleccionada
            clickedCard.isSelected = true

            // Si hay otra carta seleccionada, verificar si hacen match
            if (lastSelectedCardId != -1) {
                val lastSelectedCard = cards.find { it.id == lastSelectedCardId }
                if (lastSelectedCard != null && lastSelectedCard.number == clickedCard.number) {
                    // Si hacen match, marcar ambas como matched
                    lastSelectedCard.isMatched = true
                    clickedCard.isMatched = true
                } else {
                    // Si no hacen match, deseleccionar ambas después de un tiempo
                    GlobalScope.launch {
                        delay(1000) // Esperar un segundo antes de deseleccionar
                        lastSelectedCard?.isSelected = false
                        clickedCard.isSelected = false
                    }
                }
                // Reiniciar el id de la última carta seleccionada
                lastSelectedCardId = -1
            } else {
                // Si no hay otra carta seleccionada, actualizar el id de la última carta seleccionada
                lastSelectedCardId = clickedCard.id
            }
        }
    }

    Column(modifier = Modifier, verticalArrangement = Arrangement.Center) {
        // Mostrar las cartas en una cuadrícula (por ejemplo, en filas de a dos)
        for (i in cards.indices step 2) {
            Row {
                CardItem(card = cards[i], onClick = onCardClick)
                CardItem(card = cards.getOrNull(i + 1) ?: Carta(-1, "-1",-1), onClick = onCardClick)
            }
        }
    }
}


@Composable
fun SentenceCompletionList(onNextButtonClicked: () -> Unit) {
    var selectedOption1 by remember { mutableStateOf("") }
    var selectedOption2 by remember { mutableStateOf("") }
    var selectedOption3 by remember { mutableStateOf("") }
    var selectedOption4 by remember { mutableStateOf("") }
    var selectedOption5 by remember { mutableStateOf("") }
    var selectedOption6 by remember { mutableStateOf("") }
    var selectedOption7 by remember { mutableStateOf("") }
    var selectedOption8 by remember { mutableStateOf("") }
    var selectedOption9 by remember { mutableStateOf("") }
    var selectedOption10 by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SentenceCompletionItem(
            question = "¿____ vienes mañana a mi casa a tomar un ____.",
            options = listOf("Te", "Té", "te", "té"),
            selectedOption = selectedOption1,
            onOptionSelected = { selectedOption1 = it }
        )

        SentenceCompletionItem(
            question = "Le he dicho que ____ lo ____ al salir de clase",
            options = listOf("te", "té", "de", "dé"),
            selectedOption = selectedOption2,
            onOptionSelected = { selectedOption2 = it }
        )

        SentenceCompletionItem(
            question = "____ llegas con tiempo, ____ lo explico antes de empezar",
            options = listOf("Si", "Sí", "te", "té"),
            selectedOption = selectedOption2,
            onOptionSelected = { selectedOption2 = it }
        )

        SentenceCompletionItem(
            question = "____ ven mañana, y ____ hermano que venga pasado mañana",
            options = listOf("Tu", "Tú", "tu", "tú"),
            selectedOption = selectedOption2,
            onOptionSelected = { selectedOption2 = it }
        )

        SentenceCompletionItem(
            question = "¿____ lo has preguntado? Me ha dicho que ____",
            options = listOf("Se", "Sé", "si", "sí"),
            selectedOption = selectedOption2,
            onOptionSelected = { selectedOption2 = it }
        )

        Button(
            onClick = {
                onNextButtonClicked()
//                val correct1 = selectedOption1 == "Té"
//                val correct2 = selectedOption2 == "te"
//                if (correct1 && correct2) {
//                    // Respuestas correctas
//                    // showToast("2 respuestas correctas")
//                } else {
//                    // Respuestas incorrectas
//                    // Aquí puedes agregar la lógica adicional que desees al verificar las respuestas
//                    // Por ejemplo, mostrar un mensaje de error.
//                    // showToast("Una o más respuestas incorrectas")
//                }
            },
            enabled = true //selectedOption1.isNotEmpty() && selectedOption2.isNotEmpty()
        ) {
            Text("Verificar")
        }
    }
}

@Composable
private fun SentenceCompletionItem(
    question: String,
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(question)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            options.forEach { option ->
                RadioButton(
                    selected = (selectedOption == option),
                    onClick = { onOptionSelected(option) },
                )
                Text(text = option)
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewSentenceCompletionScreen() {
//    SentenceCompletionList()
//}


@Preview(showBackground = true)
@Composable
fun ActividadScreenPreview() {
    OrtografiaMariamelTheme {
        Actividad1(
            viewModel = viewModel(),
            onPrevButtonClicked = { /*TODO*/ },
            onNextButtonClicked = { /*TODO*/ })
    }
}