package com.example.harsh.weatherapp.data

import android.util.Log
import java.net.URL

class Request(private val url: String) {

    fun run() {
        val jsonStr = URL(url).readText()
        Log.i(javaClass.simpleName, jsonStr)
    }
}