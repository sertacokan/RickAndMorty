package com.example.rickandmortyapp.listeners

import com.example.rickandmortyapp.database.CharacterEntity
import com.example.rickandmortyapp.models.CharacterResultModel

interface CharacterSelectionListener {
    fun onCharacterSelected(characterEntity: CharacterEntity)

    fun onCharacterFavoriteChanged(isChecked: Boolean, characterEntity: CharacterEntity)
}