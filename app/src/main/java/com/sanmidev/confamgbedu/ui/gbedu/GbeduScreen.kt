package com.sanmidev.confamgbedu.ui.gbedu

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.sanmidev.confamgbedu.domain.model.GbeduId

@Composable
fun GbeduScreen(id: GbeduId, onBackPressed: () -> Unit) {
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        InputField(name = "Name") {

        }
        InputField(name = "ArtistName") {}
    }

}

@Composable
fun InputField(name: String, onValueChanged: (String) -> Unit) {
    OutlinedTextField(value = name, onValueChange = onValueChanged, Modifier.fillMaxWidth())
}
