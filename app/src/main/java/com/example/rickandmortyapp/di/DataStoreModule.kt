package com.example.rickandmortyapp.di

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.createDataStore
import com.example.rickandmortyapp.ResponsePageInfo
import com.example.rickandmortyapp.protobuf.PageInfoSerializer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton


@InstallIn(ApplicationComponent::class)
@Module
object DataStoreModule {

    @Singleton
    @Provides
    fun provideDataStore(@ApplicationContext context: Context): DataStore<ResponsePageInfo> {
        return context.createDataStore(fileName = "page_info.pb", serializer = PageInfoSerializer)
    }

}