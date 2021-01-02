package com.example.rickandmortyapp.di

import android.content.Context
import androidx.room.Room
import com.example.rickandmortyapp.database.CharacterDAO
import com.example.rickandmortyapp.database.CharacterDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideCharacterDatabase(@ApplicationContext context: Context): CharacterDatabase {
        return Room.databaseBuilder(context, CharacterDatabase::class.java, "CharacterDB").build()
    }

    @Provides
    fun provideCharacterDAO(characterDatabase: CharacterDatabase): CharacterDAO {
        return characterDatabase.characterDao()
    }

}