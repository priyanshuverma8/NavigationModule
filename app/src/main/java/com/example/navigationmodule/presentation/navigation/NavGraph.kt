package com.example.navigationmodule.presentation.navigation

import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.example.navigationmodule.domain.entities.profile.ProfileEntity
import com.example.navigationmodule.presentation.screens.home.HomeScreenComposable
import com.example.navigationmodule.presentation.screens.product.ProductDetailsScreenComposable
import com.example.navigationmodule.presentation.screens.profile.ProfileScreenComposable
import com.example.navigationmodule.presentation.screens.splash.SplashScreenComposable

@Composable
fun SetUpNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screens.SplashScreen
    ) {
        composable<Screens.SplashScreen> {
            SplashScreenComposable()
        }

        composable<Screens.HomeScreen> {
            HomeScreenComposable()
        }

        composable<Screens.ProductDetailsScreen>(
            deepLinks = listOf(
                navDeepLink<Screens.ProductDetailsScreen>(
                    basePath = "https://shop.apnamart.in/"
                )
            )
        ) {
            ProductDetailsScreenComposable()
        }

        composable<Screens.ProfileScreen>(
            typeMap = ProfileEntity.typeMap,
            enterTransition = { slideInVertically() },
            exitTransition = { slideOutVertically() }
        ) {
            ProfileScreenComposable()
        }
    }

}