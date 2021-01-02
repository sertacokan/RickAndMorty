package com.example.rickandmortyapp.network

import com.example.rickandmortyapp.models.CharacterResponseModel
import com.example.rickandmortyapp.models.CharacterResultModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharactersService {

    @GET("character/")
    suspend fun getCharacterList(
        @Query("page") page: Int?
    ): CharacterResponseModel

    @GET("character/{character_id}")
    suspend fun getCharacterInfo(
        @Path("character_id") characterId: Int
    ): CharacterResultModel

    @GET("character/")
    suspend fun filterCharacter(
        @Query("name") characterName: String?,
        @Query("status") characterStatus: String?
    ): CharacterResponseModel

}