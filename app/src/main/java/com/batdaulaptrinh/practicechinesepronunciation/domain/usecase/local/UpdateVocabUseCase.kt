package com.batdaulaptrinh.practicechinesepronunciation.domain.usecase.local

import com.batdaulaptrinh.practicechinesepronunciation.data.model.NewSpeechEntity
import com.batdaulaptrinh.practicechinesepronunciation.domain.repository.NewSpeechRepository
import javax.inject.Inject

class UpdateVocabUseCase @Inject constructor(private val newSpeechRepository: NewSpeechRepository) {
    suspend operator fun invoke(newSpeech: NewSpeechEntity) {
        newSpeechRepository.updateNewSpeech(newSpeech)
    }
}