package com.example.rickandmortyapp.database

import androidx.paging.PagingSource
import androidx.room.*

@Dao
interface CharacterDAO {

    @Query("SELECT * FROM CharacterTable")
    fun getCharacters(): PagingSource<Int, CharacterEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCharacters(characters: List<CharacterEntity>)

    @Query("SELECT * FROM CharacterTable WHERE Status= :status")
    fun filterCharacterByStatus(status: String): PagingSource<Int, CharacterEntity>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateCharacterFavoriteState(characterEntity: CharacterEntity)
}