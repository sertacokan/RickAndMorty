package com.example.rickandmortyapp.models


data class CharacterResponseModel(
    val info: ResponseInfoModel,
    val results: List<CharacterResultModel>
)

data class ResponseInfoModel(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)

data class CharacterResultModel(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: CharacterOrigin,
    val location: CharacterLocation,
    val image: String,
    val episode: List<String>,
    val url: String
)

data class CharacterOrigin(
    val name: String,
    val url: String
)

data class CharacterLocation(
    val name: String,
    val url: String
)