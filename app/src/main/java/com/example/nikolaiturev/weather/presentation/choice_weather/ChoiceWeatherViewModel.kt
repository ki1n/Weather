package com.example.nikolaiturev.weather.presentation.choice_weather

import androidx.lifecycle.MutableLiveData
import com.example.nikolaiturev.weather.presentation.base.BaseViewModel

class ChoiceWeatherViewModel : BaseViewModel()  {

    val listLocation = MutableLiveData<List<String>>()

    private val listCity =
        arrayListOf("Omsk", "Moscow", "Petersburg", "Samara", "Sochi", "Berlin", "Paris", "London")

    fun getCity(){
        listLocation.value = listCity
    }

}