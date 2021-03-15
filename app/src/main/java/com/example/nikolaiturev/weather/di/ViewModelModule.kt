package com.example.nikolaiturev.weather.di

import com.example.nikolaiturev.weather.presentation.choice_city.ChoiceCityViewModel
import com.example.nikolaiturev.weather.presentation.weather.WeatherViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { WeatherViewModel(get(), get(), get(), get(), get()) }
    viewModel { ChoiceCityViewModel(get()) }

}
