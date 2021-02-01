package com.example.nikolaiturev.weather.di

import com.example.nikolaiturev.weather.presentation.choice_weather.adapter.ChoiceWeatherAdapter
import org.koin.dsl.module

val adapterModule = module {

    single { ChoiceWeatherAdapter() }

}