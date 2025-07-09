package com.hizbaa.socialmedia.data

import android.content.Context

interface AppContainer {
    val profileRepository: ProfileRepository
}

class AppDataContainer(context: Context) : AppContainer {
    private val db = AppDatabase.getDatabase(context)
    override val profileRepository: ProfileRepository =
        OfflineProfileRepository(db.profileDao())
}