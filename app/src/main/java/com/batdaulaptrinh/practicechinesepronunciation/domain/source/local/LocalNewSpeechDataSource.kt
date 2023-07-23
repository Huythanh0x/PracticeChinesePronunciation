package com.batdaulaptrinh.practicechinesepronunciation.domain.source.local

import com.batdaulaptrinh.practicechinesepronunciation.data.model.NewSpeech

interface LocalNewSpeechDataSource {
    suspend fun getAllSpeechFromCourseTitle(courseTitle: String)
    suspend fun insertNewSpeech(newSpeech: NewSpeech)

    suspend fun insertNewSpeeches(newSpeeches: List<NewSpeech>)

    suspend fun updateNewSpeech(newSpeech: NewSpeech)

    suspend fun deleteNewSpeech(newSpeech: NewSpeech)

    suspend fun deleteAllNewSpeeches()
}