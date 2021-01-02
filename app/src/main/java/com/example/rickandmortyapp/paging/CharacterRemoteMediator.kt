package com.example.rickandmortyapp.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.rickandmortyapp.database.CharacterEntity
import com.example.rickandmortyapp.datastore.NetworkDataStore
import com.example.rickandmortyapp.di.DefaultDispatcher
import com.example.rickandmortyapp.extensions.numberFromUrl
import com.example.rickandmortyapp.extensions.toCharacterEntity
import com.example.rickandmortyapp.models.CharacterEpisodeModel
import com.example.rickandmortyapp.models.CharacterResponseModel
import com.example.rickandmortyapp.usecases.CharacterUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

@ExperimentalPagingApi
class CharacterRemoteMediator @Inject constructor(
    private val characterUseCase: CharacterUseCase,
    private val networkDataStore: NetworkDataStore,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher
) : RemoteMediator<Int, CharacterEntity>() {

    override suspend fun load(loadType: LoadType, state: PagingState<Int, CharacterEntity>): MediatorResult {
        return try {

            val loadKey = when (loadType) {
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    state.lastItemOrNull() ?: return MediatorResult.Success(endOfPaginationReached = true)
                }
                LoadType.REFRESH -> {
                    null
                }
            }

            val lastPageNumber = networkDataStore.getLastPageNumber().single()

            val response = characterUseCase.getCharacterInfo(lastPageNumber)

            val entities = convertToEntities(response)

            val pageNumber = response.info.next?.numberFromUrl('=') ?: 1

            characterUseCase.insertAll(entities)

            networkDataStore.updateLastPageNumber(pageNumber)

            MediatorResult.Success(endOfPaginationReached = response.info.next == null)
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }

    private suspend fun convertToEntities(response: CharacterResponseModel): List<CharacterEntity> {
        return withContext(dispatcher) {

            val characterEpisodeModels = response.results.map { character ->
                val episodeId = character.episode.lastOrNull()?.numberFromUrl()
                val episodeResponse = characterUseCase.getEpisodeInfo(episodeId)
                CharacterEpisodeModel(response, episodeResponse)
            }

            characterEpisodeModels.toCharacterEntity()
        }
    }
}