package com.example.nikolaiturev.weather.presentation.weather

import androidx.lifecycle.MutableLiveData
import com.example.nikolaiturev.weather.domain.entity.Weather
import com.example.nikolaiturev.weather.domain.repository.FahrenheitTemperatureRepositoryImpl
import com.example.nikolaiturev.weather.domain.repository.WeatherRepository
import com.example.nikolaiturev.weather.presentation.base.BaseViewModel

class WeatherViewModel(
    private val weatherRepository: WeatherRepository,
    private val fahrenheitTemperatureRepositoryImpl: FahrenheitTemperatureRepositoryImpl
) : BaseViewModel() {

    val weatherLiveData = MutableLiveData<Weather>()

    fun translateCelsius(value: String): Int {
        return fahrenheitTemperatureRepositoryImpl.translateCelsius(value)
    }

    fun translateFahrenheit(value: String): Int {
        return fahrenheitTemperatureRepositoryImpl.translateFahrenheit(value)
    }

    fun getWeather(city: String) {
        disposable {
            weatherRepository.get(city)
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