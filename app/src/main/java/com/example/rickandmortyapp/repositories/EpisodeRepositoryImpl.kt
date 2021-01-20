package com.example.rickandmortyapp.repositories

import com.example.rickandmortyapp.models.EpisodeResponseModel
import com.example.rickandmortyapp.network.EpisodeService
import javax.inject.Inject

class EpisodeRepositoryImpl @Inject constructor(
    private val episodeService: EpisodeService
) : EpisodeRepository {

    override suspend fun getCharacterLastEpisode(episodeId: Int?): EpisodeResponseModel {
        return episodeService.getCharacterLastEpisode(episodeId)
    }

}