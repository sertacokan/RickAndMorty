package com.example.rickandmortyapp.extensions

import com.example.rickandmortyapp.database.CharacterEntity
import com.example.rickandmortyapp.models.CharacterEpisodeModel

fun List<CharacterEpisodeModel>.toCharacterEntity(): List<CharacterEntity> {

    val characters = flatMap { it.character.results }

    return mapIndexed { index, characterEpisodeModel ->

        val character = characters[index]
        val episode = characterEpisodeModel.episode

        CharacterEntity(
            characterId = character.id,
            characterName = character.name,
            characterStatus = character.status,
            characterSpecies = character.species,
            characterGender = character.gender,
            characterImage = character.image,
            characterOriginLocation = character.origin.name,
            characterLastKnownLocation = character.location.name,
            numberOfEpisodes = character.episode.count(),
            lastEpisode = episode?.name ?: "",
            lastEpisodeAir = episode?.airDate ?: "",
        )
    }
}

fun String.numberFromUrl(filterCh: Char = '/'): Int? {
    val episodeNumberStartIndex = this.indexOfLast { ch -> ch == filterCh } + 1
    return this.substring(episodeNumberStartIndex).toIntOrNull()
}