package com.example.nikolaiturev.weather.domain.repository

class FahrenheitTemperatureRepositoryImpl : FahrenheitTemperatureRepository {

    override fun translateCelsius(value: String) : Int {
        return value.toInt() - 273
    }

    override fun translateFahrenheit(value: String) : Int {
        return ((value.toInt() * 1.8) - 460).toInt()
    }
}