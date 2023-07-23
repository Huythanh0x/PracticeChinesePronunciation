package com.batdaulaptrinh.practicechinesepronunciation.domain.source.local

interface LocalNewSpeechDataSource {
    fun getAllSpeechFromCourseTitle(courseTitle: String)
}