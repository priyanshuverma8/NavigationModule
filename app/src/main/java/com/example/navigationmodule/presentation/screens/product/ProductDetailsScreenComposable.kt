package com.example.navigationmodule.presentation.screens.product

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.navigationmodule.presentation.LocalNavigationHandler
import com.example.navigationmodule.presentation.screens.product.viewmodel.ProductDetailsViewModel

@Composable
fun ProductDetailsScreenComposable(
    viewModel: ProductDetailsViewModel = viewModel(),
) {

    val cartCount by viewModel.productCartCount.collectAsStateWithLifecycle()
    val navigationHandler = LocalNavigationHandler.current

    BackHandler {
        viewModel.setProductBackPressResult(cartCount)
        navigationHandler.navController.previousBackStackEntry?.savedStateHandle?.set(
            "productBackPressResult", cartCount
        )
        navigationHandler.popBackStack()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text("ProductId -> ${viewModel.productId}")
        Spacer(Modifier.height(16.dp))
        Button(onClick = {
            viewModel.addToCart(1)
        }) {
            Text("Add to cart")
        }

    }
}