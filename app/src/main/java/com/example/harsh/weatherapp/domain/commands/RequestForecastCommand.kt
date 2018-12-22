package com.example.harsh.weatherapp.domain.commands

import com.example.harsh.weatherapp.data.ForecastRequest
import com.example.harsh.weatherapp.domain.mappers.ForecastDataMapper
import com.example.harsh.weatherapp.domain.model.ForecastList

class RequestForecastCommand(private val zipCode: String): Command<ForecastList> {
    override fun execute(): ForecastList {

        val forecastRequest = ForecastRequest(zipCode)
        return ForecastDataMapper().convertFromDataModel(forecastRequest.execute())
    }
}