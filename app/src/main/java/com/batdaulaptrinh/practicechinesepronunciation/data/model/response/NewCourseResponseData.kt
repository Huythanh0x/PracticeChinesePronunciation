package com.batdaulaptrinh.practicechinesepronunciation.data.model.response

import com.google.gson.annotations.SerializedName

data class NewCourseResponseData(
    @SerializedName("db_version")
    val dbVersion: Int,
    val data: List<NewSpeechResponseData>
)