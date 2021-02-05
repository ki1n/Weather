package com.example.nikolaiturev.weather.data.weather

import com.example.nikolaiturev.weather.data.api.response.BaseWeatherResponse
import com.example.nikolaiturev.weather.domain.entity.WeatherGeo
import com.example.nikolaiturev.weather.util.Mapper

class WeatherGeoMapper : Mapper<BaseWeatherResponse, WeatherGeo> {
    override fun transform(value: BaseWeatherResponse): WeatherGeo {
        return WeatherGeo(
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