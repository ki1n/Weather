package com.example.nikolaiturev.weather.data.weather

import com.example.nikolaiturev.weather.domain.service.ConvertTemperatureService
import com.example.nikolaiturev.weather.util.Сonstants
import kotlin.math.roundToInt

class ConvertTemperatureServiceImpl : ConvertTemperatureService {

    override fun translateCelsius(value: String): Int {
        return ((value.toInt() - Сonstants.ICE_MELTING_POINT) / Сonstants.REAUMUR_DEGREE).roundToInt()
    }

    override fun translateFahrenheit(value: String): Int {
        return ((value.toInt() * Сonstants.REAUMUR_DEGREE) + Сonstants.ICE_MELTING_POINT).roundToInt()
    }

}
