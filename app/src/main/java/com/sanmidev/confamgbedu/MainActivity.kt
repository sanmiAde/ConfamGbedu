package com.sanmidev.confamgbedu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.sanmidev.confamgbedu.ui.gdebuList.GdebuListScreen
import com.sanmidev.confamgbedu.ui.theme.ConfamGbeduTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ConfamGbeduTheme {
                ConfamGbeduApp {
                    GdebuListScreen()
                }
            }
        }
    }
}

