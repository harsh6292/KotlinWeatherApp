package com.example.harsh.weatherapp.domain.mappers

import com.example.harsh.weatherapp.data.Forecast
import com.example.harsh.weatherapp.data.ForecastResult
import com.example.harsh.weatherapp.domain.model.ForecastList
import com.example.harsh.weatherapp.domain.model.ModelForecast
import java.text.DateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class ForecastDataMapper {

    fun convertFromDataModel(forecast: ForecastResult): ForecastList =
            ForecastList(forecast.city.name, forecast.city.country, convertForecastListToDomain(forecast.list))

    private fun convertForecastListToDomain(list: List<Forecast>): List<ModelForecast> {
        return list.mapIndexed { index, forecast ->
            val date = Calendar.getInstance().timeInMillis + TimeUnit.DAYS.toMillis(index.toLong())
            convertForecastItemToDomain(forecast.copy(dt = date))
        }
    }

    private fun convertForecastItemToDomain(forecast: Forecast): ModelForecast {
        return ModelForecast(
            convertDate(forecast.dt),
            forecast.weather[0].description,
            forecast.temp.max.toInt(),
            forecast.temp.min.toInt(),
            generateIconUrl(forecast.weather[0].icon))
    }

    private fun convertDate(date: Long): String {
        val df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault())
        return df.format(date)
    }

    private fun generateIconUrl(iconCode: String): String =
        "http://openweathermap.org/img/w/$iconCode.png"
}