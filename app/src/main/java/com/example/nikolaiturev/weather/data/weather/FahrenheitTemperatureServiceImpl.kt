package com.example.nikolaiturev.weather.data.weather

import com.example.nikolaiturev.weather.domain.service.FahrenheitTemperatureService
import com.example.nikolaiturev.weather.util.Const
import kotlin.math.roundToInt

class FahrenheitTemperatureServiceImpl : FahrenheitTemperatureService {

    override fun translateCelsius(value: String): Int {
        return ((value.toInt() - Const.ICE_MELTING_POINT) / Const.REAUMUR_DEGREE).roundToInt()
    }

    override fun translateFahrenheit(value: String): Int {
        return ((value.toInt() * Const.REAUMUR_DEGREE) + Const.ICE_MELTING_POINT).roundToInt()
    }

}
