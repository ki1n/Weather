package com.example.nikolaiturev.weather.data.api

import com.example.nikolaiturev.weather.data.api.response.BaseWeatherResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("/data/2.5/weather")
    fun getWeatherDataByCity(
        @Query("q") city: String = "London",
        @Query("appid") appId: String = "dde75f86d86c159c3e016b9253b0eb03"
    ): Single<BaseWeatherResponse>

}