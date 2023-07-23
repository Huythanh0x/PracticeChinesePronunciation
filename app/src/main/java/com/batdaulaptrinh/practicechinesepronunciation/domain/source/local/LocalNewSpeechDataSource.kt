package com.batdaulaptrinh.practicechinesepronunciation.domain.source.local

import com.batdaulaptrinh.practicechinesepronunciation.data.model.NewSpeechEntity

interface LocalNewSpeechDataSource {
    suspend fun getAllSpeechFromCourseTitle(courseTitle: String): List<NewSpeechEntity>
    suspend fun getListCourse(): List<String>
    suspend fun getListLesson(weekTitle: String): List<String>

    suspend fun getListVocab(lessonTitle: String): List<NewSpeechEntity>
    suspend fun getListWeek(courseTitle: String): List<String>

    suspend fun insertNewSpeech(newSpeech: NewSpeechEntity)

    suspend fun insertNewSpeeches(newSpeeches: List<NewSpeechEntity>)

    suspend fun updateNewSpeech(newSpeech: NewSpeechEntity)

    suspend fun deleteNewSpeech(newSpeech: NewSpeechEntity)

    suspend fun deleteAllNewSpeeches()
}