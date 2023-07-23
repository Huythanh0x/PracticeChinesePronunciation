package com.batdaulaptrinh.practicechinesepronunciation.domain.source.remote

import com.batdaulaptrinh.practicechinesepronunciation.data.model.response.NewCourseResponseData
import retrofit2.Response

interface RemoteNewSpeechDataSource {
    fun fetchAllSpeedFromCourse(courseTitle: String): Response<NewCourseResponseData>
}