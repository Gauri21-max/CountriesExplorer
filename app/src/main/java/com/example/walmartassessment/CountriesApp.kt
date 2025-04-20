package com.example.walmartassessment

import android.app.Application

class CountriesApp: Application() {

    override fun onCreate() {
        super.onCreate()
        DI.getInstance(this)
    }
}