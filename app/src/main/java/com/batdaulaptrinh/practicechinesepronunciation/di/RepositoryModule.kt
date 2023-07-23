package com.batdaulaptrinh.practicechinesepronunciation.di

import com.batdaulaptrinh.practicechinesepronunciation.data.repository.NewSpeechRepositoryImpl
import com.batdaulaptrinh.practicechinesepronunciation.data.source.local.LocalNewSpeechDataSourceImpl
import com.batdaulaptrinh.practicechinesepronunciation.data.source.remote.RemoteNewSpeechDataSourceImpl
import com.batdaulaptrinh.practicechinesepronunciation.domain.repository.NewSpeechRepository
import com.batdaulaptrinh.practicechinesepronunciation.domain.source.local.LocalNewSpeechDataSource
import com.batdaulaptrinh.practicechinesepronunciation.domain.source.remote.RemoteNewSpeechDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindNewSpeechRepository(newSpeechRepositoryImpl: NewSpeechRepositoryImpl): NewSpeechRepository

    @Binds
    @Singleton
    abstract fun bindRemoteNewSpeechDataSource(remoteNewSpeechDataSourceImpl: RemoteNewSpeechDataSourceImpl): RemoteNewSpeechDataSource

    @Binds
    @Singleton
    abstract fun bindLocalNewSpeechDataSource(localNewSpeechDataSourceImpl: LocalNewSpeechDataSourceImpl): LocalNewSpeechDataSource
}