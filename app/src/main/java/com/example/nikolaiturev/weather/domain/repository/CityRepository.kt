package com.example.nikolaiturev.weather.domain.repository

import com.example.nikolaiturev.weather.domain.entity.City
import io.reactivex.Single

interface CityRepository {

    fun getCity(): Single<List<City>>

}
