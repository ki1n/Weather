package com.example.nikolaiturev.weather.presentation.weather

import com.bumptech.glide.Glide
import com.example.nikolaiturev.weather.BuildConfig
import com.example.nikolaiturev.weather.R
import com.example.nikolaiturev.weather.presentation.base.BaseActivity
import kotlinx.android.synthetic.main.activity_weather.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeatherActivity : BaseActivity() {
    override var layoutId: Int = R.layout.activity_weather

    private val viewModel by viewModel<WeatherViewModel>()

    override fun iniView() {
        viewModel.getWeather()

        viewModel.weatherLiveData.observe(this, { weatherResponce ->
            tvTemperature.text = weatherResponce.main.temp.toInt().toString()
            tvCity.text = weatherResponce.name
            tvСlimate.text = weatherResponce.weather[0].main

            val png = "@2x.png"
            val iconUrl = BuildConfig.ICON_URL + weatherResponce.weather[0].icon + png
            Glide.with(imvWeather.context)
                .load(iconUrl)
                .error(R.drawable.ic_path_2)
                .into(imvWeather)

            tvWindTxt.text = getString(R.string.ms, weatherResponce.wind.speed.toString())
            tvPressureTxt.text = getString(R.string.mm_rt_ct, weatherResponce.main.pressure.toString())
            tvHumidityTxt.text = getString(R.string.percent, weatherResponce.main.humidity.toString())
        })
    }
}