package com.hizbaa.socialmedia.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hizbaa.socialmedia.data.Profile
import com.hizbaa.socialmedia.data.ProfileRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val repository: ProfileRepository
) : ViewModel() {
    //private val _uiState = MutableStateFlow(ProfileUiState())
    //val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    val uiState = repository.getAll()
        .map { it.lastOrNull() ?: Profile(name = "", age = 0) }
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            Profile(name = "", age = 0)
        )

    fun save(name: String, age: Int) {
        viewModelScope.launch {
            val profile = Profile(name = name, age = age)
            repository.save(profile)
        }
    }
}