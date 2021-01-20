package com.example.rickandmortyapp

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.rickandmortyapp.repositories.CharacterRepository
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.impl.annotations.MockK
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CharacterRepositoryTest {

    @MockK
    lateinit var characterRepository: CharacterRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    /**
     * Mock server
     *
     * Test data character list response is empty
     */
    @Test
    fun test_characterListEmpty() {

    }

    /**
     *
     * Mock server
     *
     * Test data character list response is not empty
     */
    @Test
    fun test_characterListNotEmpty() {

    }


    @After
    fun tearDown() {
        clearAllMocks()
    }
}