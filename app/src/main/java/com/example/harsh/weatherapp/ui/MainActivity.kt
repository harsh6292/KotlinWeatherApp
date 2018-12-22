package com.example.harsh.weatherapp.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.harsh.weatherapp.R
import com.example.harsh.weatherapp.domain.commands.RequestForecastCommand
import com.example.harsh.weatherapp.domain.model.ModelForecast
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.longToast
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {

    private val url = "http://api.openweathermap.org/data/2.5/forecast/daily?" +
            "APPID=15646a06818f61f7b8d7823ca833e1ce&q=94043&mode=json&units=metric&cnt=7"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val forecastList = findViewById<RecyclerView>(R.id.forecast_list)
        forecastList.layoutManager = LinearLayoutManager(this)

        doAsync {
            val result = RequestForecastCommand("27606").execute()
            uiThread {
                longToast("Request Performed!")
                forecastList.adapter = ForecastListAdapter(result,
                    object : ForecastListAdapter.OnItemClickListener {
                        override fun invoke(forecast: ModelForecast) {
                            toast("Date is ${forecast.date}")
                        }
                    })
            }
        }
    }
}
