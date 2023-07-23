package com.batdaulaptrinh.practicechinesepronunciation.domain.usecase.local.db_version

import com.batdaulaptrinh.practicechinesepronunciation.domain.source.datastore.LocalSettingDataSource
import javax.inject.Inject

class GetCurrentDbVersionUseCase @Inject constructor(private val localSettingDataSource: LocalSettingDataSource) {
    suspend operator fun invoke() =
        localSettingDataSource.loadCurrentLocalDbVersion()
}