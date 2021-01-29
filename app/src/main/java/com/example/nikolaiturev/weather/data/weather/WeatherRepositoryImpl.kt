package com.example.nikolaiturev.weather.data.weather

import com.example.nikolaiturev.weather.data.api.WeatherApi
import com.example.nikolaiturev.weather.domain.entity.Weather
import com.example.nikolaiturev.weather.domain.repository.WeatherRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class WeatherRepositoryImpl(
    private val weatherApi: WeatherApi,
    private val weatherMapper: WeatherMapper,
) : WeatherRepository {

    override fun get(city: String): Single<Weather> =
        weatherApi.getWeatherDataByCity(city)
            .map {
                weatherMapper.transform(it)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}