package com.example.navigationmodule.presentation.navigation

import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import androidx.navigation.toRoute
import com.example.navigationmodule.domain.entities.ProfileEntity
import com.example.navigationmodule.presentation.screens.home.HomeScreenComposable
import com.example.navigationmodule.presentation.screens.home.viewmodel.HomeViewModel
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
            SplashScreenComposable(
                onSplashComplete = {
                    navController.navigate(Screens.HomeScreen)
                }
            )
        }

        composable<Screens.HomeScreen> { backStackEntry ->
            val savedStateHandle = backStackEntry.savedStateHandle
            val productBackPressResult = savedStateHandle.getLiveData<Int>("productBackPressResult")
            val viewModel = hiltViewModel<HomeViewModel>()
            productBackPressResult.observe(LocalLifecycleOwner.current) { result ->
                viewModel.updateCartCount(result)
            }
            HomeScreenComposable(
                viewModel = viewModel,
                onProfileClick = {
                    navController.navigate(Screens.ProfileScreen(profile = it))
                },
                onProductClick = {
                    navController.navigate(Screens.ProductDetailsScreen(productId = it))
                }
            )
        }

        composable<Screens.ProductDetailsScreen>(
            deepLinks = listOf(
                navDeepLink<Screens.ProductDetailsScreen>(
                    basePath = "https://shop.apnamart.in/"
                )
            )
        ) {
            val id = it.toRoute<Screens.ProductDetailsScreen>().productId
            ProductDetailsScreenComposable(
                onBackPress = {
                    navController.previousBackStackEntry?.savedStateHandle?.set(
                        "productBackPressResult", it
                    )
                    navController.popBackStack()
                })
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