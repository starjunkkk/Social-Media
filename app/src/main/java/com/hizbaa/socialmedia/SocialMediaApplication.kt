package com.hizbaa.socialmedia

import android.app.Application
import com.hizbaa.socialmedia.data.AppContainer
import com.hizbaa.socialmedia.data.AppDataContainer

class SocialMediaApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}