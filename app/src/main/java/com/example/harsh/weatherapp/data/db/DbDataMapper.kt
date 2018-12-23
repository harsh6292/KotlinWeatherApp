package com.example.harsh.weatherapp.data.db

import com.example.harsh.weatherapp.domain.model.ForecastList
import com.example.harsh.weatherapp.domain.model.ModelForecast

class DbDataMapper {
    fun convertToDomain(forecast: CityForecast) = with(forecast) {
        val daily = dailyForecast.map { convertDayToDomain(it) }
        ForecastList(_id, city, country, daily)
    }

    private fun convertDayToDomain(dayForecast: DayForecast) = with(dayForecast) {
        ModelForecast(date, description, high, low, iconUrl)
    }

    fun convertFromDomain(forecast: ForecastList) = with(forecast) {
        val daily = dailyModelForecast.map { convertDayFromDomain(id, it) }
        CityForecast(id, city, country, daily)
    }

    fun convertDayFromDomain(cityId: Long, forecast: ModelForecast) = with(forecast) {
        DayForecast(date, description, high, low, iconUrl, cityId)
    }
}