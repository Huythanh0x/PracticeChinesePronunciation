package com.batdaulaptrinh.practicechinesepronunciation.di

import com.batdaulaptrinh.practicechinesepronunciation.data.source.remote.NewSpeechService
import com.batdaulaptrinh.practicechinesepronunciation.data.source.remote.RemoteNewSpeechDataSourceImpl
import com.batdaulaptrinh.practicechinesepronunciation.util.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideRemoteNewSpeechDataSource(newSpeechService: NewSpeechService) =
        RemoteNewSpeechDataSourceImpl(newSpeechService)

    @Singleton
    @Provides
    fun provideNewSpeechService(retrofit: Retrofit): NewSpeechService =
        retrofit.create(NewSpeechService::class.java)

    @Singleton
    @Provides
    fun provideRetrofitInstance(
        okHttpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder().baseUrl(Constant.BASE_URL_API).client(okHttpClient)
            .addConverterFactory(gsonConverterFactory).build()
    }

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient().newBuilder()
            .readTimeout(5000, TimeUnit.SECONDS)
            .connectTimeout(5000, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

}