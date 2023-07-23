package com.batdaulaptrinh.practicechinesepronunciation.data.source.local

import com.batdaulaptrinh.practicechinesepronunciation.data.model.NewSpeechEntity
import com.batdaulaptrinh.practicechinesepronunciation.domain.source.local.LocalNewSpeechDataSource
import javax.inject.Inject

class LocalNewSpeechDataSourceImpl @Inject constructor(private val newSpeechDao: NewSpeechDao) :
    LocalNewSpeechDataSource {
    override suspend fun getAllSpeechFromCourseTitle(courseTitle: String) =
        newSpeechDao.getAllSpeechFromCourseTitle(courseTitle)

    override suspend fun getListLesson(weekTitle: String) = newSpeechDao.getListLesson(weekTitle)

    override suspend fun getListVocab(lessonTitle: String) = newSpeechDao.getListVocab(lessonTitle)

    override suspend fun getListCourse() = newSpeechDao.getListCourse()

    override suspend fun getListWeek(courseTitle: String) = newSpeechDao.getListWeek(courseTitle)

    override suspend fun insertNewSpeech(newSpeech: NewSpeechEntity) =
        newSpeechDao.insertNewSpeech(newSpeech)

    override suspend fun insertNewSpeeches(newSpeeches: List<NewSpeechEntity>) =
        newSpeechDao.insertNewSpeeches(newSpeeches)

    override suspend fun updateNewSpeech(newSpeech: NewSpeechEntity) =
        newSpeechDao.updateNewSpeech(newSpeech)

    override suspend fun deleteNewSpeech(newSpeech: NewSpeechEntity) =
        newSpeechDao.deleteNewSpeech(newSpeech)

    override suspend fun deleteAllNewSpeeches() = newSpeechDao.deleteAllNewSpeeches()
}