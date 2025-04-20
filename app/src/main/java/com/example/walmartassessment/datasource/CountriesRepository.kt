package com.example.walmartassessment.datasource

import com.example.walmartassessment.datasource.net.Country

/**
 * Repository interface that provides a contract for fetching the list of countries.
 * It abstracts the data source (e.g., network, database) and provides data to the ViewModel.
 */
interface CountriesRepository {

    /**
     * Fetches the list of countries.
     * This is a suspending function that makes a network request to retrieve country data.
     *
     * @return A list of [Country] objects.
     */
    suspend fun getCountries(): List<Country>

}
