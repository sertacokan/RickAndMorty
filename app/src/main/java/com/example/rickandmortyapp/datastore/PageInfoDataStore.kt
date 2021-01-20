package com.example.rickandmortyapp.datastore

import androidx.datastore.core.DataStore
import com.example.rickandmortyapp.ResponsePageInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PageInfoDataStore @Inject constructor(private val dataStore: DataStore<ResponsePageInfo>) {

    fun getPageInfo(): Flow<ResponsePageInfo> {
        return dataStore.data.catch { exception ->

            if (exception is IOException) {
                emit(ResponsePageInfo.getDefaultInstance())
            } else {
                throw  exception
            }
        }
    }

    suspend fun updatePages(previousKey: Int, nextKey: Int) {
        dataStore.updateData { preferences ->
            preferences.toBuilder()
                .setNextPage(nextKey)
                .setPreviousPage(previousKey)
                .build()
        }
    }

    suspend fun savePageInfo(previousKey: Int, nextKey: Int, pages: Int, count: Int) {
        dataStore.updateData { preferences ->
            preferences.toBuilder()
                .setCount(count)
                .setNextPage(nextKey)
                .setPreviousPage(previousKey)
                .setPages(pages)
                .build()
        }
    }
}