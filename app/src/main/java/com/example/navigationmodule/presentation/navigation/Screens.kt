package com.example.navigationmodule.presentation.navigation

import com.example.navigationmodule.domain.entities.ProfileEntity
import kotlinx.serialization.Serializable

sealed interface Screens{

    @Serializable
    data object SplashScreen: Screens

    @Serializable
    data object HomeScreen: Screens

    @Serializable
    data class ProductDetailsScreen(
        val productId: Int
    ): Screens

    @Serializable
    data class ProfileScreen(
        val profile: ProfileEntity
    ): Screens

}




