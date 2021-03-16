package com.example.nikolaiturev.weather.data.weather

import com.example.nikolaiturev.weather.domain.service.ConvertTemperatureService
import com.example.nikolaiturev.weather.util.Сonstants

class ConvertTemperatureServiceImpl : ConvertTemperatureService {

    override fun translateCelsius(temperature: Double): Double =
        (temperature - Сonstants.ICE_MELTING_POINT) / Сonstants.REAUMUR_DEGREE

    override fun translateFahrenheit(temperature: Double): Double =
        (temperature * Сonstants.REAUMUR_DEGREE) + Сonstants.ICE_MELTING_POINT

}
