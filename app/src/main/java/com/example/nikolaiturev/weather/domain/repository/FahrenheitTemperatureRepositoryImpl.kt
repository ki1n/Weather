package com.example.nikolaiturev.weather.domain.repository

import kotlin.math.roundToInt

class FahrenheitTemperatureRepositoryImpl : FahrenheitTemperatureRepository {

    override fun translateCelsius(value: String): Int {
        return ((value.toInt() - 32) / 1.8).roundToInt()
    }

    override fun translateFahrenheit(value: String): Int {
        return ((value.toInt() * 1.8) + 32).roundToInt()
    }

}