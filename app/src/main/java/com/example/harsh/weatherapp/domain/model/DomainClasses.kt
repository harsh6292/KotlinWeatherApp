package com.example.harsh.weatherapp.domain.model

data class ForecastList(val id: Long, val city: String, val country: String, val dailyModelForecast: List<ModelForecast>) {
    val size: Int
        get() = dailyModelForecast.size

    operator fun get(pos: Int): ModelForecast = dailyModelForecast[pos]
}

data class ModelForecast(val date: Long, val description: String, val high: Int, val low: Int, val iconUrl: String)
