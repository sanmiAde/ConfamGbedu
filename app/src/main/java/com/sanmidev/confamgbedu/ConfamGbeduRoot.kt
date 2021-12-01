package com.sanmidev.confamgbedu

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sanmidev.confamgbedu.domain.model.GbeduId
import com.sanmidev.confamgbedu.ui.gbedu.AddGbeduScreen
import com.sanmidev.confamgbedu.ui.gbedu.EditGbeduScreen
import com.sanmidev.confamgbedu.ui.gdebuList.GbebuListEvent
import com.sanmidev.confamgbedu.ui.gdebuList.GdebuListScreen


sealed class Screen(val route: String) {
    object GbeduList : Screen("gbeduList")
    object AddGbedu : Screen("gbeduList/gbedu") {
        fun createRoute() =
            "gbeduList/gbedu"
    }

    object EditGbedu : Screen("gbeduList/{gbeduId}/gbedu") {
        fun createRoute(gbeduId: GbeduId) =
            "gbeduList/${gbeduId.value}/gbedu"
    }
}

@Composable
fun ConfamGbeduRoot(appState: ConfamGbeduAppState = rememberConfamGbeduAppState()) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        NavHost(navController = appState.navController, startDestination = Screen.GbeduList.route) {
            composable(Screen.GbeduList.route) {
                GdebuListScreen { gbeduListEvent ->
                    when (gbeduListEvent) {
                        GbebuListEvent.AddGbedu -> {
                            appState.navController.navigate(Screen.AddGbedu.createRoute())
                        }
                        is GbebuListEvent.EditGbedu -> {
                            appState.navController.navigate(
                                Screen.EditGbedu.createRoute(
                                    gbeduListEvent.gbeduId
                                )
                            )
                        }
                    }

                }
            }
            composable(Screen.AddGbedu.route) {
                AddGbeduScreen {
                    appState.navigateBack()
                }
            }
            composable(Screen.EditGbedu.route) { backStackEntry: NavBackStackEntry ->
                val arg = backStackEntry.arguments?.getString("gbeduId")
                requireNotNull(arg) { "Gbedu Id should not be null" }
                val gbeduId = GbeduId(arg.toLong())
                EditGbeduScreen(id = gbeduId) {
                    appState.navigateBack()
                }
            }
        }
    }

}