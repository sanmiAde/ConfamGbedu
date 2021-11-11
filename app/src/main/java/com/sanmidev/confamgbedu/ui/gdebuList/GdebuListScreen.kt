package com.sanmidev.confamgbedu.ui.gdebuList

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.sanmidev.confamgbedu.ui.theme.ConfamGbeduTheme

@Composable
fun GdebuListScreen() {
    Greeting(name = "Hello")
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ConfamGbeduTheme {
        Greeting("Android")
    }
}