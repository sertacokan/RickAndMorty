package com.example.rickandmortyapp.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapp.database.CharacterEntity
import com.example.rickandmortyapp.repositories.CharacterRepository
import kotlinx.coroutines.launch

class CharacterDetailViewModel @ViewModelInject constructor(
    private val characterRepository: CharacterRepository
) : ViewModel() {

    val selectedCharacter = MutableLiveData<CharacterEntity>()

    val imageUrl = selectedCharacter.map { it.characterImage }

    val name = selectedCharacter.map { it.characterName }

    val species = selectedCharacter.map { it.characterSpecies }

    val status = selectedCharacter.map { it.characterStatus }

    val gender = selectedCharacter.map { it.characterGender }

    val episodeNumber = selectedCharacter.map { it.numberOfEpisodes.toString() }

    val lastLocation = selectedCharacter.map { it.characterLastKnownLocation }

    val originLocation = selectedCharacter.map { it.characterOriginLocation }

    val lastEpisodeName = selectedCharacter.map { it.lastEpisode }

    val lastEpisodeAirDate = selectedCharacter.map { it.lastEpisodeAir }

    val isFavorite = selectedCharacter.map { it.isFavorite }


    fun updateFavoriteState(isChecked: Boolean) {

        viewModelScope.launch {

            val characterEntity = selectedCharacter.value

            characterEntity?.let { entity ->

                if (isChecked) {
                    characterRepository.addToFavorite(entity)
                } else {
                    characterRepository.removeFromFavorite(entity)
                }
            }
        }
    }
}