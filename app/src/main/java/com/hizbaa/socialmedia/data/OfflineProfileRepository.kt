package com.hizbaa.socialmedia.data

class OfflineProfileRepository(
    private val dao: ProfileDao
): ProfileRepository {
    override fun getAll() = dao.getAllProfiles()
    override suspend fun save(profile: Profile) = dao.insert(profile)
}