package com.example.nikolaiturev.weather.presentation.choice_city.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.nikolaiturev.weather.R
import com.example.nikolaiturev.weather.domain.entity.City
import com.example.nikolaiturev.weather.exstension.click
import com.example.nikolaiturev.weather.exstension.inflate

class ChoiceCityAdapter : ListAdapter<City, ChoiceCityViewHolder>(ExerciseDiffCallback()) {

    lateinit var onClick: (String) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChoiceCityViewHolder {
        return ChoiceCityViewHolder(parent.inflate(R.layout.item_choice_weather))
    }

    override fun onBindViewHolder(holder: ChoiceCityViewHolder, position: Int) {
        val item = currentList[position]
        with(holder) {
            setData(item.name)
            itemView.click {
                onClick.invoke(item.name)
            }
        }
    }

    class ExerciseDiffCallback : DiffUtil.ItemCallback<City>() {
        override fun areItemsTheSame(oldItem: City, newItem: City): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: City, newItem: City): Boolean {
            return oldItem == newItem
        }
    }
}
