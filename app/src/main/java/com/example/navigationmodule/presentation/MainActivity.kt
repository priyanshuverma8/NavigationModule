package com.example.navigationmodule.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.navigationmodule.presentation.navigation.SetUpNavGraph
import com.example.navigationmodule.presentation.navigation.NavigationHandler
import com.example.navigationmodule.presentation.navigation.NavigationHandlerImpl
import com.example.navigationmodule.presentation.ui.theme.NavigationModuleTheme
import com.example.navigationmodule.presentation.ui.theme.heading2Xs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            val navigationHandler = remember {
                NavigationHandlerImpl(navController)
            }

            NavigationModuleTheme {
                CompositionLocalProvider(LocalNavigationHandler provides navigationHandler) {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        Text("", style = MaterialTheme.typography.heading2Xs)
                        SetUpNavGraph(navController)
                    }
                }
            }

        }
    }
}

val LocalNavigationHandler = staticCompositionLocalOf<NavigationHandler> {
    error("No NavigationHandler provided")
}
