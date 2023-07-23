package com.batdaulaptrinh.practicechinesepronunciation.data.source.local

import androidx.room.Database
import com.batdaulaptrinh.practicechinesepronunciation.data.model.NewSpeech

@Database(entities = [NewSpeech::class], version = 1)
abstract class NewSpeechDatabase {
    abstract  fun dao(): NewSpeechDao
}