package com.batdaulaptrinh.practicechinesepronunciation.domain.usecase.local.displaylist

import com.batdaulaptrinh.practicechinesepronunciation.domain.repository.NewSpeechRepository
import javax.inject.Inject

class GetListVocabUseCase @Inject constructor(val newSpeechRepository: NewSpeechRepository) {
    suspend operator fun invoke(lessonTitle: String) {
        newSpeechRepository.getListVocab(lessonTitle)
    }
}