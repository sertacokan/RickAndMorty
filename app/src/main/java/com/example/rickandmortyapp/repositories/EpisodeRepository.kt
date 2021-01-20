package com.example.rickandmortyapp.repositories

import com.example.rickandmortyapp.models.EpisodeResponseModel

interface EpisodeRepository{
    suspend fun getCharacterLastEpisode(episodeId: Int?): EpisodeResponseModel
}