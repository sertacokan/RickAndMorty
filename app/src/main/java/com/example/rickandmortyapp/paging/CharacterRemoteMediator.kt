package com.example.rickandmortyapp.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.rickandmortyapp.database.CharacterEntity
import com.example.rickandmortyapp.datastore.PageInfoDataStore
import com.example.rickandmortyapp.usecases.CharacterUseCase
import kotlinx.coroutines.flow.first
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@ExperimentalPagingApi
class CharacterRemoteMediator @Inject constructor(
    private val characterUseCase: CharacterUseCase,
    private val pageInfoDataStore: PageInfoDataStore
) : RemoteMediator<Int, CharacterEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CharacterEntity>
    ): MediatorResult {

        return try {

            val pageInfo = pageInfoDataStore.getPageInfo().first()

            val response = characterUseCase.getCharacterInfo(pageInfo)

            characterUseCase.updateDataSources(response)

            MediatorResult.Success(endOfPaginationReached = response.info.next == null)
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }

}