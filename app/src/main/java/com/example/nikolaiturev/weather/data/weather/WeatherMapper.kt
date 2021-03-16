package com.example.nikolaiturev.weather.data.weather

import com.example.nikolaiturev.weather.data.api.response.BaseWeatherResponse
import com.example.nikolaiturev.weather.domain.entity.Weather
import com.example.nikolaiturev.weather.util.Mapper
import com.example.nikolaiturev.weather.util.Сonstants

class WeatherMapper : Mapper<BaseWeatherResponse, Weather> {
    override fun transform(value: BaseWeatherResponse): Weather {
        return Weather(
            name = value.name,
            icon = value.weather[0].icon,
            tempС = value.main.temp - Сonstants.BOLTZMANN_СONSTANT,
            description = value.weather[0].description,
            speed = value.wind.speed,
            pressure = value.main.pressure,
            humidity = value.main.humidity
        )
    }
}
