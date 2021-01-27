package com.example.nikolaiturev.weather.domain.repository

import com.example.nikolaiturev.weather.data.api.response.BaseWeatherResponse
import io.reactivex.Single

interface WeatherRepository {

    fun get() : Single<BaseWeatherResponse>

}