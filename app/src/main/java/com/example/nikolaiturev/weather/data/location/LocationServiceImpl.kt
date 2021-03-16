package com.example.nikolaiturev.weather.data.location

import android.annotation.SuppressLint
import android.content.Context
import com.example.nikolaiturev.weather.domain.entity.Location
import com.example.nikolaiturev.weather.domain.service.LocationService
import com.google.android.gms.location.LocationServices
import io.reactivex.Single

class LocationServiceImpl(
    private val context: Context
) : LocationService {

    @SuppressLint("MissingPermission")
    override fun getLastKnownLocation(): Single<Location> =
        Single.create { emitter ->
            LocationServices.getFusedLocationProviderClient(context)
                .lastLocation
                .addOnSuccessListener { location ->
                    if (location != null) {
                        emitter.onSuccess(
                            Location(
                                latitude = location.latitude,
                                longitude = location.longitude
                            )
                        )
                    } else {
                        emitter.onError(Throwable("Location is null"))
                    }
                }
                .addOnFailureListener { emitter.onError(it) }
        }
}

