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
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import com.example.ortografiamariamel.ui.AppViewModel
import com.example.ortografiamariamel.ui.theme.OrtografiaMariamelTheme

@Composable
fun Actividad1(
    viewModel: AppViewModel,
    onPrevButtonClicked: () -> Unit,
    onNextButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    val imagen = painterResource(R.drawable.actividad_unidad1)
    Column(modifier = modifier
        .padding(top = 16.dp)
        .padding(horizontal = 8.dp)) {
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
fun SentenceCompletionList(onNextButtonClicked:()->Unit) {
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