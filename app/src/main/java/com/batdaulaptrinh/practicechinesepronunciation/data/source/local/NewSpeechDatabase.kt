package com.batdaulaptrinh.practicechinesepronunciation.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.batdaulaptrinh.practicechinesepronunciation.data.model.NewSpeechEntity

@Database(entities = [NewSpeechEntity::class], version = 1)
abstract class NewSpeechDatabase : RoomDatabase() {
    abstract fun dao(): NewSpeechDao
}