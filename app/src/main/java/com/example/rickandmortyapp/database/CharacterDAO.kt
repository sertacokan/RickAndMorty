package com.example.rickandmortyapp.database

import androidx.paging.PagingSource
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDAO {

    @Query("SELECT * FROM CharacterTable")
    fun getCharacters(): PagingSource<Int, CharacterEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCharacters(characters: List<CharacterEntity>)

    @Query("SELECT * FROM CharacterTable WHERE Name= :filterText OR Status= :filterText")
    fun filterCharacterByNameOrStatus(filterText: String): Flow<List<CharacterEntity>>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateCharacterFavoriteState(characterEntity: CharacterEntity)
}