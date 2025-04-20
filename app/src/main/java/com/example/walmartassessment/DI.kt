package com.example.walmartassessment

import android.app.Application
import android.content.Context
import com.example.walmartassessment.datasource.CountriesRepository
import com.example.walmartassessment.datasource.CountriesRepositoryImpl
import com.example.walmartassessment.datasource.net.CountriesApiService
import com.example.walmartassessment.datasource.net.RetrofitInstance
import com.example.walmartassessment.utils.Constants

/**
 * Singleton class that provides dependency injection for the application.
 * It manages the creation and retrieval of instances related to the data layer, such as:
 * - API service for network requests (CountriesApiService).
 * - Repository for managing countries data (CountriesRepository).
 *
 * The DI class is initialized once during the application's lifecycle and allows easy access to
 * the dependencies throughout the app.
 */
class DI private constructor(application: Application) {

    /**
     * Lazy initialization of the CountriesApiService.
     * The service is created using RetrofitInstance and the base URL for the countries API.
     */
    private val countriesApiService: CountriesApiService by lazy {
        RetrofitInstance.getInstance(Constants.COUNTRIES_BASE_URL)
    }

    /**
     * Lazy initialization of the CountriesRepository.
     * This repository interacts with the API service to fetch country data.
     */
    val countriesRepo: CountriesRepository by lazy {
        CountriesRepositoryImpl(countriesApiService)
    }

    companion object {
        @Volatile
        // Volatile ensures that changes to this variable are immediately visible to all threads.
        private var instance: DI? = null

        /**
         * Returns a singleton instance of the DI class.
         * This method ensures that only one instance of DI exists during the app's lifecycle.
         *
         * @param application The application context required for initializing the DI class.
         * @return The singleton instance of DI.
         */
        fun getInstance(application: Application): DI {
            return instance ?: synchronized(this) {
                // Double-check locking ensures that only one instance is created.
                instance ?: DI(application).also { instance = it }
            }
        }
    }
}

/**
 * Extension function to access the DI instance from a Context.
 * This function is useful when you need to access DI in any context (Activity, Fragment, etc.)
 *
 * @return The DI instance for dependency injection.
 */
fun Context.di(): DI = DI.getInstance(applicationContext as Application)
