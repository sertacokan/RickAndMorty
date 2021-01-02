package com.example.rickandmortyapp.paging

import androidx.paging.PagingSource
import com.example.rickandmortyapp.models.CharacterResultModel
import com.example.rickandmortyapp.network.CharactersService
import com.example.rickandmortyapp.repositories.CharacterRepository
import javax.inject.Inject

class CharacterPagingSource @Inject constructor(private val characterRepository: CharacterRepository) : PagingSource<Int, CharacterResultModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterResultModel> {

        val key = params.key ?: 1

        val response = characterRepository.getCharacterList(key)

        val previous = response.info.prev
        val next = response.info.next

        val previousKey = if (previous != null) key - 1 else null
        val nextKey = if (next != null) key + 1 else null

        return try {
            LoadResult.Page(response.results, previousKey, nextKey)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}