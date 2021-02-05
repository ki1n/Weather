package com.example.nikolaiturev.weather.presentation.weather

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import com.bumptech.glide.Glide
import com.example.nikolaiturev.weather.BuildConfig
import com.example.nikolaiturev.weather.R
import com.example.nikolaiturev.weather.exstension.click
import com.example.nikolaiturev.weather.exstension.show
import com.example.nikolaiturev.weather.presentation.base.BaseActivity
import com.example.nikolaiturev.weather.presentation.choice_weather.ChoiceCityDialog
import com.google.android.gms.location.*
import kotlinx.android.synthetic.main.activity_weather.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeatherActivity : BaseActivity() {
    private val TAG = "WeatherActivity"

    override var layoutId: Int = R.layout.activity_weather

    private val viewModel by viewModel<WeatherViewModel>()

    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private val PERMISSION_ID = 101

    @SuppressLint("MissingPermission")
    override fun iniView() {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        tvGeolocation.setOnClickListener {
            Log.d(TAG, checkPermission().toString())
            Log.d(TAG, isLocationEnabled().toString())
            ActivityResultContracts.RequestPermission()
            fusedLocationProviderClient.lastLocation.addOnSuccessListener { location ->
                viewModel.getGeolocationCity(location.latitude, location.longitude)
            }
            getLastLocation()
        }

        viewModel.getWeather("Moscow")

        viewModel.weatherGeoLiveData.observe(this, { weatherGeo ->
            with(weatherGeo) {
                tvTemperature.text = getString(
                    R.string.temperatureCelsius,
                    Math.round(kotlin.String.format("%.1f", tempС).toFloat()).toString()
                )
                tvCity.text = name
                tvСlimate.text = description
                tvWindTxt.text = getString(R.string.ms, speed.toString())
                tvPressureTxt.text = getString(R.string.mm_rt_ct, pressure.toString())
                tvHumidityTxt.text = getString(R.string.percent, humidity.toString())

                com.bumptech.glide.Glide.with(imvWeather.context)
                    .load(BuildConfig.ICON_URL + icon + "@2x.png")
                    .error(R.drawable.ic_error_connect_icon)
                    .into(imvWeather)
            }
        })

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

        tvSweepCity.click {
            navigateToChoiceCityDialog()
        }
    }

    private fun navigateToChoiceCityDialog() {
        ChoiceCityDialog(
            onResult = {
                viewModel.getWeather(it)
            }
        ).show(supportFragmentManager, null)
    }

    @SuppressLint("MissingPermission")
    private fun getLastLocation() {
        if (checkPermission()) {
            if (isLocationEnabled()) {
                fusedLocationProviderClient.lastLocation.addOnCompleteListener { task ->
                    val location: Location? = task.result
                    if (location == null) {
                        newLocationData()
                    } else {
                        Log.d(TAG, "Your Location:$location")
                    }
                }
            } else {
                Toast.makeText(this, "Please Turn on Your device Location", Toast.LENGTH_SHORT)
                    .show()
            }
        } else {
            requestPermission()
        }
    }

    @SuppressLint("MissingPermission")
    private fun newLocationData() {
        val locationRequest = LocationRequest()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = 0
        locationRequest.fastestInterval = 0
        locationRequest.numUpdates = 1
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        fusedLocationProviderClient.requestLocationUpdates(
            locationRequest, locationCallback, Looper.myLooper()
        )
    }

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            val lastLocation: Location = locationResult.lastLocation
            Log.d(TAG, "your last last location: $lastLocation")
        }
    }

    private fun checkPermission(): Boolean {
        if (
            ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED ||
            ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }
        return false
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                android.Manifest.permission.ACCESS_COARSE_LOCATION,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ),
            PERMISSION_ID
        )
    }

    private fun isLocationEnabled(): Boolean {
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_ID) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d(TAG, "You have the Permission")
            }
        }
    }

}





