package com.example.navigationmodule.presentation.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.navigationmodule.domain.entities.ProfileEntity
import com.example.navigationmodule.presentation.screens.home.viewmodel.HomeViewModel

@Composable
fun HomeScreenComposable(
    viewModel: HomeViewModel,
    onProfileClick: (profileEntity: ProfileEntity) -> Unit,
    onProductClick: (productId: Int) -> Unit,
) {

    val cartCount by viewModel.cartCount.collectAsStateWithLifecycle()


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Button(onClick = {
            onProfileClick(
                viewModel.profileEntity
            )
        }) {
            Text("Open Profile")
        }

        Spacer(Modifier.height(16.dp))

        Button(onClick = {
            onProductClick(
                viewModel.selectedProductId
            )
        }) {
            Text("Open Product")
        }

        Spacer(Modifier.height(16.dp))

        Text("Cart Items -> ${cartCount}")

    }

}