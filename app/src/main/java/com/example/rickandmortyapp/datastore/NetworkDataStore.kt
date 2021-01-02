package com.example.rickandmortyapp.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.preferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkDataStore @Inject constructor(private val dataStore: DataStore<Preferences>) {

    companion object {
        private val prefKey = preferencesKey<Int>("lastPageNumber")
    }

    fun getLastPageNumber(): Flow<Int> {
        return dataStore.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw  exception
            }
        }.map { preferences ->
            preferences[prefKey] ?: 1
        }
    }

    suspend fun updateLastPageNumber(pageNumber: Int) {
        dataStore.edit { preferences ->
            preferences[prefKey] = pageNumber
        }
    }
}