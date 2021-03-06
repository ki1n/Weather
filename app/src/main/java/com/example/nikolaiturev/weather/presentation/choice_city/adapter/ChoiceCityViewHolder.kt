package com.example.nikolaiturev.weather.presentation.choice_city.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nikolaiturev.weather.R

class ChoiceCityViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    var textCity: TextView = view.findViewById(R.id.tvCity)

    fun setData(item: String) {
        textCity.text = item
    }
}
