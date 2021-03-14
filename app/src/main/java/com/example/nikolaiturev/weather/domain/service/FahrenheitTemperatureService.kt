package com.example.nikolaiturev.weather.domain.service

interface FahrenheitTemperatureService {

    fun translateCelsius(value: String) : Int

    fun translateFahrenheit(value: String) : Int

}
