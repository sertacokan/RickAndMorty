package com.example.rickandmortyapp.di

import com.example.rickandmortyapp.repositories.CharacterRepository
import com.example.rickandmortyapp.repositories.CharacterRepositoryImpl
import com.example.rickandmortyapp.repositories.EpisodeRepository
import com.example.rickandmortyapp.repositories.EpisodeRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindCharacterRepository(characterRepositoryImpl: CharacterRepositoryImpl): CharacterRepository

    @Singleton
    @Binds
    abstract fun bindEpisodeRepository(episodeRepositoryImpl: EpisodeRepositoryImpl): EpisodeRepository

}