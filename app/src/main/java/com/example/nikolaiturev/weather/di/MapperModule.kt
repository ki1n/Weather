package com.example.nikolaiturev.weather.di

import com.example.nikolaiturev.weather.data.weather.WeatherGeoMapper
import com.example.nikolaiturev.weather.data.weather.WeatherMapper
import org.koin.dsl.module

val mapperModule = module {

    factory { WeatherMapper() }
    factory { WeatherGeoMapper() }

}
