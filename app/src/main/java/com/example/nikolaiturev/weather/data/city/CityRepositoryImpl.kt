package com.example.nikolaiturev.weather.data.city

import com.example.nikolaiturev.weather.domain.entity.City
import com.example.nikolaiturev.weather.domain.repository.CityRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CityRepositoryImpl : CityRepository {
    override fun getCity(): Single<List<City>> =
        Single.just(
            listOf(
                City("Омск"),
                City("Москва"),
                City("Петербург"),
                City("Самара"),
                City("Сочи"),
                City("Берлин"),
                City("Париж"),
                City("Лондон"),
                City("Новосибирск"),
                City("Ростов-на-Дону"),
                City("Екатеринбург"),
                City("Минск"),
                City("Бразилиа"),
                City("Гватемала"),
                City("Иерусалим"),
                City("Мадрид"),
                City("Пекин"),
                City("Рига"),
                City("Скопье"),
                City("Токио"),
                City("Вашингтон"),
                City("Белград"),
                City("Киев"),
                City("Оттава")
            )
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}
