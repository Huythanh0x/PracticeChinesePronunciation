package com.batdaulaptrinh.practicechinesepronunciation.domain.usecase.local.displaylist

import com.batdaulaptrinh.practicechinesepronunciation.domain.repository.NewSpeechRepository
import javax.inject.Inject

class GetListLessonUseCase @Inject constructor(private val newSpeechRepository: NewSpeechRepository) {
    suspend operator fun invoke(weekTitle: String): List<String> {
        return newSpeechRepository.getListLesson(weekTitle)
    }
}