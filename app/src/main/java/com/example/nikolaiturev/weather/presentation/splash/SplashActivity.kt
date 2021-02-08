package com.example.nikolaiturev.weather.presentation.splash

import android.content.Intent
import android.os.Handler
import com.example.nikolaiturev.weather.R
import com.example.nikolaiturev.weather.presentation.base.BaseActivity
import com.example.nikolaiturev.weather.presentation.weather.WeatherActivity

@Suppress("DEPRECATION")
class SplashActivity : BaseActivity() {

    override var layoutId: Int = R.layout.splash_acvivity

    override fun iniView() {
        Handler().postDelayed(
            {
                val intent = Intent(this, WeatherActivity::class.java)
                startActivity(intent)
            }, 3000
        )
    }
}
