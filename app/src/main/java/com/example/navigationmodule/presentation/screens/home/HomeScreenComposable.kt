package com.example.navigationmodule.presentation.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.navigationmodule.presentation.LocalNavigationHandler
import com.example.navigationmodule.presentation.navigation.Screens
import com.example.navigationmodule.presentation.screens.home.components.HomeTopBar
import com.example.navigationmodule.presentation.screens.home.components.SectionNotAvailable
import com.example.navigationmodule.presentation.screens.home.viewmodel.HomeUiState
import com.example.navigationmodule.presentation.screens.home.viewmodel.HomeViewModel
import com.example.navigationmodule.presentation.ui.components.progressbar.CircularProgressBar
import com.example.navigationmodule.presentation.ui.theme.colorPrimary500

@Composable
fun HomeScreenComposable(
    viewModel: HomeViewModel = hiltViewModel()
) {

    val cartCount by viewModel.cartCount.collectAsStateWithLifecycle()
    val navigationHandler = LocalNavigationHandler.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        val savedStateHandle =
            navigationHandler.navController.currentBackStackEntry?.savedStateHandle
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
        HomeTopBar(
            onProfileClick = {
                navigationHandler.navigateToScreen(Screens.ProfileScreen(viewModel.profileEntity))
            },
        )
        when (uiState) {
            is HomeUiState.Loading -> {

                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressBar()
                }

            }

            is HomeUiState.Success -> {

                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text((uiState as HomeUiState.Success).data)
                }

            }

            is HomeUiState.Error -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    SectionNotAvailable(onRetryClick = {
                        viewModel.onRetryClick()
                    })
                }
            }

        }


    }

}