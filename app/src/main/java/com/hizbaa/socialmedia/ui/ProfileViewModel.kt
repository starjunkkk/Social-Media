package com.hizbaa.socialmedia.ui

import androidx.lifecycle.ViewModel
import com.hizbaa.socialmedia.data.ProfileUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ProfileViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    fun updateName(name: String) {
        _uiState.update { currentState ->
            currentState.copy(
                name = name
            )
        }
    }
    fun updateAge(age: Int) {
        _uiState.update { currentState ->
            currentState.copy(
                age = age
            )
        }
    }
}