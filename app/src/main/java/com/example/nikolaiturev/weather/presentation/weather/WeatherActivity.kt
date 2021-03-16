package com.example.nikolaiturev.weather.presentation.weather

import com.bumptech.glide.Glide
import com.example.nikolaiturev.weather.BuildConfig
import com.example.nikolaiturev.weather.R
import com.example.nikolaiturev.weather.data.permissions.AndroidPermissionsService
import com.example.nikolaiturev.weather.domain.entity.Weather
import com.example.nikolaiturev.weather.exstension.setOnDebouncedClickListener
import com.example.nikolaiturev.weather.presentation.base.BaseActivity
import com.example.nikolaiturev.weather.presentation.choice_city.ChoiceCityDialog
import kotlinx.android.synthetic.main.activity_weather.*
import kotlinx.android.synthetic.main.activity_weather.view.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeatherActivity : BaseActivity() {

    override var layoutId: Int = R.layout.activity_weather

    override val viewModel by viewModel<WeatherViewModel>()
    private val androidPermissionsService: AndroidPermissionsService by inject()

    override fun iniView() {
        androidPermissionsService.init(this)
        viewModel.getPermissionGeolocation()

        tvGeolocation.setOnDebouncedClickListener {
            viewModel.getGeolocationMyCity()
            rbChoice.radioC.isChecked = true
        }

        viewModel.weatherGeoLiveData.observe(this, { weather ->
            bindWeather(weather)
        })

        viewModel.weatherLiveData.observe(this, { weather ->
            bindWeather(weather)
        })

        rbChoice.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.radioC -> {
                    tvTemperature.text = getString(
                        R.string.temperatureCelsius,
                        viewModel.translateCelsius((tvTemperature.text.split("F")[0]).toDouble())
                    )
                }
                R.id.radioF -> {
                    tvTemperature.text = getString(
                        R.string.temperatureFaringates,
                        viewModel.translateFahrenheit((tvTemperature.text.split("º")[0]).toDouble())
                    )
                }
            }
        }

        tvSweepCity.setOnDebouncedClickListener {
            navigateToChoiceCityDialog()
        }
    }

    private fun navigateToChoiceCityDialog() {
        ChoiceCityDialog(
            onResult = {
                viewModel.getWeather(it)
                rbChoice.radioC.isChecked = true
            }
        ).show(supportFragmentManager, null)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        androidPermissionsService.onPermissionsResult(requestCode)
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun bindWeather(weather: Weather) {
        with(weather) {
            tvTemperature.text = getString(
                R.string.temperatureCelsius, tempС
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
    }
}





