package com.example.nikolaiturev.weather.presentation.weather

import com.bumptech.glide.Glide
import com.example.nikolaiturev.weather.BuildConfig
import com.example.nikolaiturev.weather.R
import com.example.nikolaiturev.weather.exstension.show
import com.example.nikolaiturev.weather.presentation.base.BaseActivity
import com.example.nikolaiturev.weather.presentation.choice_weather.ChoiceWeatherDialog
import kotlinx.android.synthetic.main.activity_weather.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeatherActivity : BaseActivity() {

    override var layoutId: Int = R.layout.activity_weather

    private val viewModel by viewModel<WeatherViewModel>()
    private val choiceWeatherDialog = ChoiceWeatherDialog()

    override fun iniView() {

        viewModel.getWeather("London")

        viewModel.weatherLiveData.observe(this, { weather ->
            with(weather) {

                tvTemperature.text = getString(
                    R.string.temperatureCelsius,
                    Math.round(String.format("%.1f", tempС).toFloat()).toString()
                )
                tvCity.text = name
                tvСlimate.text = description
                tvWindTxt.text = getString(R.string.ms, speed.toString())
                tvPressureTxt.text = getString(R.string.mm_rt_ct, pressure.toString())
                tvHumidityTxt.text = getString(R.string.percent, humidity.toString())

                Glide.with(imvWeather.context)
                    .load(BuildConfig.ICON_URL + icon + "@2x.png")
                    .error(R.drawable.ic_error_connect_icon)
                    .into(imvWeather)
            }
        })

        viewModel.isInProgress.observe(this, {
            progressBarWeather.show(it)
        })

        rbChoice.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.radioC -> {
                    tvTemperature.text = getString(
                        R.string.temperatureCelsius,
                        viewModel.translateCelsius(tvTemperature.text.split("F")[0]).toString()
                    )
                }
                R.id.radioF -> {
                    tvTemperature.text = getString(
                        R.string.temperatureFaringates,
                        viewModel.translateFahrenheit(tvTemperature.text.split("º")[0]).toString()
                    )
                }
            }
        }

        tvSweepCity.setOnClickListener {
            choiceWeatherDialog.show(supportFragmentManager, "ChoiceWeatherDialog")
        }
    }
}