package com.sanmidev.confamgbedu.ui.gdebuList

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.airbnb.mvrx.compose.collectAsState
import com.airbnb.mvrx.compose.mavericksViewModel
import com.airbnb.mvrx.withState
import com.sanmidev.confamgbedu.di.Graph
import com.sanmidev.confamgbedu.domain.model.Gbedu
import com.sanmidev.confamgbedu.domain.model.GbeduId
import com.sanmidev.confamgbedu.ui.theme.ConfamGbeduTheme
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@Composable
fun GdebuListScreen(navigateToDetails: (gbeduId: GbeduId) -> Unit) {
    val viewmodel: GbeduListViewModel = mavericksViewModel()
    val gbeduList by viewmodel.collectAsState() { it.gbeduList }
    LazyColumn(contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)) {
        items(gbeduList) { gbedu ->
            Greeting(gbedu.artistName) {
                navigateToDetails(gbedu.gbeduId)
            }
        }
    }
}

@Composable
fun Greeting(name: String, gbedu: () -> Unit) {
    Button(onClick = { gbedu() }) {
        Text(text = "Hello $name!")
    }


}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ConfamGbeduTheme {
        Greeting("Android") {}
    }
}