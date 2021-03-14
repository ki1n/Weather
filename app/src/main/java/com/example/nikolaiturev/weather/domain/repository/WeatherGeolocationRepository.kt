package com.example.nikolaiturev.weather.domain.repository

import com.example.nikolaiturev.weather.domain.entity.WeatherGeolocation
import io.reactivex.Single

interface WeatherGeolocationRepository {

    fun get(): Single<WeatherGeolocation>

}
