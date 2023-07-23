package com.batdaulaptrinh.practicechinesepronunciation.domain.repository

import com.batdaulaptrinh.practicechinesepronunciation.data.model.NewSpeech
import com.batdaulaptrinh.practicechinesepronunciation.data.model.response.NewCourseResponseData
import retrofit2.Response

interface NewSpeechRepository {

    suspend fun getAllSpeechFromCourseTitle(courseTitle: String)

    suspend fun fetchAllSpeedFromCourse(courseTitle: String): Response<NewCourseResponseData>

    suspend fun insertNewSpeech(newSpeech: NewSpeech)
    suspend fun insertNewSpeeches(newSpeeches: List<NewSpeech>)
    suspend fun updateNewSpeech(newSpeech: NewSpeech)
    suspend fun deleteNewSpeech(newSpeech: NewSpeech)
    suspend fun deleteAllNewSpeeches()
}