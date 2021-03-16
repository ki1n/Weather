package com.example.nikolaiturev.weather.domain.entity

data class Weather(
    val name: String,
    val icon: String,
    val tempС: Double,
    val description: String,
    val speed: Float,
    val pressure: Int,
    val humidity: Int,
)
