package com.sanmidev.confamgbedu.ui.gbedu

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun GbeduScreen(onBackPressed: () -> Unit) {
    Button(onClick = { onBackPressed() }) {
        Text("Go back")
    }
}