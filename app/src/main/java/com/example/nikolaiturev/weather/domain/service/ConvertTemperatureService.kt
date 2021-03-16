package com.example.nikolaiturev.weather.domain.service

interface ConvertTemperatureService {

    fun translateCelsius(temperature: Double) : Double

    fun translateFahrenheit(temperature: Double) : Double

}
