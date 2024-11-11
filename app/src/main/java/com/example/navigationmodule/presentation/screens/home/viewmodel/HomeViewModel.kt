package com.example.navigationmodule.presentation.screens.home.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.navigationmodule.domain.entities.profile.ProfileEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    init {
        callApi()
    }

    val profileEntity = ProfileEntity(
        name = "Priyanshu Verma",
        address = "Kanpur"
    )

    val selectedProductId = 123456

    private val _cartCount: MutableStateFlow<Int> = MutableStateFlow(0)
    val cartCount = _cartCount.asStateFlow()

    private val _uiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState.Loading)

    val uiState: StateFlow<HomeUiState> = _uiState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = HomeUiState.Loading
    )

    fun callApi() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.emit(HomeUiState.Loading)
            delay(2000)
            _uiState.emit(HomeUiState.Error("Something went wrong!"))
        }
    }

    fun updateCartCount(count: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _cartCount.emit(count)
        }
    }

    fun onRetryClick() {
        callApi()
    }

}

sealed interface HomeUiState {
    data object Loading : HomeUiState
    data class Success(val data: String) : HomeUiState
    data class Error(val message: String) : HomeUiState
}