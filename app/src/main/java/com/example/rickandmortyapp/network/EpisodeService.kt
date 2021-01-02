package com.example.rickandmortyapp.network

import com.example.rickandmortyapp.models.EpisodeResponseModel
import retrofit2.http.GET
import retrofit2.http.Path

interface EpisodeService {

    @GET("episode/{episode_id}")
    suspend fun getCharacterLastEpisode(
        @Path("episode_id") episodeId: Int?
    ): EpisodeResponseModel

}