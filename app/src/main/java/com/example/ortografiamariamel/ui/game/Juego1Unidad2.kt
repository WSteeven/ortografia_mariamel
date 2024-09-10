package com.example.ortografiamariamel.ui.game

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CrosswordGrid(cells: List<List<String>>, onCellValueChange: (Int, Int, String) -> Unit) {
    Column {
        cells.forEachIndexed { rowIndex, row ->
            Row {
                row.forEachIndexed { colIndex, cell ->
                    CrosswordCell(
                        value = cell,
                        onValueChange = { newValue ->
                            onCellValueChange(rowIndex, colIndex, newValue)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun CrosswordCell(value: String, onValueChange: (String) -> Unit) {
    var text by remember { mutableStateOf(value) }

    Box(
        modifier = Modifier
            .size(40.dp)
            .border(1.dp, Color.Black)
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        BasicTextField(
            value = text,
            onValueChange = { newValue ->
                if (newValue.length <= 1) {
                    text = newValue
                    onValueChange(newValue)
                }
            },
            textStyle = TextStyle(fontSize = 18.sp, textAlign = TextAlign.Center)
        )
    }
}

@Composable
fun CrosswordApp() {
    val cells = remember {
        mutableStateOf(
            listOf(
                listOf("", "", "A", "P", "P", "L", "E"),
                listOf("", "", "", "", "", "", ""),
                listOf("B", "A", "N", "A", "N", "A", ""),
                listOf("", "", "", "", "", "", "")
            )
        )
    }

    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            CrosswordGrid(
                cells = cells.value,
                onCellValueChange = { rowIndex, colIndex, newValue ->
                    val newCells = cells.value.toMutableList()
                    val row = newCells[rowIndex].toMutableList()
                    row[colIndex] = newValue
                    newCells[rowIndex] = row
                    cells.value = newCells
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CrosswordApp()
}

