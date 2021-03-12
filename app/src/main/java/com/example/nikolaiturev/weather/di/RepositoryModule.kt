package com.example.nikolaiturev.weather.di

import com.example.nikolaiturev.weather.data.weather.WeatherGeoRepositoryImpl
import com.example.nikolaiturev.weather.data.weather.WeatherRepositoryImpl
import com.example.nikolaiturev.weather.domain.repository.FahrenheitTemperatureRepository
import com.example.nikolaiturev.weather.domain.repository.FahrenheitTemperatureRepositoryImpl
import com.example.nikolaiturev.weather.domain.repository.WeatherGeoRepository
import com.example.nikolaiturev.weather.domain.repository.WeatherRepository
import org.koin.dsl.module

val repositoryModule = module {

    single { WeatherRepositoryImpl(get(), get()) as WeatherRepository }
    single { WeatherGeoRepositoryImpl(get(), get()) as WeatherGeoRepository }
    single { FahrenheitTemperatureRepositoryImpl() as FahrenheitTemperatureRepository }

}
