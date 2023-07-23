package com.batdaulaptrinh.practicechinesepronunciation.data.source.local

import com.batdaulaptrinh.practicechinesepronunciation.domain.source.local.LocalNewSpeechDataSource
import javax.inject.Inject

class LocalNewSpeechDataSourceImpl @Inject constructor(private val newSpeechDao: NewSpeechDao) :
    LocalNewSpeechDataSource {
    override fun getAllSpeechFromCourseTitle(courseTitle: String) =
        newSpeechDao.getAllSpeechFromCourseTitle(courseTitle)
}