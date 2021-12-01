package com.sanmidev.confamgbedu.ui.gdebuList

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.mvrx.Fail
import com.airbnb.mvrx.Loading
import com.airbnb.mvrx.Success
import com.airbnb.mvrx.Uninitialized
import com.airbnb.mvrx.compose.collectAsState
import com.airbnb.mvrx.compose.mavericksViewModel
import com.sanmidev.confamgbedu.R
import com.sanmidev.confamgbedu.Screen
import com.sanmidev.confamgbedu.domain.model.*
import com.sanmidev.confamgbedu.ui.theme.ConfamGbeduTheme
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

sealed interface GbebuListEvent {
    object AddGbedu : GbebuListEvent
    class EditGbedu(val gbeduId: GbeduId) : GbebuListEvent
}

@Composable
fun GdebuListScreen(navigateToDetails: (GbebuListEvent) -> Unit) {
    val viewModel: GbeduListViewModel = mavericksViewModel()
    val gbeduList by viewModel.collectAsState(GbeduListState::gbeduListRequest)
    when (gbeduList) {
        Uninitialized -> {}
        is Loading -> {
            Text(text = "Loading")
        }
        is Success -> {
            Column {
                LazyColumn(contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)) {
                    items(gbeduList.invoke()!!) { gbedu ->
                        GbeduRow(gbedu) {
                            navigateToDetails(GbebuListEvent.EditGbedu(gbedu.gbeduId))
                        }
                    }
                }
                FloatingActionButton(onClick = {
                    navigateToDetails(GbebuListEvent.AddGbedu)
                }) {
                    Icon(imageVector = Icons.Filled.Add, contentDescription = "Add new gbedu")
                }
            }
        }
        is Fail -> {

        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GbeduRow(gbedu: Gbedu, onNavigateAction: () -> Unit) {
    Card(onClick = { onNavigateAction() }) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(modifier = Modifier.align(Alignment.TopStart)) {
                Text(text = gbedu.name)
                Text(text = gbedu.artistName)
            }
            Row(modifier = Modifier.align(Alignment.TopEnd)) {
                RatingsComponent(rating = gbedu.rating)
            }
        }
    }
}

@Composable
fun RatingsComponent(rating: Rating) {
    repeat(rating.value.toInt()) {
        Image(
            painter = painterResource(R.drawable.ic_ratings_24),
            contentDescription = "Ratings",
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val gbebu = Gbedu(
        GbeduId(1), "Mass VI", "Amenra", Rating.TEN, Meta(
            ReleaseType.LP, Clock.System.now().toLocalDateTime(
                TimeZone.UTC
            ), Clock.System.now().toLocalDateTime(TimeZone.UTC)
        )
    )
    ConfamGbeduTheme {
        GbeduRow(gbedu = gbebu) {

        }
    }
}