package com.example.navigationmodule.presentation.screens.product.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.navigationmodule.presentation.navigation.Screens
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductDetailsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    val productId = savedStateHandle.toRoute<Screens.ProductDetailsScreen>().productId

    private val _productCartCount: MutableStateFlow<Int> = MutableStateFlow(0)
    val productCartCount = _productCartCount.asStateFlow()

    fun addToCart(count: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _productCartCount.value += count
        }
    }

    fun setProductBackPressResult(result: Int) {

    }

}