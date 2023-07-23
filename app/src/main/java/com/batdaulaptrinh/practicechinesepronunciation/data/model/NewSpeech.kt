package com.batdaulaptrinh.practicechinesepronunciation.data.model

import androidx.room.Entity

@Entity("new_speech")
data class NewSpeech(
    val courseTitle: String,
    val weekTitle: String,
    val lessonTitle: String,
    val type: String,
    val chinese: String,
    val pinyin: String,
    val english: String
)
