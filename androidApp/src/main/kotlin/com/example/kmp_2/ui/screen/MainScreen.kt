package com.example.kmp_2.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

@Composable
fun MainScreen() {
    var isDetail by remember { mutableStateOf(false) }
    if (isDetail) {
        DetailScreen()
    } else {
        Column {
            Text(text = "Home", color = Color.Blue)
            Button(onClick = { isDetail = true }) {
                Text("Go Detail 2")
            }
        }
    }
}

@Composable
fun DetailScreen() {
    Text("Detail Screen")
}
