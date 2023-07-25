package com.batdaulaptrinh.practicechinesepronunciation.domain.usecase.remote

import android.util.Log
import com.batdaulaptrinh.practicechinesepronunciation.data.model.response.NewCourseResponseData
import com.batdaulaptrinh.practicechinesepronunciation.domain.repository.NewSpeechRepository
import com.batdaulaptrinh.practicechinesepronunciation.util.network.NetWorkResult
import javax.inject.Inject

class FetchCourseDataUseCase @Inject constructor(private val newSpeechRepository: NewSpeechRepository) {
    suspend operator fun invoke(courseTitle: String): NetWorkResult<NewCourseResponseData> =
        newSpeechRepository.fetchAllSpeedFromCourse(courseTitle).let { response ->
            Log.d("response data", response.toString())
            if (response.isSuccessful && response.body() is NewCourseResponseData) {
                return NetWorkResult.Success(response.body())
            } else {
                return NetWorkResult.Error("${response.code()} ${response.message()}")
            }
        }
}