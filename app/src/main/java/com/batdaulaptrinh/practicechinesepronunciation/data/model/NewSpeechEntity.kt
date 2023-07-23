package com.batdaulaptrinh.practicechinesepronunciation.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("new_speech")
data class NewSpeechEntity(
    @PrimaryKey(autoGenerate = true)
    val vocabId: Int,
    val courseTitle: String,
    val weekTitle: String,
    val lessonTitle: String,
    val phrases_type: String,
    val chinese: String,
    val pinyin: String,
    val english: String,
    val isCompleted: Boolean
)
