package com.batdaulaptrinh.practicechinesepronunciation.domain.usecase.local.displaylist

import com.batdaulaptrinh.practicechinesepronunciation.domain.repository.NewSpeechRepository
import javax.inject.Inject

class GetListWeekUseCase @Inject constructor(private val newSpeechRepository: NewSpeechRepository) {
    suspend operator fun invoke(courseTitle: String): List<String> {
        return newSpeechRepository.getListWeek(courseTitle)
    }
}