package com.example.navigationmodule.presentation.navigationV2

import androidx.navigation.NavController
import com.example.navigationmodule.presentation.navigation.Screens

interface NavigationHandler {
    val navController: NavController
    fun navigateToScreen(screen: Screens)
    fun popBackStack()
}
