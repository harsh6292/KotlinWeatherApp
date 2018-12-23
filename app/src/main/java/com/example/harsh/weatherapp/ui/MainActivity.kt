package com.example.harsh.weatherapp.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import com.example.harsh.weatherapp.R
import com.example.harsh.weatherapp.domain.commands.RequestForecastCommand
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.*

class MainActivity : AppCompatActivity(), ToolbarManager {

    override val toolbar by lazy { find<Toolbar>(R.id.toolbar) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initToolbar()

        forecastList.layoutManager = LinearLayoutManager(this)
        attachToScroll(forecastList)

        doAsync {
            val result = RequestForecastCommand(27606).execute()
             uiThread {
                longToast("Request Performed!")
                val adapter = ForecastListAdapter(result) {
                    startActivity<DetailActivity>(DetailActivity.ID to it.forecastId, DetailActivity.CITY_NAME to result.city)
                }
                forecastList.adapter = adapter
                toolbarTitle = "${result.city} (${result.country})"
            }

            return@doAsync
        }
    }
}
