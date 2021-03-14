package com.example.nikolaiturev.weather.presentation.weather

import androidx.lifecycle.MutableLiveData
import com.example.nikolaiturev.weather.domain.entity.Weather
import com.example.nikolaiturev.weather.domain.entity.WeatherGeolocation
import com.example.nikolaiturev.weather.domain.repository.WeatherGeolocationRepository
import com.example.nikolaiturev.weather.domain.repository.WeatherRepository
import com.example.nikolaiturev.weather.domain.service.FahrenheitTemperatureService
import com.example.nikolaiturev.weather.presentation.base.BaseViewModel

class WeatherViewModel(
    private val weatherRepository: WeatherRepository,
    private val fahrenheitTemperatureRepository: FahrenheitTemperatureService,
    private val weatherGeolocationRepository: WeatherGeolocationRepository,
) : BaseViewModel() {

    val weatherGeoLiveData = MutableLiveData<WeatherGeolocation>()
    val weatherLiveData = MutableLiveData<Weather>()

    fun translateCelsius(value: String): Int {
        return fahrenheitTemperatureRepository.translateCelsius(value)
    }

    fun translateFahrenheit(value: String): Int {
        return fahrenheitTemperatureRepository.translateFahrenheit(value)
    }

    fun getGeolocationCity() {
        disposable {
            weatherGeolocationRepository.get()
                .doOnSubscribe { isInProgress.value = true }
                .doFinally { isInProgress.value = false }
                .subscribe(
                    {
                        weatherGeoLiveData.value = it
                    },
                    {
                        postMessage(it.localizedMessage)
                    }
                )
        }
    }

    fun getWeather(city: String) {
        disposable {
            weatherRepository.get(city)
                .doOnSubscribe { isInProgress.value = true }
                .doFinally { isInProgress.value = false }
                .subscribe(
                    {
                        weatherLiveData.value = it
                    },
                    {
                        postMessage(it.localizedMessage)
                    }
                )
        }
    }
}
