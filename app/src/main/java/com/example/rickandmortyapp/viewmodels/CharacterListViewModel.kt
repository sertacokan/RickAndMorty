package com.example.rickandmortyapp.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.rickandmortyapp.datastore.PageInfoDataStore
import com.example.rickandmortyapp.di.DefaultDispatcher
import com.example.rickandmortyapp.paging.CharacterRemoteMediator
import com.example.rickandmortyapp.usecases.CharacterUseCase
import kotlinx.coroutines.CoroutineDispatcher

@ExperimentalPagingApi
class CharacterListViewModel
@ViewModelInject constructor(
    private val characterUseCase: CharacterUseCase,
    private val pageInfoDataStore: PageInfoDataStore,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) : ViewModel() {

    val characters =
        Pager(
            config = PagingConfig(pageSize = 10),
            remoteMediator = CharacterRemoteMediator(
                characterUseCase,
                pageInfoDataStore,
                defaultDispatcher
            )
        ) { characterUseCase.getCharacters() }.flow.cachedIn(viewModelScope)

}