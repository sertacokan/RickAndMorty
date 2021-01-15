package com.example.rickandmortyapp.actions

import com.example.rickandmortyapp.database.CharacterEntity

sealed class CharacterListAction

data class FavoriteAction(val characterEntity: CharacterEntity) : CharacterListAction()