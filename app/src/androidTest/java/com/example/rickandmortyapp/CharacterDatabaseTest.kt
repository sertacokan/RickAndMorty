package com.example.rickandmortyapp

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.rickandmortyapp.database.CharacterDatabase
import com.example.rickandmortyapp.database.CharacterEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class CharacterDatabaseTest {

    private val testDispatcher = TestCoroutineDispatcher()

    private lateinit var database: CharacterDatabase

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        val context = ApplicationProvider.getApplicationContext<RickMortyApplication>()
        database = Room.inMemoryDatabaseBuilder(context, CharacterDatabase::class.java)
            .allowMainThreadQueries()
            .build()
    }

    /**
     * Mock database
     *
     * Test character entity is favorite
     */
    @Test
    fun test_updateCharacter() = runBlockingTest {

        val testCharacter = CharacterEntity(
            1, "Body Guard Morty", "Dead", "Human", "Male", "", "unknown",
            0, "", "", "Citadel of Ricks", 0
        )

        database.characterDao().updateCharacterFavoriteState(testCharacter)
    }

    /**
     *
     * Mock database
     *
     * Test character entity is not favorite
     */
    @Test
    fun test_isNotFavorite() = runBlockingTest {

        val testCharacter = CharacterEntity(
            1, "Body Guard Morty", "Dead", "Human", "Male", "", "unknown",
            0, "", "", "Citadel of Ricks", 0
        )

    }

    /**
     *
     * Mock database
     *
     * Test all response items inserted
     */
    @Test
    fun test_itemAllInserted() = runBlockingTest {

        val characters = listOf(
            CharacterEntity(
                1, "Body Guard Morty", "Dead", "Human", "Male", "", "unknown",
                0, "", "", "Citadel of Ricks", 0
            ),
            CharacterEntity(
                2, "Cyclops Morty", "Alive", "Humanoid", "Male", "", "unknown",
                0, "", "", "Citadel of Ricks", 0
            )
        )

        database.characterDao().addCharacters(characters)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
        database.close()
    }
}