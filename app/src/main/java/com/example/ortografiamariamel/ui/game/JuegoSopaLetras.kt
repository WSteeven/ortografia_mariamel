package com.example.ortografiamariamel.ui.game

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ortografiamariamel.repository.FirebaseRepository
import com.example.ortografiamariamel.ui.theme.OrtografiaMariamelTheme
import kotlin.random.Random

@Composable
fun SopaDeLetras() {
    val firebase = FirebaseRepository(LocalContext.current)
    val palabras = listOf("OFREZCO", "EMBELLEZCO", "REDUZCO", "CAREZCO", "FORTALEZCO", "APETEZCA")
    val gridSize = 12
    // Estado de la cuadrícula
    val grid = remember { generateGrid(palabras, gridSize) }
    var selectedCells by remember { mutableStateOf(setOf<Pair<Int, Int>>()) }
    var showDialog by remember { mutableStateOf(false) }
    var message by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp), horizontalAlignment = Alignment.Start
        ) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
                Row(modifier = Modifier.fillMaxWidth(.5f)) {
                    Text("- ", fontWeight = FontWeight.Bold)
                    Text(text = "OFREZCO")
                }
                Row {
                    Text("- ", fontWeight = FontWeight.Bold)
                    Text(text = "EMBELLEZCO")
                }
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
                Row(modifier = Modifier.fillMaxWidth(.5f)) {
                    Text("- ", fontWeight = FontWeight.Bold)
                    Text(text = "REDUZCO")
                }
                Row {
                    Text("- ", fontWeight = FontWeight.Bold)
                    Text(text = "CAREZCO")
                }
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
                Row(modifier = Modifier.fillMaxWidth(.5f)) {
                    Text("- ", fontWeight = FontWeight.Bold)
                    Text(text = "FORTALEZCO")
                }
                Row {
                    Text("- ", fontWeight = FontWeight.Bold)
                    Text(text = "APETEZCA")
                }
            }

        }
        Column(modifier = Modifier.horizontalScroll(rememberScrollState())) {
            for (i in 0 until gridSize) {
                Row {
                    for (j in 0 until gridSize) {
                        val letra = grid[i][j]
                        val isSelected = Pair(i, j) in selectedCells
                        Box(
                            modifier = Modifier
                                .size(30.dp)
                                .border(1.dp, Color.Black)
                                .background(
                                    if (isSelected) Color(
                                        240,
                                        150,
                                        55
                                    ) else Color.Transparent
                                ) //aqui se define el color de la celda
                                .clickable {
                                    if (letra.isNotEmpty()) {
                                        selectedCells = if (isSelected)
                                            selectedCells - Pair(i, j)
                                        else selectedCells + Pair(i, j)
                                    }
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = letra,
                                fontSize = 18.sp,
                                color = if (isSelected) Color.White else Color.Black // aqui se define el color de la letra
                            )
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.padding(8.dp))
        Button(
            onClick = {
//            val foundWords = verificarPalabrasEncontradas(selectedCells, palabras, grid)
                val foundWords = verificarPalabrasEncontradasEasy(selectedCells, palabras, grid)
//            message = "Palabras encontradas: $foundWords de ${palabras.size}"
                if (foundWords) {
                    message =     "¡Felicitaciones! Has encontrado todas las palabras."
                    firebase.actualizarProgreso(3,1, 6)
                } else {
                    message = "Aún faltan palabras por completar."
                }
                showDialog = true
            },
            border = BorderStroke(4.dp, Color(244, 225, 220)),
            shape = MaterialTheme.shapes.extraLarge,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(240, 150, 55), contentColor = Color.White
            )
        ) {
            Text("Verificar", fontSize = 20.sp)
        }

        // Diálogo de resultado
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("Resultado") },
                text = { Text(message) },
                confirmButton = {
                    Button(onClick = { showDialog = false }) {
                        Text("OK")
                    }
                }
            )
        }
    }
}

fun verificarPalabrasEncontradasEasy(
    selectedCells: Set<Pair<Int, Int>>,
    palabras: List<String>,
    grid: List<List<String>>
): Boolean {
    // Verificar si todas las palabras han sido encontradas
    return palabras.all { palabra ->
        palabra.all { letra ->
            selectedCells.any {
                grid[it.first][it.second].equals(
                    letra.toString(),
                    ignoreCase = true
                )
            }
        }
    }
}

//fun verificarPalabrasEncontradas(
//    selectedCells: Set<Pair<Int, Int>>,
//    palabras: List<String>,
//    grid: List<List<String>>
//): Int {
//    var foundCount = 0
//
//    palabras.forEach { palabra ->
//        val letras = palabra.toList()
//        val posiciones = mutableListOf<Pair<Int, Int>>()
//
//        // Obtener las posiciones de cada letra en las celdas seleccionadas
//        letras.forEach { letra ->
//            val foundPosition = selectedCells.firstOrNull {
//                grid[it.first][it.second].equals(
//                    letra.toString(),
//                    ignoreCase = true
//                )
//            }
//            if (foundPosition != null) {
//                posiciones.add(foundPosition)
//            }
//        }
//
//        // Comprobar si todas las letras fueron encontradas
//        if (posiciones.size == letras.size) {
//            // Ordenar posiciones por fila y columna
//            posiciones.sortWith(compareBy({ it.first }, { it.second }))
//
//            // Comprobar si son consecutivas en alguna dirección
//            val (rowStart, colStart) = posiciones[0]
//            val isHorizontal = posiciones.zipWithNext()
//                .all { (p1, p2) -> p1.first == p2.first && p1.second + 1 == p2.second }
//            val isVertical = posiciones.zipWithNext()
//                .all { (p1, p2) -> p1.second == p2.second && p1.first + 1 == p2.first }
//            val isDiagonalDescendente = posiciones.zipWithNext()
//                .all { (p1, p2) -> p1.first + 1 == p2.first && p1.second + 1 == p2.second }
//            val isDiagonalAscendente = posiciones.zipWithNext()
//                .all { (p1, p2) -> p1.first + 1 == p2.first && p1.second - 1 == p2.second }
//
//            if (isHorizontal || isVertical || isDiagonalDescendente || isDiagonalAscendente) {
//                foundCount++
//            }
//        }
//    }
//
//    return foundCount
//}


fun generateGrid(palabras: List<String>, gridSize: Int): List<List<String>> {
    val grid = MutableList(gridSize) { MutableList(gridSize) { "" } }
    colocarPalabras(palabras, grid)
    rellenarConLetrasAleatorias(grid)
    return grid.map { it.toList() } // Convierte a lista inmutable
}

// Función para colocar las palabras aleatoriamente
fun colocarPalabras(palabras: List<String>, grid: MutableList<MutableList<String>>) {
    palabras.forEach { palabra ->
        var placed = false
        while (!placed) {
            val direction = Random.nextInt(0, 3) // 0: Horizontal, 1: Vertical, 2: Diagonal
            val row = Random.nextInt(0, grid.size)
            val col = Random.nextInt(0, grid.size)

            if (puedeColocar(grid, palabra, row, col, direction)) {
                for (i in palabra.indices) {
                    when (direction) {
                        0 -> grid[row][col + i] = palabra[i].toString() // Horizontal
                        1 -> grid[row + i][col] = palabra[i].toString() // Vertical
                        2 -> grid[row + i][col + i] = palabra[i].toString() // Diagonal
                    }
                }
                placed = true
            }
        }
    }
}

fun puedeColocar(
    grid: MutableList<MutableList<String>>,
    palabra: String,
    row: Int,
    col: Int,
    direction: Int
): Boolean {
    return when (direction) {
        0 -> col + palabra.length <= grid.size && palabra.indices.all { grid[row][col + it].isEmpty() } // Horizontal
        1 -> row + palabra.length <= grid.size && palabra.indices.all { grid[row + it][col].isEmpty() } // Vertical
        2 -> row + palabra.length <= grid.size && col + palabra.length <= grid.size && palabra.indices.all { grid[row + it][col + it].isEmpty() } // Diagonal
        else -> false
    }
}

fun rellenarConLetrasAleatorias(grid: MutableList<MutableList<String>>) {
    val letras = ('A'..'Z').toList()
    for (i in grid.indices) {
        for (j in grid[i].indices) {
            if (grid[i][j].isEmpty()) {
                grid[i][j] = letras.random().toString()
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 300)
@Composable
fun JuegoSopaLetrasPreview() {
    OrtografiaMariamelTheme {
        SopaDeLetras()
    }
}
