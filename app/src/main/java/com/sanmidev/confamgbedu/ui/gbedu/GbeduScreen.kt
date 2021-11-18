package com.sanmidev.confamgbedu.ui.gbedu

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.sanmidev.confamgbedu.domain.model.GbeduId
import androidx.compose.runtime.getValue
import com.airbnb.mvrx.compose.collectAsState
import com.airbnb.mvrx.compose.mavericksViewModel


@Composable
fun GbeduScreen(id: GbeduId, onBackPressed: () -> Unit) {
    val viewModel: GbeduViewModel = mavericksViewModel()
    val gbeduState by viewModel.collectAsState()
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        InputField(name = gbeduState.name, "Name") {
            viewModel.updateGbeduName(it)
        }
        InputField(name = gbeduState.artistName, "Artist Name") {
            viewModel.updateArtistName(it)
        }
        SubmitButton(isValid = gbeduState.isValid) {

        }
    }

}

@Composable
fun InputField(name: String, label: String, onValueChanged: (String) -> Unit) {
    OutlinedTextField(
        value = name,
        onValueChange = onValueChanged,
        Modifier.fillMaxWidth(),
        label = {
            Text(
                text = label
            )
        })
}

@Composable
fun SubmitButton(isValid: Boolean, onClick: () -> Unit) {
    Button(onClick = { onClick() }, enabled = isValid) {
        Text(text = "Submit")
    }
}
