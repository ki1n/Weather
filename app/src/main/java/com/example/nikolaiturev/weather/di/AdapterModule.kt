package com.example.nikolaiturev.weather.di

import com.example.nikolaiturev.weather.presentation.choice_weather.adapter.ChoiceCityAdapter
import org.koin.dsl.module

val adapterModule = module {

    single { ChoiceCityAdapter() }

}