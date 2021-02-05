package com.example.nikolaiturev.weather.presentation.choice_weather.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nikolaiturev.weather.R

class ChoiceCityViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    var textCity: TextView = view.findViewById(R.id.tvCity)
    var imgCheckCity: ImageView = view.findViewById(R.id.imgCheckCity)

    fun setData(item: String) {
        textCity.text = item
    }
}