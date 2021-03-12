package com.example.nikolaiturev.weather.di

import com.example.nikolaiturev.weather.presentation.choice_city.adapter.ChoiceCityAdapter
import org.koin.dsl.module

val adapterModule = module {

    single { ChoiceCityAdapter() }

}