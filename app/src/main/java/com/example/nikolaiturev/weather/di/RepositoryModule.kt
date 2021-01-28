package com.example.nikolaiturev.weather.di

import com.example.nikolaiturev.weather.data.repository.WeatherRepositoryImpl
import com.example.nikolaiturev.weather.domain.repository.CalculRepositoryImpl
import com.example.nikolaiturev.weather.domain.repository.WeatherRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { WeatherRepositoryImpl(get()) as WeatherRepository }
    single { CalculRepositoryImpl() }
}