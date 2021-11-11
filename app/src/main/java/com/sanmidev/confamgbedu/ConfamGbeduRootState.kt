package com.sanmidev.confamgbedu

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun rememberConfamGbeduAppState(
    navController: NavHostController = rememberNavController()
) = remember(navController) {
    ConfamGbeduAppState(navController)
}

class ConfamGbeduAppState(
    val navController: NavHostController
) {
    fun navigateBack() {
        navController.popBackStack()
    }
}
