package com.example.navigationmodule.domain.entities.profile

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import com.example.navigationmodule.presentation.navigation.Screens
import com.example.navigationmodule.utils.serializableType
import kotlinx.serialization.Serializable
import kotlin.reflect.typeOf

@Serializable
data class ProfileEntity(
    val name: String,
    val address: String
){
    companion object {
        val typeMap = mapOf(typeOf<ProfileEntity>() to serializableType<ProfileEntity>())
        fun from(savedStateHandle: SavedStateHandle) =
            savedStateHandle.toRoute<Screens.ProfileScreen>(typeMap)
    }
}
