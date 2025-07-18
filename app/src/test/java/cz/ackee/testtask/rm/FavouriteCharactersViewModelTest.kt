package cz.ackee.testtask.rm

import app.cash.turbine.test
import cz.ackee.testtask.rm.character.domain.model.Character
import cz.ackee.testtask.rm.character.domain.model.error.CharacterError
import cz.ackee.testtask.rm.character.domain.usecase.GetFavoriteCharactersUseCase
import cz.ackee.testtask.rm.character.presentation.viewmodel.FavouriteCharactersViewModel
import cz.ackee.testtask.rm.request.domain.model.ResultData
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class FavouriteCharactersViewModelTest {

    private lateinit var getFavoriteCharactersUseCase: GetFavoriteCharactersUseCase
    private lateinit var viewModel: FavouriteCharactersViewModel
    private val testDispatcher = StandardTestDispatcher()

    @BeforeTest
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        getFavoriteCharactersUseCase = mockk(relaxed = true)
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when use case returns success with characters, should emit mapped UI states`() = runTest {
        val characters = listOf(
            Character(
                id = 1,
                imageUrl = "https://example.com/rick.png",
                name = "Rick Sanchez",
                status = "Alive",
                species = "Human",
                type = "",
                gender = "Male",
                origin = "Earth",
                location = "Earth"
            ),
            Character(
                id = 2,
                imageUrl = "https://example.com/morty.png",
                name = "Morty Smith",
                status = "Alive",
                species = "Human",
                type = "",
                gender = "Male",
                origin = "Earth",
                location = "Earth"
            )
        )
        val successResult = ResultData.Success<List<Character>, CharacterError>(characters)
        every { getFavoriteCharactersUseCase.execute() } returns flowOf(successResult)

        viewModel = FavouriteCharactersViewModel(getFavoriteCharactersUseCase)

        viewModel.characters.test {
            val initialEmission = awaitItem()
            assertTrue(initialEmission.isEmpty(), "Initial emission should be empty")

            val emission = awaitItem()
            assertEquals(2, emission.size)

            assertEquals(1, emission[0].id)
            assertEquals("Rick Sanchez", emission[0].name)
            assertEquals("Alive", emission[0].status)
            assertEquals("https://example.com/rick.png", emission[0].imageUrl)
            assertTrue(emission[0].isFavourite)

            assertEquals(2, emission[1].id)
            assertEquals("Morty Smith", emission[1].name)
            assertEquals("Alive", emission[1].status)
            assertEquals("https://example.com/morty.png", emission[1].imageUrl)
            assertTrue(emission[1].isFavourite)
        }

        verify(exactly = 1) { getFavoriteCharactersUseCase.execute() }
    }

    @Test
    fun `when use case returns success with empty list, should emit empty list`() = runTest {
        val successResult = ResultData.Success<List<Character>, CharacterError>(emptyList())
        every { getFavoriteCharactersUseCase.execute() } returns flowOf(successResult)

        viewModel = FavouriteCharactersViewModel(getFavoriteCharactersUseCase)

        viewModel.characters.test {
            val emission = awaitItem()
            assertTrue(emission.isEmpty())
        }

        verify(exactly = 1) { getFavoriteCharactersUseCase.execute() }
    }

    @Test
    fun `when use case returns error, should emit empty list`() = runTest {
        val errorResult = ResultData.Error<List<Character>, CharacterError>(
            CharacterError.FavouriteCharactersNotFetched
        )
        every { getFavoriteCharactersUseCase.execute() } returns flowOf(errorResult)

        viewModel = FavouriteCharactersViewModel(getFavoriteCharactersUseCase)

        viewModel.characters.test {
            val emission = awaitItem()
            assertTrue(emission.isEmpty())
        }

        verify(exactly = 1) { getFavoriteCharactersUseCase.execute() }
    }

    @Test
    fun `when use case emits multiple values, should emit corresponding UI states`() = runTest {
        val firstCharacters = listOf(
            Character(
                id = 1,
                imageUrl = "https://example.com/rick.png",
                name = "Rick Sanchez",
                status = "Alive",
                species = "Human",
                type = "",
                gender = "Male",
                origin = "Earth",
                location = "Earth"
            )
        )
        val secondCharacters = listOf(
            Character(
                id = 1,
                imageUrl = "https://example.com/rick.png",
                name = "Rick Sanchez",
                status = "Alive",
                species = "Human",
                type = "",
                gender = "Male",
                origin = "Earth",
                location = "Earth"
            ),
            Character(
                id = 2,
                imageUrl = "https://example.com/morty.png",
                name = "Morty Smith",
                status = "Alive",
                species = "Human",
                type = "",
                gender = "Male",
                origin = "Earth",
                location = "Earth"
            )
        )

        val firstResult = ResultData.Success<List<Character>, CharacterError>(firstCharacters)
        val secondResult = ResultData.Success<List<Character>, CharacterError>(secondCharacters)

        every { getFavoriteCharactersUseCase.execute() } returns flowOf(firstResult, secondResult)

        viewModel = FavouriteCharactersViewModel(getFavoriteCharactersUseCase)

        viewModel.characters.test {
            val initialEmission = awaitItem()
            assertTrue(initialEmission.isEmpty(), "Initial emission should be empty")

            val firstEmission = awaitItem()
            assertEquals(1, firstEmission.size)
            assertEquals("Rick Sanchez", firstEmission[0].name)
            assertTrue(firstEmission[0].isFavourite)

            val secondEmission = awaitItem()
            assertEquals(2, secondEmission.size)
            assertEquals("Rick Sanchez", secondEmission[0].name)
            assertEquals("Morty Smith", secondEmission[1].name)
            assertTrue(secondEmission.all { it.isFavourite })
        }

        verify(exactly = 1) { getFavoriteCharactersUseCase.execute() }
    }

    @Test
    fun `should set all characters as favourite in UI state`() = runTest {
        val characters = listOf(
            Character(
                id = 1,
                imageUrl = "https://example.com/rick.png",
                name = "Rick Sanchez",
                status = "Dead",
                species = "Human",
                type = "",
                gender = "Male",
                origin = "Earth",
                location = "Earth"
            )
        )
        val successResult = ResultData.Success<List<Character>, CharacterError>(characters)
        every { getFavoriteCharactersUseCase.execute() } returns flowOf(successResult)

        viewModel = FavouriteCharactersViewModel(getFavoriteCharactersUseCase)

        viewModel.characters.test {
            val emission = awaitItem()
            emission.forEach { character ->
                assertTrue(character.isFavourite, "Character ${character.name} should be marked as favourite")
            }
        }

        verify(exactly = 1) { getFavoriteCharactersUseCase.execute() }
    }

    @Test
    fun `should maintain proper UI state mapping for character with different statuses`() = runTest {
        val characters = listOf(
            Character(
                id = 1,
                imageUrl = "https://example.com/rick.png",
                name = "Rick Sanchez",
                status = "Alive",
                species = "Human",
                type = "",
                gender = "Male",
                origin = "Earth",
                location = "Earth"
            ),
            Character(
                id = 2,
                imageUrl = "https://example.com/morty.png",
                name = "Morty Smith",
                status = "Dead",
                species = "Human",
                type = "",
                gender = "Male",
                origin = "Earth",
                location = "Earth"
            ),
            Character(
                id = 3,
                imageUrl = "https://example.com/summer.png",
                name = "Summer Smith",
                status = "unknown",
                species = "Human",
                type = "",
                gender = "Female",
                origin = "Earth",
                location = "Earth"
            )
        )
        val successResult = ResultData.Success<List<Character>, CharacterError>(characters)
        every { getFavoriteCharactersUseCase.execute() } returns flowOf(successResult)

        viewModel = FavouriteCharactersViewModel(getFavoriteCharactersUseCase)

        viewModel.characters.test {
            val initialEmission = awaitItem()
            assertTrue(initialEmission.isEmpty(), "Initial emission should be empty")

            val emission = awaitItem()
            assertEquals(3, emission.size)

            assertEquals("Alive", emission[0].status)
            assertEquals("Dead", emission[1].status)
            assertEquals("unknown", emission[2].status)

            assertTrue(emission.all { it.isFavourite })
        }
    }

    @Test
    fun `should handle null and empty image URLs correctly`() = runTest {
        val characters = listOf(
            Character(
                id = 1,
                imageUrl = "",
                name = "Character with empty image",
                status = "Alive",
                species = "Human",
                type = "",
                gender = "Male",
                origin = "Earth",
                location = "Earth"
            )
        )
        val successResult = ResultData.Success<List<Character>, CharacterError>(characters)
        every { getFavoriteCharactersUseCase.execute() } returns flowOf(successResult)

        viewModel = FavouriteCharactersViewModel(getFavoriteCharactersUseCase)

        viewModel.characters.test {
            val initialEmission = awaitItem()
            assertTrue(initialEmission.isEmpty(), "Initial emission should be empty")

            val emission = awaitItem()
            assertEquals(1, emission.size)
            assertEquals("", emission[0].imageUrl)
            assertEquals("Character with empty image", emission[0].name)
            assertTrue(emission[0].isFavourite)
        }
    }
}