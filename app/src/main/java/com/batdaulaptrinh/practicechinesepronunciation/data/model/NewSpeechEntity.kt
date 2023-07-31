package com.batdaulaptrinh.practicechinesepronunciation.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity("new_speech")
@Parcelize
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
) : Parcelable
