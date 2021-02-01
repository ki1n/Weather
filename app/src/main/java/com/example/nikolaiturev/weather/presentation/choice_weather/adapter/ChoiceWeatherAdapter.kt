package com.example.nikolaiturev.weather.presentation.choice_weather.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nikolaiturev.weather.R
import com.example.nikolaiturev.weather.exstension.inflate

class ChoiceWeatherAdapter : RecyclerView.Adapter<ChoiceWeatherViewHolder>() {
    private var listCity: List<String> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChoiceWeatherViewHolder {
        return ChoiceWeatherViewHolder(parent.inflate(R.layout.item_choice_weather))
    }

    override fun onBindViewHolder(holder: ChoiceWeatherViewHolder, position: Int) {
        val item = listCity[position]
        with(holder) {
            setData(item)
        }
    }

    override fun getItemCount(): Int {
        return listCity.size
    }

    fun updateCityWeather(list: List<String>) {
        listCity = list
        notifyDataSetChanged()
    }
}