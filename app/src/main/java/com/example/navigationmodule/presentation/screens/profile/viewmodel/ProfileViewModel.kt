package com.example.navigationmodule.presentation.screens.profile.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.navigationmodule.domain.entities.ProfileEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val profileDetails = ProfileEntity.from(savedStateHandle)

    private val _profile = MutableStateFlow(profileDetails.profile)
    val profile = _profile.asStateFlow()

}