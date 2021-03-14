package com.example.nikolaiturev.weather.presentation.choice_city

import androidx.lifecycle.MutableLiveData
import com.example.nikolaiturev.weather.domain.repository.CityRepository
import com.example.nikolaiturev.weather.presentation.base.BaseViewModel
import com.example.nikolaiturev.weather.presentation.base.FilterListMediatorLiveData
import java.util.*

class ChoiceCityViewModel(cityRepository: CityRepository) : BaseViewModel() {

    val listCity = MutableLiveData<List<String>>()

    val listCityFilter = MutableLiveData<String>()

    private val arrayCity = cityRepository.getCity()

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
        listCity.value = arrayCity
    }
}
