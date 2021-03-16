package com.example.nikolaiturev.weather.presentation.weather

import androidx.lifecycle.MutableLiveData
import com.example.nikolaiturev.weather.data.permissions.AndroidPermissionsService
import com.example.nikolaiturev.weather.domain.entity.Weather
import com.example.nikolaiturev.weather.domain.repository.WeatherGeolocationRepository
import com.example.nikolaiturev.weather.domain.repository.WeatherRepository
import com.example.nikolaiturev.weather.domain.service.ConvertTemperatureService
import com.example.nikolaiturev.weather.presentation.base.BaseViewModel
import com.example.nikolaiturev.weather.util.Сonstants

class WeatherViewModel(
    private val weatherRepository: WeatherRepository,
    private val fahrenheitTemperatureRepository: ConvertTemperatureService,
    private val weatherGeolocationRepository: WeatherGeolocationRepository,
    private val androidPermissionsService: AndroidPermissionsService
) : BaseViewModel() {

    val weatherGeoLiveData = MutableLiveData<Weather>()
    val weatherLiveData = MutableLiveData<Weather>()

    fun translateCelsius(value: String): Int {
        return fahrenheitTemperatureRepository.translateCelsius(value)
    }

    fun translateFahrenheit(value: String): Int {
        return fahrenheitTemperatureRepository.translateFahrenheit(value)
    }

    fun getGeolocationMyCity() {
        disposable {
            weatherGeolocationRepository.get()
                .doOnSubscribe { isInProgress.value = true }
                .doFinally { isInProgress.value = false }
                .subscribe(
                    {
                        weatherGeoLiveData.value = it
                    },
                    {
                        postMessage(it.localizedMessage)
                    }
                )
        }
    }

    fun getWeather(city: String) {
        disposable {
            weatherRepository.get(city)
                .doOnSubscribe { isInProgress.value = true }
                .doFinally { isInProgress.value = false }
                .subscribe(
                    {
                        weatherLiveData.value = it
                    },
                    {
                        postMessage(it.localizedMessage)
                    }
                )
        }
    }

    fun getPermissionGeolocation() {
        androidPermissionsService.requestPermissions(
            requestCode = Сonstants.PERMISSIONS_REQUEST_CODE_LOCATION,
            permissions = listOf(
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ),
            onPermissionsResultsCallback = { requestCode, result ->
                if (requestCode == Сonstants.PERMISSIONS_REQUEST_CODE_LOCATION && result) {
                    getGeolocationMyCity()
                }
            }
        )
    }
}
