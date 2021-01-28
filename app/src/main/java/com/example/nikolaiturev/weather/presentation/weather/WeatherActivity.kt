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

    //TODO: pls update this func
    override fun iniView() {
        viewModel.getWeather()

        viewModel.weatherLiveData.observe(this, { weatherResponce ->

            tvTemperature.text =
                viewModel.translateCelsius(weatherResponce.main.temp.toInt().toString()).toString()
            tvCity.text = weatherResponce.name
            tvСlimate.text = weatherResponce.weather[0].main

            val iconUrl = BuildConfig.ICON_URL + weatherResponce.weather[0].icon + "@2x.png"

            Glide.with(imvWeather.context)
                .load(iconUrl)
                .error(R.drawable.ic_error_connect_icon)
                .into(imvWeather)

            tvWindTxt.text = getString(R.string.ms, weatherResponce.wind.speed.toString())
            tvPressureTxt.text =
                getString(R.string.mm_rt_ct, weatherResponce.main.pressure.toString())
            tvHumidityTxt.text =
                getString(R.string.percent, weatherResponce.main.humidity.toString())

            rbChoice.setOnCheckedChangeListener { _, checkedId ->
                when (checkedId) {
                    R.id.radioC -> {
                        tvTemperature.text =
                            viewModel.translateCelsius(weatherResponce.main.temp.toInt().toString())
                                .toString()
                    }
                    R.id.radioF -> {
                        tvTemperature.text =
                            viewModel.translateFahrenheit(
                                weatherResponce.main.temp.toInt().toString()
                            ).toString()
                    }
                }
            }
        })
    }
}