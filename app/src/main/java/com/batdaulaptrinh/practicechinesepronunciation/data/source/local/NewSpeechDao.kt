package com.batdaulaptrinh.practicechinesepronunciation.data.source.local

import androidx.room.Dao
import androidx.room.Query

@Dao
interface NewSpeechDao {

    @Query("SELECT * FROM new_speech where courseTitle = :courseTitle")
    fun getAllSpeechFromCourseTitle(courseTitle: String)
}