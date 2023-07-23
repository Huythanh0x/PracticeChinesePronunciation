package com.batdaulaptrinh.practicechinesepronunciation.domain.usecase.remote

import com.batdaulaptrinh.practicechinesepronunciation.data.mapper.Mapper.toEntity
import com.batdaulaptrinh.practicechinesepronunciation.domain.repository.NewSpeechRepository
import com.batdaulaptrinh.practicechinesepronunciation.domain.usecase.local.db_version.UpdateCurrentDbVersionUseCase
import com.batdaulaptrinh.practicechinesepronunciation.util.ExecutionResult
import com.batdaulaptrinh.practicechinesepronunciation.util.network.NetWorkResult
import javax.inject.Inject

class DownloadCourseDataUseCase @Inject constructor(
    private val newSpeechRepository: NewSpeechRepository,
    private val fetchCourseDataUseCase: FetchCourseDataUseCase,
    val updateCurrentDbVersionUseCase: UpdateCurrentDbVersionUseCase
) {
    suspend operator fun invoke(courseTitle: String): ExecutionResult {
        val networkResult = fetchCourseDataUseCase(courseTitle)
        if (networkResult is NetWorkResult.Success && networkResult.data != null) {
            newSpeechRepository.insertNewSpeeches(networkResult.data.data.map { responseObject -> responseObject.toEntity() })
            updateCurrentDbVersionUseCase(networkResult.data.dbVersion)
            return ExecutionResult.Success()
        } else if (networkResult.data == null) {
            return ExecutionResult.Error("networkResult.data == null")
        } else {
            return ExecutionResult.Error(networkResult.message)
        }
    }
}