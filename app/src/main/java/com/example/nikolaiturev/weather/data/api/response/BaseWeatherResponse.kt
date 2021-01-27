package com.example.nikolaiturev.weather.data.api.response

import com.google.gson.annotations.SerializedName

data class BaseWeatherResponse(
        @SerializedName("weather")
        val weather: List<Weather>,
        @SerializedName("main")
        val main: Main,
        @SerializedName("wind")
        val wind: Wind,
        @SerializedName("name")
        val name: String,
)

