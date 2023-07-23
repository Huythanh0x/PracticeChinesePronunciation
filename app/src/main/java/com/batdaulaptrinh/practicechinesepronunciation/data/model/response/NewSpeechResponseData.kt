package com.batdaulaptrinh.practicechinesepronunciation.data.model.response

import com.google.gson.annotations.SerializedName

data class NewSpeechResponseData(
    @SerializedName("vocab_id")
    val vocabId: Int,
    @SerializedName("course_title")
    val courseTitle: String,
    @SerializedName("week_title")
    val weekTitle: String,
    @SerializedName("lesson_title")
    val lessonTitle: String,
    @SerializedName("phrases_type")
    val phrases_type: String,
    val chinese: String,
    val pinyin: String,
    val english: String
)
