package com.batdaulaptrinh.practicechinesepronunciation.data.source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.batdaulaptrinh.practicechinesepronunciation.data.model.NewSpeech

@Dao
interface NewSpeechDao {
    @Query("SELECT * FROM new_speech where courseTitle = :courseTitle")
    suspend fun getAllSpeechFromCourseTitle(courseTitle: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewSpeech(newSpeech: NewSpeech)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewSpeeches(newSpeeches: List<NewSpeech>)

    @Update
    suspend fun updateNewSpeech(newSpeech: NewSpeech)

    @Delete
    suspend fun deleteNewSpeech(newSpeech: NewSpeech)

    @Query("DELETE FROM new_speech")
    suspend fun deleteAllNewSpeeches()
}