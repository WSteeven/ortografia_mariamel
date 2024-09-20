package com.example.ortografiamariamel.ui.game

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CrosswordGrid(
    cells: List<List<CellState>>,
    onCellValueChange: (Int, Int, String) -> Unit,
    onCellFocused: (Int, Int) -> Unit,
) {
    Column(modifier = Modifier.horizontalScroll(rememberScrollState())) {
        cells.forEachIndexed { rowIndex, row ->
            Row {
                row.forEachIndexed { colIndex, cell ->
                    CrosswordCell(
                        state = cell,
                        onValueChange = { newValue ->
                            onCellValueChange(rowIndex, colIndex, newValue)
                        },
                        onFocused = { onCellFocused(rowIndex, colIndex) },
                    )
                }
            }
        }
    }
//    }
}

@Composable
fun CrosswordCell(
    state: CellState,
    onValueChange: (String) -> Unit,
    onFocused: () -> Unit,
) {
    var text by remember { mutableStateOf(state.value) }
    val borderWidth by animateDpAsState(
        targetValue = if (state.isFocused) 2.dp else 1.dp,
        label = "ancho de borde"
    )
    val borderColor by animateColorAsState(
        targetValue = if (state.isFocused) Color.Blue else Color.Black,
        label = "color de borde"
    )


    Box(
        modifier = Modifier
            .size(40.dp)
            .border(borderWidth, borderColor)
            .padding(8.dp)
            .background(if (state.isActive) Color.Transparent else Color(240, 150, 55))
            .clickable { onFocused() },
        contentAlignment = Alignment.Center
    ) {
        if (state.isActive) {
            BasicTextField(
                value = text,
                onValueChange = { newValue ->
                    if (newValue.length <= 1) {
                        text = newValue
                        onValueChange(newValue)
                    }
                },
                textStyle = TextStyle(
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                    color = Color.Black
                ),
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Characters,
                    keyboardType = KeyboardType.Text, imeAction = ImeAction.Next
                )
            )
        } else {
            Text(
                text = state.value,
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                color = Color.Black
            )
        }
    }
}

data class CellState(
    val value: String,
    val isActive: Boolean,
    val isFocused: Boolean
)

@Composable
fun CrosswordApp() {
    val cells = remember {
        mutableStateOf(
            List(15) { row ->
                List(15) { col ->
                    CellState(
                        value = "",
                        isActive = isCellActive(row, col), //Define tus celdas activas
                        isFocused = false
                    )
                }
            }
        )
    }
    var showDialog by remember { mutableStateOf<Pair<Boolean, String>?>(null) }
    var focusedCell by remember { mutableStateOf<Pair<Int, Int>?>(null) }

    fun onVerifyClick() {
        val allCorrect = verifyCrossword(cells.value)
        showDialog = if (allCorrect) {
            Pair(true, "!Todo está correcto!")
        } else {
            Pair(false, "¡Incorrecto! Hay errores en el crucigrama!")
        }
    }

    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column {
                Text(text = "Pistas", fontWeight = FontWeight.Bold)
                Row {
                    Icon(
                        imageVector = Icons.Filled.ArrowUpward,
                        contentDescription = "Vertical de abajo a arriba"
                    )
                    Text(text = " Proceso de nacer. ")
                }
                Row {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = "horizontal"
                    )
                    Text(text = " Expresión de gratitud.")
                }
                Row {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = "horizontal"
                    )
                    Text(text = " Proceso de conocer o hacerse conocido.")
                }
                Row {
                    Icon(
                        imageVector = Icons.Filled.ArrowDownward,
                        contentDescription = "horizontal"
                    )
                    Text(text = " Acto de construir o crecer. ")
                }

                Row {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = "horizontal"
                    )
                    Text(text = " Evento, hecho o suceso.")
                }
                CrosswordGrid(
                    cells = cells.value,
                    onCellValueChange = { rowIndex, colIndex, newValue ->
                        val newCells = cells.value.mapIndexed { rowIdx, row ->
                            row.mapIndexed { colIdx, cell ->
                                if (rowIdx == rowIndex && colIdx == colIndex) {
                                    cell.copy(value = newValue)
                                } else {
                                    cell
                                }
                            }
                        }
                        cells.value = newCells
                    },
                    onCellFocused = { rowIndex, colIndex ->
                        val newCells = cells.value.mapIndexed { rowIdx, row ->
                            row.mapIndexed { colIdx, cell ->
                                cell.copy(isFocused = rowIdx == rowIndex && colIdx == colIndex)
                            }
                        }
                        cells.value = newCells
                        focusedCell = rowIndex to colIndex
                    }
                )
                Spacer(modifier = Modifier.padding(8.dp))
                Button(
                    onClick = { onVerifyClick() },
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    border = BorderStroke(4.dp, Color(244, 225, 220)),
                    shape = MaterialTheme.shapes.extraLarge,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(240, 150, 55), contentColor = Color.White
                    )
                ) {
                    Text("Verificar", fontSize = 30.sp)
                }
            }
        }
    }

    showDialog?.let { (isCorrect, message) ->
        AlertDialog(onDismissRequest = { showDialog = null },
            title = { Text("Resultado") },
            text = { Text(message) },
            confirmButton = {
                Button(onClick = { showDialog = null }) {
                    Text("OK")
                }
            })
    }
}

fun isCellActive(row: Int, col: Int): Boolean {
    // Define the specific active cells based on the pattern you provided
    val activeCells = setOf(
        // define en base a (fila y columna)
        Pair(1, 5),
        Pair(2, 5),

        Pair(3, 0),
        Pair(3, 1),
        Pair(3, 2),
        Pair(3, 3),
        Pair(3, 4),
        Pair(3, 5),
        Pair(3, 6),
        Pair(3, 7),
        Pair(3, 8),
        Pair(3, 9),
        Pair(3, 10),
        Pair(3, 11),
        Pair(3, 12),
        Pair(3, 13),

        // Column for "AGRADECIMIENTO"
        Pair(4, 5),

        Pair(5, 5),
        Pair(5, 9),

        Pair(6, 5),
        Pair(6, 9),

        Pair(7, 0),
        Pair(7, 1),
        Pair(7, 2),
        Pair(7, 3),
        Pair(7, 4),
        Pair(7, 5),
        Pair(7, 6),
        Pair(7, 7),
        Pair(7, 8),
        Pair(7, 9),
        Pair(7, 10),
        Pair(7, 11),

        Pair(8, 5),
        Pair(8, 9),

        Pair(9, 5),
        Pair(9, 9),
        // Column for "R"
        Pair(10, 1),
        Pair(10, 2),
        Pair(10, 3),
        Pair(10, 4),
        Pair(10, 5),
        Pair(10, 6),
        Pair(10, 7),
        Pair(10, 8),
        Pair(10, 9),
        Pair(10, 10),
        Pair(10, 11),
        Pair(10, 12),
        Pair(10, 13),
        Pair(10, 14),

        Pair(11, 5),
        Pair(11, 9),
        Pair(12, 9),
        Pair(13, 9),
        Pair(14, 9),
    )

    return Pair(row, col) in activeCells
}

val correctAnswers = mapOf(
    Pair(1, 5) to "C",
    Pair(2, 5) to "R",
    Pair(3, 0) to "A",
    Pair(3, 1) to "G",
    Pair(3, 2) to "R",
    Pair(3, 3) to "A",
    Pair(3, 4) to "D",
    Pair(3, 5) to "E",
    Pair(3, 6) to "C",
    Pair(3, 7) to "I",
    Pair(3, 8) to "M",
    Pair(3, 9) to "I",
    Pair(3, 10) to "E",
    Pair(3, 11) to "N",
    Pair(3, 12) to "T",
    Pair(3, 13) to "O",
    Pair(4, 5) to "C",
    Pair(5, 5) to "I",
    Pair(5, 9) to "O",
    Pair(6, 5) to "M",
    Pair(6, 9) to "T",
    Pair(7, 0) to "C",
    Pair(7, 1) to "O",
    Pair(7, 2) to "N",
    Pair(7, 3) to "O",
    Pair(7, 4) to "C",
    Pair(7, 5) to "I",
    Pair(7, 6) to "M",
    Pair(7, 7) to "I",
    Pair(7, 8) to "E",
    Pair(7, 9) to "N",
    Pair(7, 10) to "T",
    Pair(7, 11) to "O",
    Pair(8, 5) to "E",
    Pair(8, 9) to "E",
    Pair(9, 5) to "N",
    Pair(9, 9) to "I",
    Pair(10, 1) to "A",
    Pair(10, 2) to "C",
    Pair(10, 3) to "O",
    Pair(10, 4) to "N",
    Pair(10, 5) to "T",
    Pair(10, 6) to "E",
    Pair(10, 7) to "C",
    Pair(10, 8) to "I",
    Pair(10, 9) to "M",
    Pair(10, 10) to "I",
    Pair(10, 11) to "E",
    Pair(10, 12) to "N",
    Pair(10, 13) to "T",
    Pair(10, 14) to "O",
    Pair(11, 5) to "O",
    Pair(11, 9) to "I",
    Pair(12, 9) to "C",
    Pair(13, 9) to "A",
    Pair(14, 9) to "N",
)

fun verifyCrossword(cells: List<List<CellState>>): Boolean {
    var allCorrect = true
    val incorrectCells = mutableMapOf<Pair<Int, Int>, Color>()

    cells.forEachIndexed { rowIndex, row ->
        row.forEachIndexed { colIndex, cell ->
            if (cell.isActive) {
                val correctAnswer = correctAnswers[Pair(rowIndex, colIndex)]
                if (cell.value != correctAnswer) {
                    allCorrect = false
                    incorrectCells[Pair(rowIndex, colIndex)] =
                        Color.Red //Marca las celdas incorrectas
                }
            }
        }
    }
    return allCorrect
}

@Preview(showBackground = true, widthDp = 600)
@Composable
fun DefaultPreview() {
    CrosswordApp()
}

