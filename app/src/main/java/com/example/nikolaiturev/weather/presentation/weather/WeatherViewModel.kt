package com.example.nikolaiturev.weather.presentation.weather

import androidx.lifecycle.MutableLiveData
import com.example.nikolaiturev.weather.data.api.response.BaseWeatherResponse
import com.example.nikolaiturev.weather.domain.repository.WeatherRepository
import com.example.nikolaiturev.weather.domain.repository.FahrenheitTemperatureRepositoryImpl
import com.example.nikolaiturev.weather.presentation.base.BaseViewModel

class WeatherViewModel(
    private val weatherRepository: WeatherRepository,
    private val calculRepositoryImpl: FahrenheitTemperatureRepositoryImpl
) : BaseViewModel() {

    val weatherLiveData = MutableLiveData<BaseWeatherResponse>()

    fun translateCelsius(value: String): Int {
        return calculRepositoryImpl.translateCelsius(value)
    }

    fun translateFahrenheit(value: String): Int {
        return calculRepositoryImpl.translateFahrenheit(value)
    }

    fun getWeather() {
        disposable {
            weatherRepository.get()
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