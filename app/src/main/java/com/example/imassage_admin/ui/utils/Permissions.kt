package com.example.imassage_admin.ui.utils

import android.app.Activity
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

fun askForPermission(permission: String , requestCode: Int , activity: Activity){
    if (ContextCompat.checkSelfPermission(
            activity,
            permission
        ) !== PackageManager.PERMISSION_GRANTED
    ) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                activity,
                permission
            )
        ) {
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(permission),
                requestCode
            )
        } else {
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(permission),
                requestCode
            )
        }
    } else if (ContextCompat.checkSelfPermission(
            activity,
            permission
        ) === PackageManager.PERMISSION_DENIED
    ) {
        Toast.makeText( activity, "دسترسی  به حاظه داده نیشده است.", Toast.LENGTH_SHORT)
            .show()
    }
}