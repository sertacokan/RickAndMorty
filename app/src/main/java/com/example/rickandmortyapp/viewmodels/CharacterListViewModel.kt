package com.example.rickandmortyapp.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.rickandmortyapp.database.CharacterEntity
import com.example.rickandmortyapp.paging.CharacterRemoteMediator
import com.example.rickandmortyapp.usecases.CharacterUseCase
import kotlinx.coroutines.launch

@ExperimentalPagingApi
class CharacterListViewModel
@ViewModelInject constructor(
    private val characterUseCase: CharacterUseCase,
    private val pagingConfig: PagingConfig,
    private val remoteMediator: CharacterRemoteMediator
) : ViewModel() {

    val characters =
        Pager(
            config = pagingConfig,
            remoteMediator = remoteMediator
        ) { characterUseCase.getCharacters() }
            .flow
            .cachedIn(viewModelScope)

    fun updateFavoriteState(isChecked: Boolean, characterEntity: CharacterEntity) {
        viewModelScope.launch {
            characterUseCase.updateFavoriteState(isChecked, characterEntity)
        }
    }

}