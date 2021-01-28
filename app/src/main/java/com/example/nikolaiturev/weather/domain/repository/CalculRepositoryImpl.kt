package com.example.nikolaiturev.weather.domain.repository

class CalculRepositoryImpl : CalculRepository {
    // Пример: (50°F - 32) : 1,8 = 10°C
    override fun translateCelsius(value: String) : Int {
        return ((value.toInt() - 32) / 1.8).toInt()
    }

   // 10°C x 1,8 + 32 = 50°F
    override fun translateFahrenheit(value: String) : Int {
        return ((value.toInt() * 1.8) + 32).toInt()
    }


}