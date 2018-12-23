package com.example.harsh.weatherapp.domain.datasource

import com.example.harsh.weatherapp.domain.model.ForecastList

interface ForecastDataSource {
    fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList?
}