package com.example.nikolaiturev.weather.presentation.weather

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.nikolaiturev.weather.domain.entity.Weather
import com.example.nikolaiturev.weather.domain.entity.WeatherGeo
import com.example.nikolaiturev.weather.domain.repository.FahrenheitTemperatureRepository
import com.example.nikolaiturev.weather.domain.repository.WeatherGeoRepository
import com.example.nikolaiturev.weather.domain.repository.WeatherRepository
import com.example.nikolaiturev.weather.presentation.base.BaseViewModel

class WeatherViewModel(
    private val weatherRepository: WeatherRepository,
    private val fahrenheitTemperatureRepository: FahrenheitTemperatureRepository,
    private val weatherGeoRepository: WeatherGeoRepository
) : BaseViewModel() {

    val weatherGeoLiveData = MutableLiveData<WeatherGeo>()
    val weatherLiveData = MutableLiveData<Weather>()

    fun translateCelsius(value: String): Int {
        return fahrenheitTemperatureRepository.translateCelsius(value)
    }

    fun translateFahrenheit(value: String): Int {
        return fahrenheitTemperatureRepository.translateFahrenheit(value)
    }

    fun getGeolocationCity(lat: Double, lon: Double) {
        disposable {
            weatherGeoRepository.getGeolocation(lat, lon)
                .doOnSubscribe { isInProgress.value = true }
                .doFinally { isInProgress.value = false }
                .subscribe(
                    {
                        weatherGeoLiveData.value = it
                        Log.e("TAG", "getGeolocationCity:  ${it.description}")
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