package com.example.nikolaiturev.weather.di

import com.example.nikolaiturev.weather.data.weather.WeatherRepositoryImpl
import com.example.nikolaiturev.weather.domain.repository.FahrenheitTemperatureRepositoryImpl
import com.example.nikolaiturev.weather.domain.repository.WeatherRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { WeatherRepositoryImpl(get(), get()) as WeatherRepository }
    single { FahrenheitTemperatureRepositoryImpl() }
}
