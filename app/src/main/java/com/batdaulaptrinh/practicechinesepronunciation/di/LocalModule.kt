package com.batdaulaptrinh.practicechinesepronunciation.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.batdaulaptrinh.practicechinesepronunciation.data.source.datastore.LocalSettingDataSourceImpl
import com.batdaulaptrinh.practicechinesepronunciation.util.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {
    @Provides
    @Singleton
    fun provideLocalSettingDataSource(datastore: DataStore<Preferences>) =
        LocalSettingDataSourceImpl(datastore)

    @Provides
    @Singleton
    fun providePreferencesDatastore(@ApplicationContext appContext: Context): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            corruptionHandler = ReplaceFileCorruptionHandler(produceNewData = { emptyPreferences() }),
            produceFile = { appContext.preferencesDataStoreFile(Constant.DATA_STORE_NAME) },
            migrations = listOf(SharedPreferencesMigration(appContext, Constant.DATA_STORE_NAME)),
            scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
        )
    }
}