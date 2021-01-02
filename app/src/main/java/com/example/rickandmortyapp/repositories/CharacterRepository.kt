package com.example.rickandmortyapp.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import com.example.rickandmortyapp.database.CharacterDAO
import com.example.rickandmortyapp.database.CharacterEntity
import com.example.rickandmortyapp.models.CharacterResponseModel
import com.example.rickandmortyapp.models.CharacterResultModel
import com.example.rickandmortyapp.network.CharactersService
import com.example.rickandmortyapp.paging.CharacterPagingSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharacterRepository @Inject constructor(private val charactersService: CharactersService, private val characterDAO: CharacterDAO) {

    fun getCharacters(): PagingSource<Int, CharacterEntity> {
        return characterDAO.getCharacters()
    }

    suspend fun getCharacterList(page: Int?): CharacterResponseModel {
        return charactersService.getCharacterList(page)
    }

    suspend fun getCharacterInfo(characterId: Int): CharacterResultModel {
        return charactersService.getCharacterInfo(characterId)
    }

    suspend fun addToFavorite(characterEntity: CharacterEntity) {
        characterDAO.updateCharacterFavoriteState(characterEntity.copy(isFavorite = 1))
    }

    suspend fun removeFromFavorite(characterEntity: CharacterEntity) {
        characterDAO.updateCharacterFavoriteState(characterEntity.copy(isFavorite = 0))
    }

    suspend fun insertAll(entities: List<CharacterEntity>) {
        characterDAO.addCharacters(entities)
    }
}