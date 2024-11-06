package com.example.navigationmodule.presentation.screens.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.navigationmodule.domain.entities.ProfileEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(): ViewModel() {

    val profileEntity = ProfileEntity(
        name = "Priyanshu Verma",
        address = "Kanpur"
    )

    val selectedProductId = 123456

    private val _cartCount: MutableStateFlow<Int> = MutableStateFlow(0)
    val cartCount = _cartCount.asStateFlow()

    fun updateCartCount(count: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _cartCount.emit(count)
        }
    }

}