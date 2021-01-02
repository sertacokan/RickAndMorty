package com.example.rickandmortyapp.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.rickandmortyapp.paging.CharacterPagingSource
import com.example.rickandmortyapp.paging.CharacterRemoteMediator
import com.example.rickandmortyapp.repositories.CharacterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach

@ExperimentalPagingApi
class CharacterListViewModel
@ViewModelInject constructor(
    private val characterPagingSource: CharacterPagingSource,
    private val characterRepository: CharacterRepository,
    private val characterRemoteMediator: CharacterRemoteMediator,
    pagingConfig: PagingConfig
) : ViewModel() {

    val characters = Pager(config = pagingConfig, remoteMediator = characterRemoteMediator, initialKey = 1) { characterRepository.getCharacters() }
        .flow
        .cachedIn(viewModelScope)

}