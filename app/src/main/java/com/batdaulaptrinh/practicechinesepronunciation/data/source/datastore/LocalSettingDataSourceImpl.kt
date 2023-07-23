package com.batdaulaptrinh.practicechinesepronunciation.data.source.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import com.batdaulaptrinh.practicechinesepronunciation.domain.source.datastore.LocalSettingDataSource
import com.batdaulaptrinh.practicechinesepronunciation.util.Constant
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class LocalSettingDataSourceImpl @Inject constructor(private val datastore: DataStore<Preferences>) :
    LocalSettingDataSource {
    private object PreferenceKey {
        val currentLocalDbVersion = intPreferencesKey(Constant.PREFERENCES_CURRENT_DB_VERSION)
    }

    override suspend fun updateCurrentLocalDbVersion(currentDbVersion: Int) {
        datastore.edit { preferences ->
            preferences[PreferenceKey.currentLocalDbVersion] = currentDbVersion
        }
    }

    override suspend fun loadCurrentLocalDbVersion(): Int? {
        return datastore.data.first()[PreferenceKey.currentLocalDbVersion]
    }

}