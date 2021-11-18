package com.sanmidev.confamgbedu.ui.gbedu

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import com.airbnb.mvrx.compose.collectAsState
import com.airbnb.mvrx.compose.mavericksViewModel
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarStyle


@Composable
fun GbeduScreen(id: GbeduId, onBackPressed: () -> Unit) {
    val viewModel: GbeduViewModel = mavericksViewModel()
    val gbeduState by viewModel.collectAsState()
    val context = LocalContext.current
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        InputField(name = gbeduState.name, "Name") {
            viewModel.updateGbeduName(it)
        }
        InputField(name = gbeduState.artistName, "Artist Name") {
            viewModel.updateArtistName(it)
        }
        RatingBar(value = gbeduState.rating.value,
            ratingBarStyle = RatingBarStyle.HighLighted, onValueChange = {
                viewModel.updateGbeduRating(it)
            }, numStars = 10
        ) {
        }
        SubmitButton(isValid = gbeduState.isValid) {
            Toast.makeText(context, "$gbeduState", Toast.LENGTH_SHORT).show()
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
