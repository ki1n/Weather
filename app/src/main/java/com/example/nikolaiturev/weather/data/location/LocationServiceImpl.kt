package com.example.nikolaiturev.weather.data.location

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.example.nikolaiturev.weather.domain.entity.Location
import com.example.nikolaiturev.weather.domain.service.LocationService
import com.google.android.gms.location.LocationServices
import io.reactivex.Single

class LocationServiceImpl(
    private val context: Context
) : LocationService {

    override fun getLastKnownLocation(): Single<Location> =
        Single.create { emitter ->
            if (!checkPermission()) {
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


//    if (!checkPermission()) {
//        LocationServices.getFusedLocationProviderClient(context)
//            .lastLocation
//            .addOnSuccessListener { location ->
//                if (location != null) {
//                    emitter.onSuccess(
//                        Location(
//                            latitude = location.latitude,
//                            longitude = location.longitude
//                        )
//                    )
//                } else {
//                    emitter.onError(Throwable("Location is null"))
//                }
//            }
//            .addOnFailureListener { emitter.onError(it) }
//    }


    private fun checkPermission(): Boolean {
        if (
            ActivityCompat.checkSelfPermission(
                context,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED ||
            ActivityCompat.checkSelfPermission(
                context,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }
        return false
    }
}

