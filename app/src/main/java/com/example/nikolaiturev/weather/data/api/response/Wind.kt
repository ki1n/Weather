package com.example.nikolaiturev.weather.data.api.response

import com.google.gson.annotations.SerializedName

data class Wind(
        @SerializedName("speed")
        val speed: Float
)