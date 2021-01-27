package com.example.nikolaiturev.weather.data.api.response

import com.google.gson.annotations.SerializedName

data class Weather(
    @SerializedName("main")
    val main: String,
    @SerializedName("icon")
    val icon: String
)