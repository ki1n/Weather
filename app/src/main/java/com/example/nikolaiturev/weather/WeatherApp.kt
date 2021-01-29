package com.example.nikolaiturev.weather

import android.app.Application
import com.example.nikolaiturev.weather.di.dataModule
import com.example.nikolaiturev.weather.di.mapperModule
import com.example.nikolaiturev.weather.di.repositoryModule
import com.example.nikolaiturev.weather.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WeatherApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            this.modules(
                listOf(
                    dataModule,
                    repositoryModule,
                    viewModelModule,
                    mapperModule
                )
            )
            this.androidContext(this@WeatherApp)
        }
    }
}