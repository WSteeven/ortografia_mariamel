package com.example.ortografiamariamel.ui.screens.unidad1

//noinspection UsingMaterialAndMaterial3Libraries

//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicText
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ortografiamariamel.R
import com.example.ortografiamariamel.repository.FirebaseRepository
import com.example.ortografiamariamel.service.SoundManager
import com.example.ortografiamariamel.ui.AppViewModel
import com.example.ortografiamariamel.ui.AppViewModelProvider
import com.example.ortografiamariamel.ui.screens.MenuLateral
import com.example.ortografiamariamel.ui.theme.OrtografiaMariamelTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random


@Composable
fun FallingWordsGame(onPrevButtonClicked: () -> Unit) {
    val firebase = FirebaseRepository(LocalContext.current)
    val configuration = LocalConfiguration.current
    val density = LocalDensity.current
//    val screenWidthPx = with(density) { configuration.screenWidthDp.dp.toPx() }
    val screenHeightPx = with(density) { configuration.screenHeightDp.dp.toPx() }

    val startX = with(density) { 10.dp.toPx() } // Fixed horizontal position

    var fallingWords by remember { mutableStateOf<List<FallingWord>>(listOf()) }
    var gameOver by remember { mutableStateOf(false) }
    var hearts by remember { mutableIntStateOf(3) }
    var correctCount by remember { mutableIntStateOf(0) }

    val soundManager = SoundManager(LocalContext.current)
    val words = listOf(
        "El no vino hoy.",
        "Esto es para mi.",
        "Se amable con todos.",
        "Aun no llegué.",
        "Quiero un te",
        "Se la verdad.",
        "Es para mi, no para mi hermana.",
        "Hoy es un buen día.",
        "El perro duerme tranquilo.",
        "Me gusta tu camisa.",
        "Salimos a caminar.",
        "Es mi libro favorito.",
        "No sé la respuesta",
        "Ayer llovió mucho.",
    )
//    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        while (!gameOver) {
            fallingWords = fallingWords + generateFallingWord(words, startX)
            delay(1000) // Adjust delay as needed
        }
    }

    if (gameOver) {
        Dialog(onDismissRequest = { /* Handle dialog dismissal */ }) {
            Surface(
                modifier = Modifier.padding(16.dp),
                shape = MaterialTheme.shapes.medium,
                color = MaterialTheme.colorScheme.background
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "¡Perdiste!",
                        style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    OutlinedButton(onClick = { onPrevButtonClicked() }) {
                        Text("Volver al Menú")
                    }
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Display hearts
        Row(
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.TopEnd)
        ) {
            repeat(3) { index ->
                HeartIcon(isFilled = index < hearts)
            }
        }

        // Display correct count
        Text(
            text = "Aciertos: $correctCount",
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.TopStart),
            style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold)
        )
        if(correctCount>5){
            firebase.actualizarProgreso(1, 3, correctCount)
        }

        fallingWords.forEach { fallingWord ->
            FallingWordView(
                fallingWord = fallingWord,
                screenHeightPx = screenHeightPx,
                onWordClicked = { word ->
                    if (isTildeDiacritic(word)) {
                        soundManager.playSound(R.raw.correct_card_sound)
                        correctCount++
                        if (correctCount % 3 == 0 && hearts < 3) {
                            hearts++
                        }
                        fallingWords = fallingWords - fallingWord
                    } else {
                        soundManager.playSound(R.raw.incorrect_card_sound)
                        hearts--
                        if (hearts <= 0) {
                            gameOver = true
                        }
                        fallingWords = fallingWords - fallingWord
                    }
                },
                onWordMissed = {
                    fallingWords = fallingWords - fallingWord
                }
            )
        }
    }
}

@Composable
fun FallingWordView(
    fallingWord: FallingWord,
    screenHeightPx: Float,
    onWordClicked: (String) -> Unit,
    onWordMissed: () -> Unit
) {
    val animatableY = remember { Animatable(0f) }
    val coroutineScope = rememberCoroutineScope()
    val word = fallingWord.word
    val position = fallingWord.position

    LaunchedEffect(fallingWord) {
        coroutineScope.launch {
            try {
                animatableY.animateTo(
                    targetValue = screenHeightPx - 100, // Avoid the word going off-screen
                    animationSpec = tween(durationMillis = 16000) // Increase duration as needed
                )
                onWordMissed() // Remove word if it reaches the bottom
            } catch (e: Exception) {
                // Handle exceptions if needed
            }
        }
    }

    BasicText(
        text = word,
        modifier = Modifier
            .offset(x = position.x.dp, y = (position.y + animatableY.value).dp)
            .clickable {
                onWordClicked(word)
            }
            .background(Color.LightGray)
            .padding(8.dp),
        style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold)
    )
}

fun generateFallingWord(words: List<String>, startX: Float): FallingWord {
    val word = words[Random.nextInt(words.size)]
    return FallingWord(word, Offset(startX, 0f)) // Fixed horizontal position
}

fun isTildeDiacritic(word: String): Boolean {
    val correctas = listOf(
        "El no vino hoy.",
        "Esto es para mi.",
        "Se amable con todos.",
        "Aun no llegué.",
        "Quiero un te",
        "Se la verdad.",
        "Es para mi, no para mi hermana."
    )
    return word in correctas
}

@Composable
fun HeartIcon(isFilled: Boolean) {
    Icon(
        imageVector = Icons.Filled.Favorite,
        contentDescription = null,
        tint = if (isFilled) Color.Red else Color.Gray,
        modifier = Modifier.size(24.dp)
    )
}

data class FallingWord(val word: String, val position: Offset)

@Composable
fun Actividad3U1(
    viewModel: AppViewModel,
    modifier: Modifier = Modifier,
    onPrevButtonClicked: () -> Unit,
    onItemMenuButtonClicked: () -> Unit
) {

    MenuLateral(
        title = R.string.blank,
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
                            .fillMaxWidth(.4f)
                            .fillMaxHeight(.07f)
                    ) {
                        LottieAnimationA2U1Screen()
                    }
                }

                Text(
                    text = "Señale las oraciones que necesitan la tilde diacrítica.",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Justify,
                    fontFamily = FontFamily.SansSerif,
                    letterSpacing = 1.sp,
                    color = Color(230, 170, 75),
                    modifier = modifier
                        .align(Alignment.CenterHorizontally)
                )

                // Aquí va el contenido
                FallingWordsGame(onPrevButtonClicked = onPrevButtonClicked)

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
                        OutlinedButton(
                            modifier = Modifier.weight(1f),
                            onClick = onPrevButtonClicked
                        ) {
                            Text(stringResource(R.string.atras))
                        }
                    }
                }
            }
        },
        viewModel = viewModel,
        onItemMenuButtonClicked = onItemMenuButtonClicked,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun Actividad3ScreenPreview() {
    OrtografiaMariamelTheme {
        Actividad3U1(
            viewModel = viewModel(factory = AppViewModelProvider.Factory),
            onPrevButtonClicked = { /*TODO*/ },
            onItemMenuButtonClicked = {})
    }
}









