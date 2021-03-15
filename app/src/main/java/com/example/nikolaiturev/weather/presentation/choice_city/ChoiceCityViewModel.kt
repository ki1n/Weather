package com.example.nikolaiturev.weather.presentation.choice_city

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.example.nikolaiturev.weather.domain.entity.City
import com.example.nikolaiturev.weather.domain.repository.CityRepository
import com.example.nikolaiturev.weather.presentation.base.BaseViewModel
import com.example.nikolaiturev.weather.presentation.base.FilterListMediatorLiveData
import java.util.*

class ChoiceCityViewModel(private val cityRepository: CityRepository) : BaseViewModel() {

    val listCity = MutableLiveData<List<City>>()

    val listCityFilter = MutableLiveData<City>()

    @SuppressLint("CheckResult")
    private fun getCityAll() {
        cityRepository.getCity()
            .doOnSubscribe { isInProgress.value = true }
            .doFinally { isInProgress.value = false }
            .subscribe(
                {
                    listCity.value = it
                }, {
                    postMessage(it.localizedMessage)
                }
            )
    }

    init {
        getCityAll()
    }

    val nameFilterCity by lazy {
        FilterListMediatorLiveData(listCity, listCityFilter) { item, filter ->
            if (filter?.name!!.isEmpty()) {
                true
            } else {
                item.name.toLowerCase(Locale.ROOT).contains(filter.name)
            }
        }
    }
}
