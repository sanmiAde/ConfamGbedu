package com.sanmidev.confamgbedu

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sanmidev.confamgbedu.domain.model.GbeduId
import com.sanmidev.confamgbedu.ui.gbedu.GbeduScreen
import com.sanmidev.confamgbedu.ui.gdebuList.GdebuListScreen


sealed class Screen(val route: String) {
    object GbeduList : Screen("gbeduList")
    object Gbedu : Screen("gbeduList/{gbebuId}/gbedu") {
        fun createRoute(gbeduId: GbeduId) = "gbeduList/${gbeduId.value}/gbedu"
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
                GdebuListScreen { gbeduId: GbeduId ->
                    appState.navController.navigate(Screen.Gbedu.createRoute(gbeduId = gbeduId))
                }
            }
            composable(Screen.Gbedu.route) { backStackEntry ->
                val arg = backStackEntry.arguments?.getString("gbebuId")
                requireNotNull(arg) { "Gbedu Id should not be null" }
                val gbeduId = GbeduId(arg.toLong())
                GbeduScreen(gbeduId) {
                    appState.navigateBack()
                }
            }
        }
    }
}