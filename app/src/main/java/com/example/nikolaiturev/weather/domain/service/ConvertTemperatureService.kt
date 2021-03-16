package com.example.nikolaiturev.weather.domain.service

interface ConvertTemperatureService {

    fun translateCelsius(value: String) : Int

    fun translateFahrenheit(value: String) : Int

}
