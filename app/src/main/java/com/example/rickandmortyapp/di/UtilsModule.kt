package com.example.rickandmortyapp.di

import android.content.Context
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ActivityContext

@InstallIn(ActivityComponent::class)
@Module
object UtilsModule {

    @Provides
    fun provideDividerItemDecoration(@ActivityContext context: Context): DividerItemDecoration {
        return DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
    }

    @Provides
    fun provideGridLayoutManager(@ActivityContext context: Context): GridLayoutManager {
        return GridLayoutManager(context, 2)
    }

    @Provides
    fun provideLinearLayoutManager(@ActivityContext context: Context): LinearLayoutManager {
        return LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }
}