package com.example.ortografiamariamel.ui.screens.unidad1

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ortografiamariamel.ui.theme.OrtografiaMariamelTheme


@Composable
fun EnergyBar(energy: Int = 5, modifier: Modifier = Modifier) {
//    var energy by remember { mutableIntStateOf(energias) } // Energía inicial
    Box {
        EnergyJar(energy)
        Text(
            " Mis energías: $energy",
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun EnergyJar(energy: Int) {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(25.dp)
    ) {
        drawEnergyJar(energy)
    }
}

fun DrawScope.drawEnergyJar(energy: Int) {
    val totalEnergy = 5
    val fillWidth = (size.width * (energy / totalEnergy.toFloat())).coerceIn(0f, size.width)

    // Dibuja el frasco
    drawRoundRect(
        color = Color.LightGray,
        size = size.copy(width = size.width, height = size.height),
        cornerRadius = CornerRadius(10f, 10f)
    )

    // Dibuja el nivel de energía
    drawRoundRect(
        color = Color.Green,
        size = size.copy(width = fillWidth, height = size.height),
        topLeft = Offset(size.height - size.height, 0f),
        cornerRadius = CornerRadius(10f, 10f)
    )
}


@Preview(showBackground = true, widthDp = 305)
@Composable
fun EnergyBarPreview() {
    OrtografiaMariamelTheme {
        EnergyBar()
    }
}
