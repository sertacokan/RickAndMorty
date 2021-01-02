package com.example.rickandmortyapp.models

data class CharacterEpisodeModel(
    val character: CharacterResponseModel,
    val episode:EpisodeResponseModel?
)
