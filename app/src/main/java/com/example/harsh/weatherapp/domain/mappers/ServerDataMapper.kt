package com.example.harsh.weatherapp.domain.mappers

import com.example.harsh.weatherapp.data.Forecast
import com.example.harsh.weatherapp.data.ForecastResult
import com.example.harsh.weatherapp.domain.model.ForecastList
import com.example.harsh.weatherapp.domain.model.ModelForecast
import java.util.*
import java.util.concurrent.TimeUnit

class ServerDataMapper {

    fun convertToDomain(zipCode: Long, forecast: ForecastResult) = with(forecast) {
        ForecastList(zipCode, city.name, city.country, convertForecastListToDomain(list))
    }

    private fun convertForecastListToDomain(list: List<Forecast>): List<ModelForecast> {
        return list.mapIndexed { index, forecast ->
            val date = Calendar.getInstance().timeInMillis + TimeUnit.DAYS.toMillis(index.toLong())
            convertForecastItemToDomain(forecast.copy(dt = date))
        }
    }

    private fun convertForecastItemToDomain(forecast: Forecast) = with (forecast) {
        ModelForecast(dt, weather[0].description,
            temp.max.toInt(), temp.min.toInt(),
            generateIconUrl(weather[0].icon))
    }

    private fun generateIconUrl(iconCode: String): String =
        "http://openweathermap.org/img/w/$iconCode.png"
}