package com.example.navigationmodule.presentation.screens.home

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import com.example.navigationmodule.domain.entities.ProfileEntity
import com.example.navigationmodule.presentation.LocalNavigationHandler
import com.example.navigationmodule.presentation.navigation.Screens
import com.example.navigationmodule.presentation.screens.home.viewmodel.HomeViewModel

@Composable
fun HomeScreenComposable(
    viewModel: HomeViewModel = hiltViewModel()
) {

    val cartCount by viewModel.cartCount.collectAsStateWithLifecycle()
    val navigationHandler = LocalNavigationHandler.current
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(Unit) {
        val savedStateHandle = navigationHandler.navController.currentBackStackEntry?.savedStateHandle
        val productBackPressResult = savedStateHandle?.getLiveData<Int>("productBackPressResult")
        productBackPressResult?.observe(lifecycleOwner) { result ->
            viewModel.updateCartCount(result)
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Button(onClick = {
            navigationHandler.navigateToScreen(Screens.ProfileScreen(viewModel.profileEntity))
        }) {
            Text("Open Profile")
        }

        Spacer(Modifier.height(16.dp))

        Button(onClick = {
            navigationHandler.navigateToScreen(Screens.ProductDetailsScreen(viewModel.selectedProductId))
        }) {
            Text("Open Product")
        }

        Spacer(Modifier.height(16.dp))

        Text("Cart Items -> ${cartCount}")

    }

}