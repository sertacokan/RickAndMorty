package com.example.rickandmortyapp.repositories

import androidx.paging.PagingSource
import com.example.rickandmortyapp.database.CharacterEntity
import com.example.rickandmortyapp.models.CharacterResponseModel
import com.example.rickandmortyapp.models.CharacterResultModel

interface CharacterRepository{

    fun getCharacters(): PagingSource<Int, CharacterEntity>

    fun filterCharacterByStatus(status: String): PagingSource<Int, CharacterEntity>

    suspend fun getCharacterList(page: Int?): CharacterResponseModel

    suspend fun getCharacterInfo(characterId: Int): CharacterResultModel

    suspend fun addToFavorite(characterEntity: CharacterEntity)

    suspend fun removeFromFavorite(characterEntity: CharacterEntity)

    suspend fun insertAll(entities: List<CharacterEntity>)

}