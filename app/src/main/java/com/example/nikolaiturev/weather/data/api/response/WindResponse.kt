package com.example.nikolaiturev.weather.data.api.response

import com.google.gson.annotations.SerializedName

data class WindResponse(
        @SerializedName("speed")
        val speed: Float
)