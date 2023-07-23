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

    @Query("SELECT courseTitle FROM new_speech group by courseTitle;")
    suspend fun getListCourse(): List<String>

    @Query("SELECT weekTitle FROM new_speech where courseTitle = :courseTitle group by weekTitle;")
    suspend fun getListWeek(courseTitle: String): List<String>

    @Query("SELECT lessonTitle FROM new_speech where weekTitle = :weekTitle group by lessonTitle")
    suspend fun getListLesson(weekTitle: String): List<String>

    @Query("SELECT * FROM new_speech where lessonTitle = :lessonTitle")
    suspend fun getListVocab(lessonTitle: String): List<NewSpeechEntity>
}