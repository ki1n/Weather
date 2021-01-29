package com.example.nikolaiturev.weather.util

interface Mapper<FROM, TO> {
    fun transform(value: FROM): TO
}