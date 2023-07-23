package com.batdaulaptrinh.practicechinesepronunciation.data.source.local

import com.batdaulaptrinh.practicechinesepronunciation.data.model.NewSpeech
import com.batdaulaptrinh.practicechinesepronunciation.domain.source.local.LocalNewSpeechDataSource
import javax.inject.Inject

class LocalNewSpeechDataSourceImpl @Inject constructor(private val newSpeechDao: NewSpeechDao) :
    LocalNewSpeechDataSource {
    override suspend fun getAllSpeechFromCourseTitle(courseTitle: String) =
        newSpeechDao.getAllSpeechFromCourseTitle(courseTitle)

    override suspend fun insertNewSpeech(newSpeech: NewSpeech) = newSpeechDao.insertNewSpeech(newSpeech)

    override suspend fun insertNewSpeeches(newSpeeches: List<NewSpeech>) = newSpeechDao.insertNewSpeeches(newSpeeches)

    override suspend fun updateNewSpeech(newSpeech: NewSpeech) = newSpeechDao.updateNewSpeech(newSpeech)

    override suspend fun deleteNewSpeech(newSpeech: NewSpeech)  = newSpeechDao.deleteNewSpeech(newSpeech)

    override suspend fun deleteAllNewSpeeches() = newSpeechDao.deleteAllNewSpeeches()
}