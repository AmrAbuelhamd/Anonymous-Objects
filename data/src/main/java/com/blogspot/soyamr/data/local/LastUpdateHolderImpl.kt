package com.blogspot.soyamr.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class LastUpdateHolderImpl(private val context: Context) : LastUpdateHolder {
    private val key = longPreferencesKey(LAST_UPDATE_KEY)
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = LAST_UPDATE_SETTINGS)
    private val lastUpdated: Flow<Long> by lazy {
        context.dataStore.data
            .map { preferences ->
                preferences[key]
                    ?: System.currentTimeMillis() - ONE_HOUR_MILLIS
            }
    }

    override suspend fun shouldWeUpdateCache(): Boolean =
        (System.currentTimeMillis() > lastUpdated.first())

    override suspend fun setNowAsLastUpdate() {
        context.dataStore.edit { settings ->
            settings[key] = System.currentTimeMillis()
        }
    }

    companion object {
        const val LAST_UPDATE_SETTINGS = "last_update_settings"
        const val LAST_UPDATE_KEY = "last_update_value"
        const val ONE_HOUR_MILLIS = 3600000
    }
}