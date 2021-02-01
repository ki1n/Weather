package com.example.nikolaiturev.weather.domain.repository

class FahrenheitTemperatureRepositoryImpl : FahrenheitTemperatureRepository {

    override fun translateCelsius(value: String): Int {
        return Math.round((value.toInt() - 32) / 1.8).toInt()
    }

    override fun translateFahrenheit(value: String): Int {
        return Math.round((value.toInt() * 1.8) + 32).toInt()
    }
}