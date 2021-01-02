package com.example.rickandmortyapp.di

import androidx.paging.PagingConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object PagingModule {

    @Singleton
    @Provides
    fun providePagingConfig(): PagingConfig {
        return PagingConfig(pageSize = 20)
    }


}