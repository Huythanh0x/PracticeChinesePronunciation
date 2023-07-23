package com.batdaulaptrinh.practicechinesepronunciation.data.source.remote

import com.batdaulaptrinh.practicechinesepronunciation.data.model.response.NewCourseResponseData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewSpeechService {
    @GET("/Huythanh0x/hanzi_database/master/course_data.json")
    suspend fun fetchAllSpeedFromCourse(@Query("courseTitle") courseTitle: String): Response<NewCourseResponseData>
}