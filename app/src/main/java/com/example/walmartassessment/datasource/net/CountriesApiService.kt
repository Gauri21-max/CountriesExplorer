package com.example.walmartassessment.datasource.net

import retrofit2.http.GET
import com.example.walmartassessment.utils.Constants.COUNTRIES_API_ENDPOINT

/**
 * Interface for defining API endpoints related to countries.
 * This interface uses Retrofit to make network requests to fetch country data.
 */
interface CountriesApiService {

    /**
     * Fetches a list of countries from the API endpoint.
     * The API request is made using a GET method and returns a list of [Country] objects.
     *
     * @return A list of countries fetched from the API.
     * @throws Exception if the network request fails or if there are issues with the API response.
     */
    @GET(COUNTRIES_API_ENDPOINT)
    suspend fun getCountries(): List<Country>
}
