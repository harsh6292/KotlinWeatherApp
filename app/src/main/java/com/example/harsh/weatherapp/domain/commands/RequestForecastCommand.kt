package com.example.harsh.weatherapp.domain.commands

import com.example.harsh.weatherapp.domain.datasource.ForecastProvider
import com.example.harsh.weatherapp.domain.model.ForecastList

class RequestForecastCommand(private val zipCode: Long,
                             private val forecastProvider: ForecastProvider = ForecastProvider()):
    Command<ForecastList> {

    companion object {
        const val DAYS = 7
    }
    override fun execute() = forecastProvider.requestByZipcode(zipCode, DAYS)
}