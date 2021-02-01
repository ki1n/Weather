package com.example.nikolaiturev.weather.data.weather

import com.example.nikolaiturev.weather.data.api.response.BaseWeatherResponse
import com.example.nikolaiturev.weather.domain.entity.Weather
import com.example.nikolaiturev.weather.util.Mapper

class WeatherMapper : Mapper<BaseWeatherResponse, Weather> {
    override fun transform(value: BaseWeatherResponse): Weather {
        return Weather(
            name = value.name,
            icon = value.weather[0].icon,
            tempС = value.main.temp - 273,
            description = value.weather[0].description,
            speed = value.wind.speed,
            pressure = value.main.pressure,
            humidity = value.main.humidity
        )
    }
}
