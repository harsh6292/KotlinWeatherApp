package com.example.harsh.weatherapp.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.harsh.weatherapp.R
import com.example.harsh.weatherapp.domain.model.ForecastList
import com.example.harsh.weatherapp.domain.model.ModelForecast
import com.example.harsh.weatherapp.ui.utils.ctx
import com.squareup.picasso.Picasso
import org.jetbrains.anko.find

class ForecastListAdapter(private val weekForecast: ForecastList,
                          private val itemClick: OnItemClickListener):
    RecyclerView.Adapter<ForecastListAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.ctx).inflate(R.layout.layout_item_forecast, parent, false)

        return ViewHolder(view, itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindForecast(weekForecast[position])
    }

    override fun getItemCount(): Int = weekForecast.size

    class ViewHolder(view: View, private val itemClick: OnItemClickListener): RecyclerView.ViewHolder(view) {
        private val iconView = view.find<ImageView>(R.id.icon)
        private val dateView = view.find<TextView>(R.id.date)
        private val descView = view.find<TextView>(R.id.description)
        private val maxTempView = view.find<TextView>(R.id.maxTemperature)
        private val minTempView = view.find<TextView>(R.id.minTemperature)

        fun bindForecast(forecast: ModelForecast) {
            with(forecast) {
                Picasso.with(itemView.ctx).load(iconUrl).into(iconView)
                dateView.text = date
                descView.text = description
                maxTempView.text = "$high"
                minTempView.text = "$low"
                itemView.setOnClickListener { itemClick(this) }
            }
        }
    }

    interface OnItemClickListener {
        operator fun invoke(forecast: ModelForecast)
    }
}
