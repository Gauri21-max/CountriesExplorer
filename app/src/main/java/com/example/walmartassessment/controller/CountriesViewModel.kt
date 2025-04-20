package com.example.walmartassessment.controller

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.walmartassessment.datasource.CountriesRepository
import com.example.walmartassessment.datasource.net.Country
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel responsible for managing the state of the countries list.
 * It fetches countries from the repository and exposes the state to the UI via [UiState].
 */
class CountriesViewModel(
    private val countriesRepository: CountriesRepository
) : ViewModel() {

    // MutableStateFlow to hold the UI state which can be observed by the UI.
    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)

    // Publicly exposed state as a read-only flow.
    val uiState = _uiState.asStateFlow()

    init {
        fetchCountries()
    }

    /**
     * Fetches the list of countries from the repository asynchronously.
     * Updates the UI state based on the result.
     */
    fun fetchCountries() {
        viewModelScope.launch {
            try {
                val countriesList = countriesRepository.getCountries()
                _uiState.value = if (countriesList.isEmpty()) {
                    UiState.Error(NO_COUNTRIES_FOUND)
                } else {
                    UiState.Success(countriesList)
                }
            } catch (e: Exception) {
                _uiState.value = UiState.Error(GENERIC_ERROR_MESSAGE)
            }
        }
    }

    /**
     * Sealed class representing the different states of the UI:
     * - [Loading] for the initial loading state
     * - [Success] for successfully fetched countries
     * - [Error] for error scenarios.
     */
    sealed class UiState {
        object Loading : UiState() // State when data is being loaded
        data class Success(val countries: List<Country>) : UiState() // State with successfully loaded countries
        data class Error(val message: String) : UiState() // State with error message
    }

    /**
     * Factory class to create instances of [CountriesViewModel] with the required repository dependency.
     */
    class Factory(
        private val repository: CountriesRepository
    ) : ViewModelProvider.Factory {

        /**
         * Creates a new instance of [CountriesViewModel].
         *
         * @param modelClass the class of the ViewModel to be created.
         * @return a new [CountriesViewModel] instance.
         * @throws IllegalArgumentException if the ViewModel class is not assignable from [CountriesViewModel].
         */
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            // Checks if the provided class is assignable from CountriesViewModel and returns the ViewModel instance
            return if (modelClass.isAssignableFrom(CountriesViewModel::class.java)) {
                CountriesViewModel(repository) as T
            } else {
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }
    }

    companion object {
        private const val NO_COUNTRIES_FOUND = "No countries found"
        private const val GENERIC_ERROR_MESSAGE = "An error occurred, Please try later"
    }
}
