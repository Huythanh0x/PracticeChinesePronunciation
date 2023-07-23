package com.batdaulaptrinh.practicechinesepronunciation.domain.usecase.local.db_version

import android.util.Log
import com.batdaulaptrinh.practicechinesepronunciation.domain.usecase.remote.FetchCourseDataUseCase
import javax.inject.Inject

class CheckUpdateUseCase @Inject constructor(
    val getCurrentDbVersionUseCase: GetCurrentDbVersionUseCase,
    private val fetchCourseDataUseCase: FetchCourseDataUseCase
) {
    suspend operator fun invoke(): Boolean {
        val remoteDbVersion = fetchCourseDataUseCase("").data?.dbVersion ?: return false
        Log.d("REMOTE DB VERSION", remoteDbVersion.toString())
        val localDbVersion = getCurrentDbVersionUseCase() ?: return true
        Log.d("LOCAL DB VERSION", localDbVersion.toString())
        return localDbVersion < remoteDbVersion
    }
}