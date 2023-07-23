package com.batdaulaptrinh.practicechinesepronunciation.domain.usecase.local.displaylist

import com.batdaulaptrinh.practicechinesepronunciation.domain.repository.NewSpeechRepository
import javax.inject.Inject

class GetListCourseUseCase @Inject constructor(private val newSpeechRepository: NewSpeechRepository) {
    suspend operator fun invoke(): List<String> {
       return newSpeechRepository.getListCourse()
    }
}