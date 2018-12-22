package com.example.harsh.weatherapp.ui

import android.app.Application
import com.example.harsh.weatherapp.ui.utils.DelegatesExtensions

class App: Application() {

    /*companion object {
         lateinit var instance: App
            private set
    }*/

    companion object {
        var instance: App by DelegatesExtensions.notNullSingleValue()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}