package com.example.rickandmortyapp.usecases

import androidx.paging.PagingSource
import com.example.rickandmortyapp.database.CharacterEntity
import com.example.rickandmortyapp.models.CharacterResponseModel
import com.example.rickandmortyapp.models.EpisodeResponseModel
import com.example.rickandmortyapp.repositories.CharacterRepository
import com.example.rickandmortyapp.repositories.EpisodeRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharacterUseCase @Inject constructor(private val characterRepository: CharacterRepository, private val episodeRepository: EpisodeRepository) {

    fun getCharacters(): PagingSource<Int, CharacterEntity> {
        return characterRepository.getCharacters()
    }

    fun getFilteredCharacters(status: String): PagingSource<Int, CharacterEntity> {
        return characterRepository.filterCharacterByStatus(status)
    }

    suspend fun getCharacterInfo(page: Int?): CharacterResponseModel {
        return characterRepository.getCharacterList(page)
    }

    suspend fun getEpisodeInfo(episodeId: Int?): EpisodeResponseModel {
        return episodeRepository.getCharacterLastEpisode(episodeId)
    }

    suspend fun insertAll(characterEntities: List<CharacterEntity>) {
        characterRepository.insertAll(characterEntities)
    }

    suspend fun updateFavoriteState(isChecked: Boolean, characterEntity: CharacterEntity) {
        if (isChecked) {
            characterRepository.addToFavorite(characterEntity)
        } else {
            characterRepository.removeFromFavorite(characterEntity)
        }
    }
}