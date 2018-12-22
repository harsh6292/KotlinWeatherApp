package com.example.harsh.weatherapp.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.harsh.weatherapp.R
import com.example.harsh.weatherapp.domain.model.ForecastList
import com.example.harsh.weatherapp.domain.model.ModelForecast
import com.example.harsh.weatherapp.ui.utils.ctx
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.layout_item_forecast.*

class ForecastListAdapter(private val weekForecast: ForecastList,
                          private val itemClick: (ModelForecast) -> Unit):
    RecyclerView.Adapter<ForecastListAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.ctx).inflate(R.layout.layout_item_forecast, parent, false)

        return ViewHolder(view, itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindForecast(weekForecast[position])
    }

    override fun getItemCount(): Int = weekForecast.size

    class ViewHolder(override val containerView: View, private val itemClick: (ModelForecast) -> Unit):
            RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bindForecast(forecast: ModelForecast) {
            with(forecast) {
                Picasso.with(itemView.ctx).load(iconUrl).into(icon)
                dateText.text = date
                descriptionText.text = description
                maxTemperature.text = "$high"
                minTemperature.text = "$low"
                itemView.setOnClickListener { itemClick(this) }
            }
        }
    }
}
