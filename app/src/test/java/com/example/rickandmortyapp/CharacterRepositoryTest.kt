package com.example.rickandmortyapp

import androidx.paging.ExperimentalPagingApi
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.rickandmortyapp.repositories.CharacterRepository
import com.example.rickandmortyapp.viewmodels.CharacterListViewModel
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.runner.RunWith

@ExperimentalPagingApi
@RunWith(AndroidJUnit4::class)
class CharacterRepositoryTest {

    @MockK
    lateinit var characterRepository: CharacterRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

}