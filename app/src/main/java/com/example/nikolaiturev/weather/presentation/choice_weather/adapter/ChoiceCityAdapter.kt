package com.example.nikolaiturev.weather.presentation.choice_weather.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nikolaiturev.weather.R
import com.example.nikolaiturev.weather.exstension.click
import com.example.nikolaiturev.weather.exstension.inflate

class ChoiceCityAdapter : RecyclerView.Adapter<ChoiceCityViewHolder>() {

    private var listCity: List<String> = arrayListOf()

    lateinit var onClick: (String) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChoiceCityViewHolder {
        return ChoiceCityViewHolder(parent.inflate(R.layout.item_choice_weather))
    }

    override fun onBindViewHolder(holder: ChoiceCityViewHolder, position: Int) {
        val item = listCity[position]
        with(holder) {
            setData(item)
            itemView.click {
                onClick.invoke(item)
            }
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