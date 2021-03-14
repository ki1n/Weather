package com.example.nikolaiturev.weather.domain.service

import com.example.nikolaiturev.weather.domain.entity.Location
import io.reactivex.Single

interface LocationService {

    /**
     * Получение последнего известного местоположения устройства
     */

    fun getLastKnownLocation(): Single<Location>

}
