package com.batdaulaptrinh.practicechinesepronunciation.data.repository

import com.batdaulaptrinh.practicechinesepronunciation.data.model.NewSpeechEntity
import com.batdaulaptrinh.practicechinesepronunciation.domain.repository.NewSpeechRepository
import com.batdaulaptrinh.practicechinesepronunciation.domain.source.local.LocalNewSpeechDataSource
import com.batdaulaptrinh.practicechinesepronunciation.domain.source.remote.RemoteNewSpeechDataSource
import javax.inject.Inject

class NewSpeechRepositoryImpl @Inject constructor(
    private val localNewSpeechDataSource: LocalNewSpeechDataSource,
    private val remoteNewSpeechDataSource: RemoteNewSpeechDataSource
) : NewSpeechRepository {
    override suspend fun getAllSpeechFromCourseTitle(courseTitle: String) =
        localNewSpeechDataSource.getAllSpeechFromCourseTitle(courseTitle)

    override suspend fun fetchAllSpeedFromCourse(courseTitle: String) =
        remoteNewSpeechDataSource.fetchAllSpeedFromCourse(courseTitle)

    override suspend fun getListLesson(weekTitle: String) =
        localNewSpeechDataSource.getListLesson(weekTitle)

    override suspend fun getListVocab(lessonTitle: String) =
        localNewSpeechDataSource.getListVocab(lessonTitle)

    override suspend fun getListCourse() = localNewSpeechDataSource.getListCourse()

    override suspend fun getListWeek(courseTitle: String) =
        localNewSpeechDataSource.getListWeek(courseTitle)

    override suspend fun insertNewSpeech(newSpeech: NewSpeechEntity) =
        localNewSpeechDataSource.insertNewSpeech(newSpeech)

    override suspend fun insertNewSpeeches(newSpeeches: List<NewSpeechEntity>) =
        localNewSpeechDataSource.insertNewSpeeches(newSpeeches)

    override suspend fun updateNewSpeech(newSpeech: NewSpeechEntity) =
        localNewSpeechDataSource.updateNewSpeech(newSpeech)

    override suspend fun deleteNewSpeech(newSpeech: NewSpeechEntity) =
        localNewSpeechDataSource.deleteNewSpeech(newSpeech)

    override suspend fun deleteAllNewSpeeches() = localNewSpeechDataSource.deleteAllNewSpeeches()
}