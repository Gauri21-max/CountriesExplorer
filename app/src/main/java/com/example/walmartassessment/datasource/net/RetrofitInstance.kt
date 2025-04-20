package com.example.walmartassessment.datasource.net

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Singleton object for creating and managing the Retrofit instance for the [CountriesApiService].
 * This class ensures that there is a single instance of [CountriesApiService] used throughout the app.
 */
object RetrofitInstance {

    // Volatile ensures visibility of the instance across threads
    @Volatile
    private var countriesApiService: CountriesApiService? = null

    /**
     * Returns the singleton instance of [CountriesApiService].
     * If the instance doesn't exist, it creates it using the provided base URL.
     *
     * @param baseUrl The base URL for the API.
     * @return [CountriesApiService] instance.
     */
    fun getInstance(baseUrl: String): CountriesApiService {
        // Check if the instance already exists
        return countriesApiService ?: synchronized(this) {
            // If not, build and assign a new instance
            countriesApiService ?: buildRetrofit(baseUrl).also {
                countriesApiService = it
            }
        }
    }

    /**
     * Builds a Retrofit instance with the given base URL and necessary converters.
     *
     * @param baseUrl The base URL for the API.
     * @return A new instance of [CountriesApiService] created using Retrofit.
     */
    private fun buildRetrofit(baseUrl: String): CountriesApiService {
        // Build the Retrofit instance
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl) // Set the base URL for the API
            .addConverterFactory(GsonConverterFactory.create()) // Use Gson for JSON parsing
            .build()

        // Return the API service interface
        return retrofit.create(CountriesApiService::class.java)
    }
}
