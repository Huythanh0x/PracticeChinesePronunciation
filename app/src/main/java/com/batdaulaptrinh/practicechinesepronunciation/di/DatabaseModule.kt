package com.batdaulaptrinh.practicechinesepronunciation.di

import android.content.Context
import androidx.room.Room
import com.batdaulaptrinh.practicechinesepronunciation.data.source.local.NewSpeechDatabase
import com.batdaulaptrinh.practicechinesepronunciation.util.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideDao(newSpeechDatabase: NewSpeechDatabase) = newSpeechDatabase.dao()

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context) =
        Room
            .databaseBuilder(context, NewSpeechDatabase::class.java, Constant.COUPON_DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()

}