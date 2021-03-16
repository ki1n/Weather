package com.example.nikolaiturev.weather.domain.repository

import com.example.nikolaiturev.weather.domain.entity.Weather
import io.reactivex.Single

interface WeatherGeolocationRepository {

    fun get(): Single<Weather>

}
