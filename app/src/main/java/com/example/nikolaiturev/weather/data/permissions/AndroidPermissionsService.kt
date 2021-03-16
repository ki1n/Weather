package com.example.nikolaiturev.weather.data.permissions

import android.app.Activity
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.lang.ref.WeakReference

class AndroidPermissionsService {

    private lateinit var mActivityRef: WeakReference<Activity>

    private var mRequestCode: Int = 0
    private var mOnPermissionsResultsCallback: ((Int, Boolean) -> Unit)? = null
    private var mRequestedPermissions = mutableListOf<String>()

    fun requestPermissions(
        requestCode: Int,
        onPermissionsResultsCallback: (Int, Boolean) -> Unit,
        permissions: List<String>
    ) {
        if (isPermissionsGranted(*permissions.toTypedArray())) {
            onPermissionsResultsCallback.invoke(requestCode, true)

        } else {
            mRequestCode = requestCode

            mRequestedPermissions.clear()
            mRequestedPermissions.addAll(permissions)

            mOnPermissionsResultsCallback = onPermissionsResultsCallback

            mActivityRef.get()?.let { activity ->
                ActivityCompat.requestPermissions(activity, permissions.toTypedArray(), requestCode)
            }
        }
    }

    fun isPermissionsGranted(vararg permissions: String): Boolean {
        mActivityRef.get()?.let { activity ->
            for (permission in permissions) {
                if (ContextCompat.checkSelfPermission(
                        activity,
                        permission
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    return false
                }
            }

            return true
        } ?: return false
    }

    fun init(activity: AppCompatActivity) {
        mActivityRef = WeakReference(activity)
    }

    fun onPermissionsResult(requestCode: Int) {
        mOnPermissionsResultsCallback?.invoke(
            requestCode,
            isPermissionsGranted(*mRequestedPermissions.toTypedArray())
        )
    }

}
