package com.sanmidev.confamgbedu.ui.gbedu

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.sanmidev.confamgbedu.domain.model.GbeduId

@Composable
fun GbeduScreen(id: GbeduId, onBackPressed: () -> Unit) {
    Button(onClick = { onBackPressed() }) {
        Text("Go back ${id.value}")
    }
}