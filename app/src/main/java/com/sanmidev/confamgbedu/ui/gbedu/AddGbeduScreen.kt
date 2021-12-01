package com.sanmidev.confamgbedu.ui.gbedu

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.airbnb.mvrx.compose.collectAsState
import com.airbnb.mvrx.compose.mavericksViewModel
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarStyle
import com.sanmidev.confamgbedu.domain.model.ReleaseType


@Composable
fun AddGbeduScreen(onBackPressed: () -> Unit) {
    val viewModel: AddGbeduViewModel = mavericksViewModel()
    val gbeduState by viewModel.collectAsState()

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        InputField(name = gbeduState.name, "Name") {
            viewModel.updateGbeduName(it)
        }
        InputField(name = gbeduState.artistName, "Artist Name") {
            viewModel.updateArtistName(it)
        }
        RatingBar(
            value = gbeduState.rating.value,
            ratingBarStyle = RatingBarStyle.HighLighted, onValueChange = {
                viewModel.updateGbeduRating(it)
            }, numStars = 10
        ) {
        }
        ReleaseTypeDropDown(
            releaseTypes = gbeduState.availableReleaseTypes,
            selectedReleaseType = gbeduState.selectedReleaseType
        ) {
            viewModel.updateGbeduReleaseType(releaseType = it)
        }
        SubmitButton(isValid = gbeduState.isValid) {
            viewModel.storeGbedu(gbeduState)
        }
    }


}


@Composable
fun InputField(name: String, label: String, onValueChanged: (String) -> Unit) {
    OutlinedTextField(
        value = name,
        onValueChange = onValueChanged,
        Modifier.fillMaxWidth(),
        label = { Text(text = label) })
}

@Composable
fun SubmitButton(isValid: Boolean, onClick: () -> Unit) {
    Button(onClick = { onClick() }, enabled = isValid) {
        Text(text = "Submit")
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ReleaseTypeDropDown(
    selectedReleaseType: ReleaseType,
    releaseTypes: List<ReleaseType>,
    onReleaseTypeClicked: (ReleaseType) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(selectedReleaseType) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        TextField(
            readOnly = true,
            value = selectedOptionText.toString(),
            onValueChange = { },
            label = { Text("Label") },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {
            releaseTypes.forEach { selectionOption ->
                DropdownMenuItem(
                    onClick = {
                        selectedOptionText = selectionOption
                        expanded = false
                        onReleaseTypeClicked(selectionOption)
                    }
                ) {
                    Text(text = selectionOption.toString())
                }
            }
        }
    }
}
