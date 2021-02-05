package com.example.nikolaiturev.weather.domain.repository

interface FahrenheitTemperatureRepository {

    fun translateCelsius(value: String) : Int

    fun translateFahrenheit(value: String) : Int

}