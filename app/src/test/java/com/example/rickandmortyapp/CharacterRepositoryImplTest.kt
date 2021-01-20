package com.example.rickandmortyapp

import androidx.paging.ExperimentalPagingApi
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.rickandmortyapp.repositories.CharacterRepositoryImpl
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.runner.RunWith

@ExperimentalPagingApi
@RunWith(AndroidJUnit4::class)
class CharacterRepositoryImplTest {

    @MockK
    lateinit var characterRepositoryImpl: CharacterRepositoryImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

}