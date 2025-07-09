package com.hizbaa.socialmedia.data

import kotlinx.coroutines.flow.Flow

interface ProfileRepository {
    fun getAll(): Flow<List<Profile>>
    suspend fun save(profile: Profile)
}