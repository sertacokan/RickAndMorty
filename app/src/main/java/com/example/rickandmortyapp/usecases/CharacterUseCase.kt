package com.example.rickandmortyapp.usecases

import androidx.paging.PagingSource
import com.example.rickandmortyapp.ResponsePageInfo
import com.example.rickandmortyapp.database.CharacterEntity
import com.example.rickandmortyapp.datastore.PageInfoDataStore
import com.example.rickandmortyapp.extensions.numberFromUrl
import com.example.rickandmortyapp.extensions.toCharacterEntity
import com.example.rickandmortyapp.models.CharacterEpisodeModel
import com.example.rickandmortyapp.models.CharacterResponseModel
import com.example.rickandmortyapp.models.EpisodeResponseModel
import com.example.rickandmortyapp.repositories.CharacterRepository
import com.example.rickandmortyapp.repositories.EpisodeRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharacterUseCase @Inject constructor(
    private val characterRepository: CharacterRepository,
    private val episodeRepository: EpisodeRepository,
    private val pageInfoDataStore: PageInfoDataStore
) {

    fun getCharacters(): PagingSource<Int, CharacterEntity> {
        return characterRepository.getCharacters()
    }

    fun getFilteredCharacters(status: String): PagingSource<Int, CharacterEntity> {
        return characterRepository.filterCharacterByStatus(status)
    }

    suspend fun getCharacterInfo(pageInfo: ResponsePageInfo): CharacterResponseModel {
        return characterRepository.getCharacterList(pageInfo.nextPage)
    }

    suspend fun updateDataSources(characterResponse: CharacterResponseModel) {

        val responseNextPage = characterResponse.info.next?.numberFromUrl('=') ?: 1
        val responsePreviousPage = characterResponse.info.prev?.numberFromUrl('=') ?: 1

        val entities = convertToEntities(characterResponse)

        val responseInfo = characterResponse.info

        if (responseInfo.prev == null) {
            pageInfoDataStore.savePageInfo(
                responsePreviousPage,
                responseNextPage,
                responseInfo.pages,
                responseInfo.count
            )
        } else {
            pageInfoDataStore.updatePages(responsePreviousPage, responseNextPage)
        }

        insertAll(entities)
    }


    private suspend fun convertToEntities(response: CharacterResponseModel): List<CharacterEntity> {
        val characterEpisodeModels = response.results.map { character ->
            val episodeId = character.episode.lastOrNull()?.numberFromUrl()
            val episodeResponse = getEpisodeInfo(episodeId)
            CharacterEpisodeModel(response, episodeResponse)
        }

        return characterEpisodeModels.toCharacterEntity()
    }

    private suspend fun getEpisodeInfo(episodeId: Int?): EpisodeResponseModel {
        return episodeRepository.getCharacterLastEpisode(episodeId)
    }

    private suspend fun insertAll(characterEntities: List<CharacterEntity>) {
        characterRepository.insertAll(characterEntities)
    }

    suspend fun updateFavoriteState(isChecked: Boolean, characterEntity: CharacterEntity) {
        if (isChecked) {
            characterRepository.addToFavorite(characterEntity)
        } else {
            characterRepository.removeFromFavorite(characterEntity)
        }
    }
}