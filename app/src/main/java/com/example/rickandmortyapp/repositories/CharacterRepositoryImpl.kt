package com.example.rickandmortyapp.repositories

import androidx.paging.PagingSource
import com.example.rickandmortyapp.database.CharacterDAO
import com.example.rickandmortyapp.database.CharacterEntity
import com.example.rickandmortyapp.models.CharacterResponseModel
import com.example.rickandmortyapp.models.CharacterResultModel
import com.example.rickandmortyapp.network.CharactersService
import javax.inject.Inject
import javax.inject.Singleton

class CharacterRepositoryImpl @Inject constructor(
    private val charactersService: CharactersService,
    private val characterDAO: CharacterDAO
) : CharacterRepository {

    override fun getCharacters(): PagingSource<Int, CharacterEntity> {
        return characterDAO.getCharacters()
    }

    override fun filterCharacterByStatus(status: String): PagingSource<Int, CharacterEntity> {
        return characterDAO.filterCharacterByStatus(status)
    }

    override suspend fun getCharacterList(page: Int?): CharacterResponseModel {
        return charactersService.getCharacterList(page)
    }

    override suspend fun getCharacterInfo(characterId: Int): CharacterResultModel {
        return charactersService.getCharacterInfo(characterId)
    }

    override suspend fun addToFavorite(characterEntity: CharacterEntity) {
        characterDAO.updateCharacterFavoriteState(characterEntity.copy(isFavorite = 1))
    }

    override suspend fun removeFromFavorite(characterEntity: CharacterEntity) {
        characterDAO.updateCharacterFavoriteState(characterEntity.copy(isFavorite = 0))
    }

    override suspend fun insertAll(entities: List<CharacterEntity>) {
        characterDAO.addCharacters(entities)
    }
}