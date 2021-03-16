package com.example.nikolaiturev.weather.di

import com.example.nikolaiturev.weather.data.city.CityRepositoryImpl
import com.example.nikolaiturev.weather.data.location.LocationServiceImpl
import com.example.nikolaiturev.weather.data.weather.ConvertTemperatureServiceImpl
import com.example.nikolaiturev.weather.data.weather.WeatherGeolocationRepositoryImpl
import com.example.nikolaiturev.weather.data.weather.WeatherRepositoryImpl
import com.example.nikolaiturev.weather.domain.repository.CityRepository
import com.example.nikolaiturev.weather.domain.repository.WeatherGeolocationRepository
import com.example.nikolaiturev.weather.domain.repository.WeatherRepository
import com.example.nikolaiturev.weather.domain.service.ConvertTemperatureService
import com.example.nikolaiturev.weather.domain.service.LocationService
import org.koin.dsl.module

val repositoryModule = module {

    single { WeatherRepositoryImpl(get(), get()) as WeatherRepository }
    single { WeatherGeolocationRepositoryImpl(get(), get(), get()) as WeatherGeolocationRepository }
    single { ConvertTemperatureServiceImpl() as ConvertTemperatureService }
    single { LocationServiceImpl(get()) as LocationService }
    single { CityRepositoryImpl() as CityRepository }

}
