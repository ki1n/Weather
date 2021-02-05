package com.example.nikolaiturev.weather.data.weather

import com.example.nikolaiturev.weather.data.api.WeatherApi
import com.example.nikolaiturev.weather.domain.entity.WeatherGeo
import com.example.nikolaiturev.weather.domain.repository.WeatherGeoRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class WeatherGeoRepositoryImpl(
    private val weatherApi: WeatherApi,
    private val weatherGeoMapper: WeatherGeoMapper
) : WeatherGeoRepository {

    override fun getGeolocation(lat: Double, lon: Double): Single<WeatherGeo> =
        weatherApi.getWeatherDataByGeolocation(lat, lon)
            .map {
                weatherGeoMapper.transform(it)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}