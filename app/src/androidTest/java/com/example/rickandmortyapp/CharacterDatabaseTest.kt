package com.example.rickandmortyapp

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.rickandmortyapp.database.CharacterDatabase
import com.example.rickandmortyapp.database.CharacterEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class CharacterDatabaseTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private lateinit var database: CharacterDatabase

    @Before
    fun setUp() {
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
    fun test_updateCharacter() = coroutineTestRule.runBlockingTest {

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
    fun test_isNotFavorite() = coroutineTestRule.runBlockingTest {

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
    fun test_itemAllInserted() = coroutineTestRule.runBlockingTest {

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
        database.close()
    }
}