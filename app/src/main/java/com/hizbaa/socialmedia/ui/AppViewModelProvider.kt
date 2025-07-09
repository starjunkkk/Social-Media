package com.hizbaa.socialmedia.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.hizbaa.socialmedia.SocialMediaApplication

object AppViewModelProvider {
    val factory = viewModelFactory {
        initializer {
            ProfileViewModel(
                repository = socialMediaApplication().container.profileRepository
            )
        }
    }
}

fun CreationExtras.socialMediaApplication(): SocialMediaApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as SocialMediaApplication)