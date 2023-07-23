package com.batdaulaptrinh.practicechinesepronunciation.data.source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.batdaulaptrinh.practicechinesepronunciation.data.model.NewSpeechEntity

@Dao
interface NewSpeechDao {
    @Query("SELECT * FROM new_speech where courseTitle = :courseTitle")
    suspend fun getAllSpeechFromCourseTitle(courseTitle: String): List<NewSpeechEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewSpeech(newSpeech: NewSpeechEntity)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewSpeeches(newSpeeches: List<NewSpeechEntity>)

    @Update
    suspend fun updateNewSpeech(newSpeech: NewSpeechEntity)

    @Delete
    suspend fun deleteNewSpeech(newSpeech: NewSpeechEntity)

    @Query("DELETE FROM new_speech")
    suspend fun deleteAllNewSpeeches()
}