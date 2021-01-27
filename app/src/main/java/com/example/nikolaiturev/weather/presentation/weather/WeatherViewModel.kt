package com.example.nikolaiturev.weather.presentation.weather

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.nikolaiturev.weather.data.api.response.BaseWeatherResponse
import com.example.nikolaiturev.weather.domain.repository.WeatherRepository
import com.example.nikolaiturev.weather.presentation.base.BaseViewModel

class WeatherViewModel(
    private val weatherRepository: WeatherRepository
): BaseViewModel() {

    private val weatherLiveData = MutableLiveData<BaseWeatherResponse>()

    fun getWeather(){
        disposable {
            weatherRepository.get()
                .subscribe(
                    {
                        Log.d("wetherView", " = " + it.toString())
                    },
                    {
                        postMessage(it.localizedMessage)
                    }
                )
        }
    }
}