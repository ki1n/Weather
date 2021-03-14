package com.example.nikolaiturev.weather.data.city

import com.example.nikolaiturev.weather.domain.repository.CityRepository

class CityRepositoryImpl : CityRepository {
    override fun getCity(): List<String> =
            arrayListOf(
                "Омск",
                "Москва",
                "Петербург",
                "Самара",
                "Сочи",
                "Берлин",
                "Париж",
                "Лондон",
                "Новосибирск",
                "Ростов-на-Дону",
                "Екатеринбург",
            )
}
