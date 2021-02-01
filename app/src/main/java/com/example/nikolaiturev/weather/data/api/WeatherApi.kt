package com.example.nikolaiturev.weather.data.api

import com.example.nikolaiturev.weather.BuildConfig
import com.example.nikolaiturev.weather.data.api.response.BaseWeatherResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    // curl -s "${api_prefix}weather?${appid}${id}${units}&lang=ru" -o "$weather"

    @GET("/data/2.5/weather")
    fun getWeatherDataByCity(
        @Query("q") city: String = "",
        @Query("appid") appId: String = BuildConfig.API_KEY,
        @Query("lang") lang: String = "ru"
    ): Single<BaseWeatherResponse>
}
