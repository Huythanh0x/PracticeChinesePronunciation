package com.batdaulaptrinh.practicechinesepronunciation.domain.usecase.local

import com.batdaulaptrinh.practicechinesepronunciation.domain.repository.NewSpeechRepository
import javax.inject.Inject

class GetCourseDataUseCase @Inject constructor(private val newSpeechRepository: NewSpeechRepository) {
    suspend operator fun invoke(courseTitle: String) =
        newSpeechRepository.getAllSpeechFromCourseTitle(courseTitle)
}