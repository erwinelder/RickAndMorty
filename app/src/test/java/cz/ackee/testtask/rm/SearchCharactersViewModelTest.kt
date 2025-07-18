package cz.ackee.testtask.rm

import androidx.paging.PagingData
import app.cash.turbine.test
import cz.ackee.testtask.rm.character.domain.usecase.GetCharactersByNameUseCase
import cz.ackee.testtask.rm.character.domain.usecase.GetFavoriteCharacterIdsUseCase
import cz.ackee.testtask.rm.character.presentation.viewmodel.SearchCharactersViewModel
import cz.ackee.testtask.rm.request.domain.model.ResultData
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class SearchCharactersViewModelTest {

    private val getCharactersByNameUseCase = mockk<GetCharactersByNameUseCase>()
    private val getFavoriteCharacterIdsUseCase = mockk<GetFavoriteCharacterIdsUseCase>()

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var viewModel: SearchCharactersViewModel

    @BeforeTest
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when search query is updated, should emit debounced query`() = runTest {
        // Given
        coEvery { getFavoriteCharacterIdsUseCase.execute() } returns flowOf(
            ResultData.Success(emptySet())
        )
        coEvery { getCharactersByNameUseCase.execute(any()) } returns flowOf(PagingData.empty())

        // When
        viewModel = SearchCharactersViewModel(getFavoriteCharacterIdsUseCase, getCharactersByNameUseCase)

        viewModel.searchQuery.test {
            // Initial empty query
            assertEquals("", awaitItem())

            // Update search query
            viewModel.onSearchQueryChange("Rick")
            assertEquals("Rick", awaitItem())

            viewModel.onSearchQueryChange("Morty")
            assertEquals("Morty", awaitItem())
        }
    }

    @Test
    fun `when search query has leading spaces, should trim them`() = runTest {
        // Given
        coEvery { getFavoriteCharacterIdsUseCase.execute() } returns flowOf(
            ResultData.Success(emptySet())
        )
        coEvery { getCharactersByNameUseCase.execute(any()) } returns flowOf(PagingData.empty())

        // When
        viewModel = SearchCharactersViewModel(getFavoriteCharacterIdsUseCase, getCharactersByNameUseCase)

        viewModel.searchQuery.test {
            // Initial empty query
            assertEquals("", awaitItem())

            // Update with leading spaces
            viewModel.onSearchQueryChange("   Rick")
            assertEquals("Rick", awaitItem())

            viewModel.onSearchQueryChange("     Morty Smith")
            assertEquals("Morty Smith", awaitItem())
        }
    }

    @Test
    fun `when search query is empty, should call use case with empty string`() = runTest {
        // Given
        coEvery { getFavoriteCharacterIdsUseCase.execute() } returns flowOf(
            ResultData.Success(emptySet())
        )
        coEvery { getCharactersByNameUseCase.execute("") } returns flowOf(PagingData.empty())

        // When
        viewModel = SearchCharactersViewModel(getFavoriteCharacterIdsUseCase, getCharactersByNameUseCase)

        // Start collecting the characters flow to trigger use case calls
        val job = launch {
            viewModel.characters.collect {}
        }

        // Advance time to trigger debounce
        advanceTimeBy(300)
        runCurrent()

        job.cancel()

        // Then
        coVerify { getCharactersByNameUseCase.execute("") }
    }

    @Test
    fun `when search query changes rapidly, should debounce and only call use case once`() = runTest {
        // Given
        coEvery { getFavoriteCharacterIdsUseCase.execute() } returns flowOf(
            ResultData.Success(emptySet())
        )
        coEvery { getCharactersByNameUseCase.execute(any()) } returns flowOf(PagingData.empty())

        // When
        viewModel = SearchCharactersViewModel(getFavoriteCharacterIdsUseCase, getCharactersByNameUseCase)

        // Start collecting the characters flow to trigger use case calls
        val job = launch {
            viewModel.characters.collect {}
        }

        // Advance time to trigger initial debounce for empty query
        advanceTimeBy(300)
        runCurrent()

        // Rapidly change search query
        viewModel.onSearchQueryChange("R")
        viewModel.onSearchQueryChange("Ri")
        viewModel.onSearchQueryChange("Ric")
        viewModel.onSearchQueryChange("Rick")

        // Advance time to trigger debounce for the final query
        advanceTimeBy(300)
        runCurrent()

        job.cancel()

        // Then
        coVerify(exactly = 1) { getCharactersByNameUseCase.execute("") } // Initial empty query
        coVerify(exactly = 1) { getCharactersByNameUseCase.execute("Rick") } // Final debounced query
    }

}