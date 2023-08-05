package com.batdaulaptrinh.practicechinesepronunciation.domain.model_ui

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NewSpeechFlashCard(
    val vocabId: Int,
    val courseTitle: String,
    val weekTitle: String,
    val lessonTitle: String,
    val phrases_type: String,
    val chinese: String,
    val pinyin: String,
    val english: String,
    val isCompleted: Boolean,
    var isChineseActivated: Boolean = false,
    var isPinyinActivated: Boolean = false
) : Parcelable