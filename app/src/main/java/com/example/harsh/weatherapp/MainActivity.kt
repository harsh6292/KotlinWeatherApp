package com.example.harsh.weatherapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.harsh.weatherapp.data.Request
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.longToast
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {

    private val items = listOf(
        "Mon 12/16 - Sunny - 30/31",
        "Tue 12/17 - Sunny - 31/32",
        "Wed 12/18 - Sunny - 32/33",
        "Thurs 12/19 - Sunny - 33/34",
        "Fri 12/20 - Sunny - 34/35",
        "Sat 12/21 - Sunny - 35/36",
        "Sun 12/22 - Sunny - 36/37"
    )

    private val url = "http://api.openweathermap.org/data/2.5/forecast/daily?" +
            "APPID=15646a06818f61f7b8d7823ca833e1ce&q=94043&mode=json&units=metric&cnt=7"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val forecastList = findViewById<RecyclerView>(R.id.forecast_list)
        forecastList.layoutManager = LinearLayoutManager(this)
        forecastList.adapter = ForecastListAdapter(items)

        doAsync {
            Request(url).run()
            uiThread { longToast("Request Performed!") }
        }
    }
}
