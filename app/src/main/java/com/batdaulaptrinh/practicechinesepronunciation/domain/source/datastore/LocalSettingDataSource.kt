package com.batdaulaptrinh.practicechinesepronunciation.domain.source.datastore

interface LocalSettingDataSource {
    suspend fun loadCurrentLocalDbVersion(): Int?
    suspend fun updateCurrentLocalDbVersion(currentDbVersion: Int)
}