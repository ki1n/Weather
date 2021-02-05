package com.example.nikolaiturev.weather.presentation.choice_weather

import androidx.lifecycle.MutableLiveData
import com.example.nikolaiturev.weather.presentation.base.BaseViewModel
import com.example.nikolaiturev.weather.presentation.base.FilterListMediatorLiveData

class ChoiceCityViewModel : BaseViewModel() {

    val listCity = MutableLiveData<List<String>>()

    val listCityFilter = MutableLiveData<String>()

    private val arraysCity =
        arrayListOf("Omsk", "Moscow", "Petersburg", "Samara", "Sochi", "Berlin", "Paris", "London")

    val nameFilterCity by lazy {
        FilterListMediatorLiveData(listCity, listCityFilter) { item, filter ->
            if (filter.isNullOrEmpty()) {
                true
            } else {
                item.toLowerCase().contains(filter)
            }
        }
    }

    fun getCity() {
        listCity.value = arraysCity
    }
}