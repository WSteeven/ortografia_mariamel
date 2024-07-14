package com.example.ortografiamariamel.ui.screens

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ortografiamariamel.AppScreen
import com.example.ortografiamariamel.R
import com.example.ortografiamariamel.ui.theme.OrtografiaMariamelTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InicioScreen(
    modifier: Modifier = Modifier,
    onNextButtonClicked: () -> Unit = {},
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            AppTopBar(
                title =  AppScreen.Inicio.title,
                puedeNavegarAtras = false,
                modifier = modifier,
                mostrarEncabezado = false,
                mostrarMenu = false
            )
        }
    ) { innerPadding ->

        Column(modifier = modifier.padding(innerPadding)) {
            SlideInFromTopAnimation(
                R.drawable.logo_colegio, modifier = Modifier
                    .padding(16.dp)
                    .align(alignment = Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(16.dp))
            ContinuousSlideAnimation(
                modifier = Modifier
                    .fillMaxSize(.6f)
                    .align(alignment = Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Bienvenid@ a",
                modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
            )
            BasicMarqueeNombreApp()

            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = onNextButtonClicked,
                modifier = Modifier
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
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BasicMarqueeNombreApp() {
    // Marquee only animates when the content doesn't fit in the max width.
    Column(Modifier.width(400.dp)) {
        Text(
            buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 44.sp,
                        fontFamily = FontFamily.SansSerif
                    )
                ) { append("ORTOGRAFÍA ") }
                withStyle(
                    style = SpanStyle(
                        color = Color(240, 150, 44),
                        fontSize = 44.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.SansSerif
                    )
                ) {
                    append("MARIA")
                }
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        color = Color.Red,
                        fontSize = 44.sp,
                        fontFamily = FontFamily.SansSerif
                    )
                ) {
                    append("MEL")
                }
            },
            modifier = Modifier.basicMarquee(),
        )
    }
}

@Composable
fun ContinuousSlideAnimation(modifier: Modifier) {
    val infiniteTransition = rememberInfiniteTransition(label = "")

    // Horizontal scrolling animation from left to right
    val offsetX by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 3000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    Column {
        SlideInFromRightAnimation(offsetX, modifier)
    }
}

@Composable
private fun SlideInFromRightAnimation(offsetX: Float, modifier: Modifier) {
    val painter = painterResource(id = R.drawable.lapiz8)
    Column {
        Image(
            painter = painter,
            contentDescription = null,
            modifier = modifier.offset(x = (offsetX * 200).dp)
        )
    }
}

@Composable
fun SlideInFromTopAnimation(imageResId: Int, modifier: Modifier) {
    var isVisible by remember { mutableStateOf(false) }

    // Animation vertical center to Top of screen
    val offsetY by animateFloatAsState(
        targetValue = if (isVisible) 0f else 1f,
        animationSpec = tween(durationMillis = 2000, easing = FastOutSlowInEasing), label = ""
    )

    // When is visible, set isVisible = true
    LaunchedEffect(key1 = true) {
        isVisible = true
    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedImage(offsetY, imageResId)
    }
}

@Composable
private fun AnimatedImage(offsetY: Float, imageResId: Int) {
    val painter: Painter = painterResource(id = imageResId)

    Box(
        modifier = Modifier.offset(y = (offsetY * 50).dp)
    ) {
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier
                .size(100.dp) // Size of image
                .padding(8.dp)
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
