package com.example.nikolaiturev.weather.domain.repository

interface CalculRepository {
    fun translateCelsius(value: String) : Int

    fun translateFahrenheit(value: String) : Int
}