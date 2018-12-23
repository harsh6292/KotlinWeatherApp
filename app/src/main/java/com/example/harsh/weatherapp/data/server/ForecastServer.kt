package com.example.harsh.weatherapp.data.server

import com.example.harsh.weatherapp.data.ForecastRequest
import com.example.harsh.weatherapp.data.db.ForecastDb
import com.example.harsh.weatherapp.domain.datasource.ForecastDataSource
import com.example.harsh.weatherapp.domain.mappers.ServerDataMapper
import com.example.harsh.weatherapp.domain.model.ForecastList

class ForecastServer(private val dataMapper: ServerDataMapper = ServerDataMapper(),
                     private val forecastDb: ForecastDb = ForecastDb()) : ForecastDataSource {


    override fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList? {
        val result = ForecastRequest(zipCode).execute()
        val converted = dataMapper.convertToDomain(zipCode, result)

        forecastDb.saveForecast(converted)
        return forecastDb.requestForecastByZipCode(zipCode, date)
    }
}