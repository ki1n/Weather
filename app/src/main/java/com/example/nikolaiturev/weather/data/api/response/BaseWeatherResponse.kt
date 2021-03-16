package com.example.nikolaiturev.weather.data.api.response

import com.google.gson.annotations.SerializedName

data class BaseWeatherResponse(
        @SerializedName("weather")
        val weather: List<WeatherResponse>,

        @SerializedName("main")
        val main: MainResponse,

        @SerializedName("wind")
        val wind: WindResponse,

        @SerializedName("name")
        val name: String,
)

