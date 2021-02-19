package com.example.rickandmortyapp

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.rickandmortyapp.repositories.CharacterRepository
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CharacterRepositoryTest {

    lateinit var characterRepository: CharacterRepository

    @Before
    fun setUp() {

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
    }
}