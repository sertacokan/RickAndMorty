package com.example.rickandmortyapp.repositories

import com.example.rickandmortyapp.models.EpisodeResponseModel
import com.example.rickandmortyapp.network.EpisodeService
import javax.inject.Inject

class EpisodeRepository @Inject constructor(private val episodeService: EpisodeService) {

    suspend fun getCharacterLastEpisode(episodeId: Int?): EpisodeResponseModel {
        return episodeService.getCharacterLastEpisode(episodeId)
    }

}