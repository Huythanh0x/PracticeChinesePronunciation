package com.batdaulaptrinh.practicechinesepronunciation.domain.usecase.local.db_version

import com.batdaulaptrinh.practicechinesepronunciation.domain.source.datastore.LocalSettingDataSource
import javax.inject.Inject

class UpdateCurrentDbVersionUseCase @Inject constructor(private val localSettingDataSource: LocalSettingDataSource) {
    suspend operator fun invoke(newDbVersion: Int) {
        localSettingDataSource.updateCurrentLocalDbVersion(newDbVersion)
    }
}