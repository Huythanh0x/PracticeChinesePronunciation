package com.batdaulaptrinh.practicechinesepronunciation.domain.repository

import com.batdaulaptrinh.practicechinesepronunciation.data.model.NewSpeechEntity
import com.batdaulaptrinh.practicechinesepronunciation.data.model.response.NewCourseResponseData
import retrofit2.Response

interface NewSpeechRepository {

    suspend fun getAllSpeechFromCourseTitle(courseTitle: String): List<NewSpeechEntity>

    suspend fun fetchAllSpeedFromCourse(courseTitle: String): Response<NewCourseResponseData>

    suspend fun insertNewSpeech(newSpeech: NewSpeechEntity)
    suspend fun insertNewSpeeches(newSpeeches: List<NewSpeechEntity>)
    suspend fun updateNewSpeech(newSpeech: NewSpeechEntity)
    suspend fun deleteNewSpeech(newSpeech: NewSpeechEntity)
    suspend fun deleteAllNewSpeeches()
}