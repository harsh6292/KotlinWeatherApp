package com.example.harsh.weatherapp.domain.commands

import com.example.harsh.weatherapp.domain.datasource.ForecastProvider
import com.example.harsh.weatherapp.domain.model.ModelForecast

class RequestDayForecastCommand(private val zipCode: Long,
                                private val forecastProvider: ForecastProvider = ForecastProvider()):
    Command<ModelForecast> {

    override fun execute() = forecastProvider.requestForecast(zipCode)
}