package com.example.nikolaiturev.weather.data.weather

import com.example.nikolaiturev.weather.domain.service.FahrenheitTemperatureService
import kotlin.math.roundToInt

class FahrenheitTemperatureServiceImpl : FahrenheitTemperatureService {

    // ЧТо за цифры? Имеется газвание у них?
    // ctrl+alt+l
    override fun translateCelsius(value: String): Int {
        return ((value.toInt() - 32) / 1.8).roundToInt()
    }

    override fun translateFahrenheit(value: String): Int {
        return ((value.toInt() * 1.8) + 32).roundToInt()
    }

}
