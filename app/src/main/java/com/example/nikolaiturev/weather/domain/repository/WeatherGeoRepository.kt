package com.example.nikolaiturev.weather.domain.repository

import com.example.nikolaiturev.weather.domain.entity.WeatherGeo
import io.reactivex.Single

interface WeatherGeoRepository {

    fun getGeolocation(lat: Double, lon: Double): Single<WeatherGeo>

}