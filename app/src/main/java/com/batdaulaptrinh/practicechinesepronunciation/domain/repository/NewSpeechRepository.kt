package com.batdaulaptrinh.practicechinesepronunciation.domain.repository

import com.batdaulaptrinh.practicechinesepronunciation.data.model.response.NewCourseResponseData
import retrofit2.Response

interface NewSpeechRepository {

    fun getAllSpeechFromCourseTitle(courseTitle: String)

    fun fetchAllSpeedFromCourse(courseTitle: String): Response<NewCourseResponseData>
}