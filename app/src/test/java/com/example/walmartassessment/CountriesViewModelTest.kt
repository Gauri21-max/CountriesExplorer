package com.example.walmartassessment

import androidx.lifecycle.viewModelScope
import com.example.walmartassessment.controller.CountriesViewModel
import com.example.walmartassessment.datasource.CountriesRepository
import com.example.walmartassessment.datasource.net.Country
import com.example.walmartassessment.datasource.net.Currency
import com.example.walmartassessment.datasource.net.Language
import com.example.walmartassessment.controller.CountriesViewModel.UiState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import org.mockito.kotlin.mock
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After

@ExperimentalCoroutinesApi
class CountriesViewModelTest {

    private lateinit var viewModel: CountriesViewModel
    private val countriesRepository: CountriesRepository = mock()

    // TestDispatcher for coroutines testing
    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        // Set the Main dispatcher to the test dispatcher
        Dispatchers.setMain(testDispatcher)

        // Set up ViewModel with mocked repository and test dispatcher
        viewModel = CountriesViewModel(countriesRepository).apply {
            // Override the dispatcher for testing
            viewModelScope.launch(testDispatcher) {}
        }
    }

    @Test
    fun `test fetchCountries success`() = runBlockingTest {
        // Given
        val countriesList = listOf(Country("USA", "US", Currency("USD", "US Dollar", "$"), "usa_flag_url", Language("en", "English"), "United States", "Americas"))
        `when`(countriesRepository.getCountries()).thenReturn(countriesList)

        // When
        viewModel.fetchCountries()

        // Then
        assertEquals(UiState.Success(countriesList), viewModel.uiState.value)
    }

    @Test
    fun `test fetchCountries empty list`() = runBlockingTest {
        // Given
        val emptyCountriesList = emptyList<Country>()
        `when`(countriesRepository.getCountries()).thenReturn(emptyCountriesList)

        // When
        viewModel.fetchCountries()

        // Then
        assertEquals(UiState.Error("No countries found."), viewModel.uiState.value)
    }

    @Test
    fun `test fetchCountries error`() = runBlockingTest {
        // Given
        val errorMessage = "Network error"
        `when`(countriesRepository.getCountries()).thenThrow(RuntimeException(errorMessage))

        // When
        viewModel.fetchCountries()

        // Then
        assertEquals(UiState.Error("An error occurred: $errorMessage"), viewModel.uiState.value)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // Reset the main dispatcher after tests
        testDispatcher.cleanupTestCoroutines() // Clean up the dispatcher
    }
}
