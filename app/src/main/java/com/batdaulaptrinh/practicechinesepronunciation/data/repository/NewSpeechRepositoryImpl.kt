package com.batdaulaptrinh.practicechinesepronunciation.data.repository

import com.batdaulaptrinh.practicechinesepronunciation.domain.repository.NewSpeechRepository
import com.batdaulaptrinh.practicechinesepronunciation.domain.source.local.LocalNewSpeechDataSource
import com.batdaulaptrinh.practicechinesepronunciation.domain.source.remote.RemoteNewSpeechDataSource
import javax.inject.Inject

class NewSpeechRepositoryImpl @Inject constructor(
    private val localNewSpeechDataSource: LocalNewSpeechDataSource,
    private val remoteNewSpeechDataSource: RemoteNewSpeechDataSource
) : NewSpeechRepository {
    override fun getAllSpeechFromCourseTitle(courseTitle: String) =
        localNewSpeechDataSource.getAllSpeechFromCourseTitle(courseTitle)

    override fun fetchAllSpeedFromCourse(courseTitle: String) =
        remoteNewSpeechDataSource.fetchAllSpeedFromCourse(courseTitle)
}