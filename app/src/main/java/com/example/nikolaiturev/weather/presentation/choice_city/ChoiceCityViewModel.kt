package com.example.nikolaiturev.weather.presentation.choice_city

import androidx.lifecycle.MutableLiveData
import com.example.nikolaiturev.weather.presentation.base.BaseViewModel
import com.example.nikolaiturev.weather.presentation.base.FilterListMediatorLiveData
import java.util.*

class ChoiceCityViewModel : BaseViewModel() {

    val listCity = MutableLiveData<List<String>>()

    val listCityFilter = MutableLiveData<String>()

    private val arraysCity =
        arrayListOf("Омск", "Москва", "Петербург", "Самара", "Сочи", "Берлин", "Париж", "Лондон")

    init {
        getCity()
    }

    val nameFilterCity by lazy {
        FilterListMediatorLiveData(listCity, listCityFilter) { item, filter ->
            if (filter.isNullOrEmpty()) {
                true
            } else {
                item.toLowerCase(Locale.ROOT).contains(filter)
            }
        }
    }

    private fun getCity() {
        listCity.value = arraysCity
    }
}