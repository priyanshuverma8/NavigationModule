package com.example.navigationmodule.presentation.navigationV2

import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.navigationmodule.domain.entities.ProfileEntity
import com.example.navigationmodule.presentation.navigation.Screens

class NavigationHandlerImpl(
    override val navController: NavHostController
) : NavigationHandler {

    override fun navigateToScreen(screen: Screens) {
        navController.navigate(screen)
    }

    override fun popBackStack() {
        navController.popBackStack()
    }
}
