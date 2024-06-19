package com.example.ortografiamariamel.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ortografiamariamel.R
import com.example.ortografiamariamel.ui.theme.OrtografiaMariamelTheme

@Composable
fun MenuScreen(
    onPrevButtonClicked: () -> Unit,
    onNextButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    var showButton by remember { mutableStateOf(true) }
    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                Text("Menu", modifier = Modifier.padding(16.dp))
                Divider()
                NavigationDrawerItem(
                    label = { Text(text = "Drawer Item") },
                    selected = false,
                    onClick = { /*TODO*/ }
                )
                NavigationDrawerItem(
                    label = { Text(text = "Drawer Item 2") },
                    selected = false,
                    onClick = { /*TODO*/ }
                )
                NavigationDrawerItem(
                    label = { Text(text = "Drawer Item 3") },
                    selected = false,
                    onClick = { /*TODO*/ }
                )
                NavigationDrawerItem(
                    label = { Text(text = "Drawer Item 4") },
                    selected = false,
                    onClick = { /*TODO*/ }
                )
                // ...other drawer items
            }
        }
    ) {
        // Screen content

        Column(
            modifier = modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (showButton) {
                ButtonComponent(onClick = { showButton = !showButton })
            }
            Image(
                painter = painterResource(id = R.drawable.caratula_lengua_literatura),
                contentDescription = null,
                modifier = Modifier.size(300.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier) {
                Box(
                    modifier = Modifier
                        .size(150.dp)
                        .padding(8.dp)
                        .width(100.dp)
                        .height(50.dp)
                        .clickable(onClick = onNextButtonClicked)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.unidad1),
                        contentDescription = "Unidad 1",
                    )
                }

                Box(
                    modifier = Modifier
                        .size(150.dp)
                        .padding(8.dp)
                        .width(100.dp)
                        .height(50.dp)
                        .clickable(onClick = { })
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.unidad2),
                        contentDescription = "Unidad 2"
                    )

                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                Box(
                    modifier = Modifier
                        .size(150.dp)
                        .padding(8.dp)
                        .width(100.dp)
                        .height(50.dp)
                        .clickable(onClick = { })
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.unidad3),
                        contentDescription = "Unidad 3",
                    )
                }


                Box(
                    modifier = Modifier
                        .size(150.dp)
                        .padding(8.dp)
                        .width(100.dp)
                        .height(50.dp)
                        .clickable(onClick = { })
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.unidad4),
                        contentDescription = "Unidad 4"
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
                    .fillMaxWidth()
            ) {
//            Boton Atrás
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .padding(8.dp)
                        .width(100.dp)
                        .height(50.dp)
                        .clickable(onClick = { onPrevButtonClicked() })
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.back),
                        contentDescription = "Unidad 4"
                    )
                }
            }
        }
    }
}

@Composable
fun ButtonComponent(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        IconButton(onClick = onClick) {
            Icon(
                imageVector = Icons.Filled.Menu,
                contentDescription = "Menú"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MenuScreenPreview() {
    OrtografiaMariamelTheme {
        MenuScreen({}, {}, modifier = Modifier.fillMaxSize())
    }
}

