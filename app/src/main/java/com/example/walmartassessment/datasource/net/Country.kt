package com.example.walmartassessment.datasource.net

/**
 * Data class representing a country.
 * This class encapsulates details about a country, such as its capital, code, currency, flag, language, and region.
 */
data class Country(
    /**
     * The capital city of the country.
     */
    val capital: String,

    /**
     * The country code (e.g., "US" for United States).
     */
    val code: String,

    /**
     * The currency used in the country, represented by a [Currency] object.
     */
    val currency: Currency,

    /**
     * The URL or identifier for the country's flag image.
     */
    val flag: String,

    /**
     * The official language(s) of the country, represented by a [Language] object.
     */
    val language: Language,

    /**
     * The name of the country (e.g., "United States").
     */
    val name: String,

    /**
     * The geographical region of the country (e.g., "Americas").
     */
    val region: String
)

/**
 * Data class representing currency details.
 * This class encapsulates the country's currency, including the currency code, name, and symbol.
 */
data class Currency(
    /**
     * The code of the currency (e.g., "USD" for US Dollar).
     */
    val code: String,

    /**
     * The name of the currency (e.g., "United States Dollar").
     */
    val name: String,

    /**
     * The symbol used for the currency (e.g., "$" for US Dollar).
     */
    val symbol: String
)

/**
 * Data class representing language details.
 * This class contains information about the language used in the country, including the language code and name.
 */
data class Language(
    /**
     * The language code (e.g., "en" for English).
     */
    val code: String,

    /**
     * The name of the language (e.g., "English").
     */
    val name: String
)
