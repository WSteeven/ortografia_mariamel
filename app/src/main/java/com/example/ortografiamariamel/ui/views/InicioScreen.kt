package com.example.ortografiamariamel.ui.views

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ortografiamariamel.R
import com.example.ortografiamariamel.ui.theme.OrtografiaMariamelTheme

@Composable
fun InicioScreen(
    modifier: Modifier = Modifier,
    onNextButtonClicked: () -> Unit = {},
) {
    val imageColegio = painterResource(R.drawable.logo_colegio)
    val imageLogo = painterResource(R.drawable.lapiz8)

    Column(modifier = modifier) {
        Image(
            painter = imageColegio,
            contentDescription = null,
            modifier = Modifier
                .padding(16.dp)
                .align(alignment = Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(32.dp))
        SlideInFromRightAnimation(
            Modifier
                .fillMaxSize(.6f)
                .padding(16.dp)
                .align(alignment = Alignment.CenterHorizontally)
        )
//        Image(
//            painter = imageLogo,
//            contentDescription = null,
//            modifier = Modifier
//                .fillMaxSize(.6f)
//                .padding(16.dp)
//                .align(alignment = Alignment.CenterHorizontally)
//        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Bienvenid@ a",

            modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
        )
        Text(
            text = "ORTOGRAFIA",

            modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif
            )
        )
        Row(
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
        ) {
            Text(
                text = "MARIA",

                modifier = Modifier.background(color = Color.White),
                style = TextStyle(
                    color = Color(240, 150, 44),
                    fontSize = 44.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif
                )
            )
            Text(
                text = "MEL",

                modifier = Modifier.background(color = Color.White),
                style = TextStyle(
                    color = Color.Red,
                    fontSize = 44.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif
                )
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = onNextButtonClicked,
            modifier = Modifier
//                .height(64.dp)
                .align(Alignment.CenterHorizontally),
            border = BorderStroke(4.dp, Color(244, 225, 220)),
            shape = MaterialTheme.shapes.extraLarge,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(240, 150, 55), contentColor = Color.White
            ),
        ) {
            Text(text = "Comenzar", fontSize = 30.sp)
        }

    }
}

@Composable
fun SlideInFromRightAnimation(modifier: Modifier) {
    var isVisible by remember { mutableStateOf(false) }

    // Anima el desplazamiento horizontal de la imagen
    val offsetX by animateDpAsState(
        targetValue = if (isVisible) 0.dp else 200.dp,
        animationSpec = tween(durationMillis = 1000)
    )

    // Cuando se hace visible, establece isVisible en true
    LaunchedEffect(key1 = true) {
        isVisible = true
    }

    Column {
        Image(
            painter = painterResource(id = R.drawable.lapiz8), // Reemplaza con tu imagen
            contentDescription = null, // Puedes agregar una descripción accesible aquí
            modifier = modifier.offset(x = offsetX)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun InicioScreenPreview() {
    OrtografiaMariamelTheme {
        InicioScreen()
    }
}
