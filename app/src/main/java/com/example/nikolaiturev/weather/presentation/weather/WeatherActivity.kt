package com.example.nikolaiturev.weather.presentation.weather

import com.example.nikolaiturev.weather.R
import com.example.nikolaiturev.weather.presentation.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeatherActivity : BaseActivity() {
    override var layoutId: Int = R.layout.activity_weather

    private val viewModel by viewModel<WeatherViewModel>()

    override fun iniView() {}
}