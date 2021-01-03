package com.example.rickandmortyapp.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.rickandmortyapp.database.CharacterEntity
import com.example.rickandmortyapp.datastore.PageInfoDataStore
import com.example.rickandmortyapp.extensions.numberFromUrl
import com.example.rickandmortyapp.extensions.toCharacterEntity
import com.example.rickandmortyapp.models.CharacterEpisodeModel
import com.example.rickandmortyapp.models.CharacterResponseModel
import com.example.rickandmortyapp.usecases.CharacterUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

@ExperimentalPagingApi
class CharacterRemoteMediator constructor(
    private val characterUseCase: CharacterUseCase,
    private val pageInfoDataStore: PageInfoDataStore,
    private val dispatcher: CoroutineDispatcher
) : RemoteMediator<Int, CharacterEntity>() {

    override suspend fun load(loadType: LoadType, state: PagingState<Int, CharacterEntity>): MediatorResult {

        val pageInfo = pageInfoDataStore.getPageInfo().first()

        val loadKey = when (loadType) {
            LoadType.PREPEND -> { //Load data at the beginning of the currently loaded data set
                pageInfo.previousPage
            }
            LoadType.APPEND -> { //Load data at the end of the currently loaded data set
                pageInfo.nextPage
            }
            LoadType.REFRESH -> { // First time loading data
                null
            }
        }

        return try {

            val response = characterUseCase.getCharacterInfo(loadKey)

            val responseNextPage = response.info.next?.numberFromUrl('=') ?: 1
            val responsePreviousPage = response.info.prev?.numberFromUrl('=') ?: 1

            val entities = convertToEntities(response)

            if (loadType == LoadType.REFRESH) {
                val responseInfo = response.info
                pageInfoDataStore.savePageInfo(
                    responsePreviousPage,
                    responseNextPage,
                    responseInfo.pages,
                    responseInfo.count
                )
            } else {
                pageInfoDataStore.updatePages(responsePreviousPage, responseNextPage)
            }

            characterUseCase.insertAll(entities)

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