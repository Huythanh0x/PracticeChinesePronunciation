package com.batdaulaptrinh.practicechinesepronunciation.data.mapper

import com.batdaulaptrinh.practicechinesepronunciation.data.model.NewSpeechEntity
import com.batdaulaptrinh.practicechinesepronunciation.data.model.response.NewSpeechResponseData

object Mapper {
    fun NewSpeechResponseData.toEntity(): NewSpeechEntity {
        return NewSpeechEntity(
            this.vocabId,
            this.courseTitle,
            this.weekTitle,
            this.lessonTitle,
            this.phrases_type,
            this.chinese,
            this.pinyin,
            this.english,
            false
        )
    }
}