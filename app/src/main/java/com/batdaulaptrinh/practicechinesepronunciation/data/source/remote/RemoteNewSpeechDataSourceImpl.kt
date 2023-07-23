package com.batdaulaptrinh.practicechinesepronunciation.data.source.remote

import com.batdaulaptrinh.practicechinesepronunciation.domain.source.remote.RemoteNewSpeechDataSource
import javax.inject.Inject

class RemoteNewSpeechDataSourceImpl @Inject constructor(private val newSpeechService: NewSpeechService) :
    RemoteNewSpeechDataSource {
    override suspend fun fetchAllSpeedFromCourse(courseTitle: String) =
        newSpeechService.fetchAllSpeedFromCourse(courseTitle)
}