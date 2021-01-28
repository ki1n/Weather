package com.example.nikolaiturev.weather.data.api.response

import com.google.gson.annotations.SerializedName

data class MainResponse(
    @SerializedName("temp")
    val temp: Float,
    @SerializedName("pressure")
    val pressure: Int,
    @SerializedName("humidity")
    val humidity: Int,
)