package com.example.harsh.weatherapp.domain.datasource

import com.example.harsh.weatherapp.data.db.ForecastDb
import com.example.harsh.weatherapp.data.server.ForecastServer
import com.example.harsh.weatherapp.domain.model.ForecastList
import com.example.harsh.weatherapp.extensions.firstResult

class ForecastProvider(private val sources: List<ForecastDataSource> = ForecastProvider.SOURCES) {

    companion object {
        const val DAY_IN_MILLIS = 1000 * 60 * 60 * 24
        val SOURCES = listOf(ForecastDb(), ForecastServer())
    }

    fun requestByZipcode(zipcode: Long, days: Int): ForecastList = sources.firstResult { requestSource(it, days, zipcode) }

    fun requestSource(source: ForecastDataSource, days: Int, zipcode: Long): ForecastList? {
        val res = source.requestForecastByZipCode(zipcode, todayTimeSpan())

        return if (res != null && res.size >= days) res else null
    }

    private fun todayTimeSpan() = System.currentTimeMillis() / DAY_IN_MILLIS * DAY_IN_MILLIS
}
