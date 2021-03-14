package com.example.nikolaiturev.weather.data.api

import com.example.nikolaiturev.weather.BuildConfig
import com.example.nikolaiturev.weather.data.api.response.BaseWeatherResponse
import com.example.nikolaiturev.weather.util.Const
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("/data/2.5/weather")
    fun getWeatherDataByCity(
        @Query("q") city: String = "",
        @Query("appid") appId: String = BuildConfig.API_KEY,
        @Query("lang") lang: String = Const.LANG
    ): Single<BaseWeatherResponse>

    @GET("/data/2.5/weather")
    fun getWeatherDataByGeolocation(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") appId: String = BuildConfig.API_KEY,
        @Query("lang") lang: String = Const.LANG
    ): Single<BaseWeatherResponse>
}
