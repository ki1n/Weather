package com.example.nikolaiturev.weather.data.weather

import com.example.nikolaiturev.weather.data.api.WeatherApi
import com.example.nikolaiturev.weather.domain.entity.Weather
import com.example.nikolaiturev.weather.domain.repository.WeatherGeolocationRepository
import com.example.nikolaiturev.weather.domain.service.LocationService
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class WeatherGeolocationRepositoryImpl(
    private val weatherApi: WeatherApi,
    private val weatherMapper: WeatherMapper,
    private val locationService: LocationService
) : WeatherGeolocationRepository {

    override fun get(): Single<Weather> =
        locationService.getLastKnownLocation()
            .flatMap { location ->
                weatherApi.getWeatherDataByGeolocation(location.latitude, location.longitude)
                    .map { weatherMapper.transform(it) }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}
