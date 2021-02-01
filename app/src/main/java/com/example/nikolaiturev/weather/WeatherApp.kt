package com.example.nikolaiturev.weather

import android.app.Application
import com.example.nikolaiturev.weather.di.*
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
                    mapperModule,
                    adapterModule
                )
            )
            this.androidContext(this@WeatherApp)
        }
    }
}