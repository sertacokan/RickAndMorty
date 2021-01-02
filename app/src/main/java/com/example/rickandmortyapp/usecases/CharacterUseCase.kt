package com.example.rickandmortyapp.usecases

import com.example.rickandmortyapp.database.CharacterEntity
import com.example.rickandmortyapp.models.CharacterResponseModel
import com.example.rickandmortyapp.models.EpisodeResponseModel
import com.example.rickandmortyapp.repositories.CharacterRepository
import com.example.rickandmortyapp.repositories.EpisodeRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharacterUseCase @Inject constructor(private val characterRepository: CharacterRepository, private val episodeRepository: EpisodeRepository) {

    suspend fun getCharacterInfo(page: Int?): CharacterResponseModel {
        return characterRepository.getCharacterList(page)
    }

    suspend fun getEpisodeInfo(episodeId: Int?): EpisodeResponseModel {
        return episodeRepository.getCharacterLastEpisode(episodeId)
    }

    suspend fun insertAll(characterEntities: List<CharacterEntity>) {
        characterRepository.insertAll(characterEntities)
    }
}