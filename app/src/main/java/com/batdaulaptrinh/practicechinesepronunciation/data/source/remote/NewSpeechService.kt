package com.batdaulaptrinh.practicechinesepronunciation.data.source.remote

import com.batdaulaptrinh.practicechinesepronunciation.data.model.response.NewCourseResponseData
import retrofit2.Response
import retrofit2.http.GET

interface NewSpeechService {
    @GET("")
    suspend fun fetchAllSpeedFromCourse(courseTitle: String): Response<NewCourseResponseData>
}