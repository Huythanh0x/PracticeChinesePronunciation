package com.batdaulaptrinh.practicechinesepronunciation.domain.usecase.local.displaylist

import com.batdaulaptrinh.practicechinesepronunciation.data.model.NewSpeechEntity
import com.batdaulaptrinh.practicechinesepronunciation.domain.repository.NewSpeechRepository
import javax.inject.Inject

class GetListVocabUseCase @Inject constructor(val newSpeechRepository: NewSpeechRepository) {
    suspend operator fun invoke(lessonTitle: String): List<NewSpeechEntity> {
        return newSpeechRepository.getListVocab(lessonTitle)
    }
}