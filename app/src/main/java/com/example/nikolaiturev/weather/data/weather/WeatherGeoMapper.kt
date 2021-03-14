package com.example.nikolaiturev.weather.data.weather

import com.example.nikolaiturev.weather.data.api.response.BaseWeatherResponse
import com.example.nikolaiturev.weather.domain.entity.WeatherGeolocation
import com.example.nikolaiturev.weather.util.Mapper

class WeatherGeoMapper : Mapper<BaseWeatherResponse, WeatherGeolocation> {
    // 273 - константа
    override fun transform(value: BaseWeatherResponse): WeatherGeolocation {
        return WeatherGeolocation(
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
