package com.example.walmartassessment.datasource

import android.util.Log
import com.example.walmartassessment.datasource.net.CountriesApiService
import com.example.walmartassessment.datasource.net.Country

/**
 * Implementation of the [CountriesRepository] interface that interacts with the remote API to fetch countries.
 * It provides the actual data source logic to retrieve country data.
 */
internal class CountriesRepositoryImpl(private val apiService: CountriesApiService) : CountriesRepository {

    /**
     * Fetches the list of countries from the remote API.
     * In case of an error or empty response, an empty list is returned.
     *
     * @return A list of [Country] objects. If an error occurs or the response is empty, an empty list is returned.
     */
    override suspend fun getCountries(): List<Country> {
        return try {
            // Attempt to fetch the list of countries from the API service
            apiService.getCountries().takeIf { it.isNotEmpty() } ?: emptyList()
        } catch (e: Exception) {
            // Log the error message if the request fails and return an empty list
            Log.e(COUNTRIES_REPOSITORY_TAG, "Error occurred while fetching countries list: ${e.message}", e)
            emptyList() // Return an empty list in case of error
        }
    }

    companion object {
        // Tag for logging errors in this class
        private const val COUNTRIES_REPOSITORY_TAG = "CountriesRepositoryImpl"
    }
}
