package ir.aryanmo.permissionlibrary

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat


private const val PREFS_FILE_NAME = "PermissionLibrarySharedPreference"
const val DEFAULT_REQUEST_CODE = 1856423

fun shouldAskPermission(): Boolean {
    return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
}

fun shouldAskPermission(context: Context, permission: String): Boolean {
    if (shouldAskPermission()) {
        val permissionResult = ActivityCompat.checkSelfPermission(context, permission)
        if (permissionResult != PackageManager.PERMISSION_GRANTED) {
            return true
        }
    }
    return false
}

fun hasPermission(context: Context, manifestPermission: String): Boolean {
    return if (!shouldAskPermission()) true else ContextCompat.checkSelfPermission(
        context,
        manifestPermission
    ) == PackageManager.PERMISSION_GRANTED
}

@SuppressLint("NewApi")
fun checkPermission(activity: Activity, permission: String, listener: PermissionAskListener) {
    /*
     * If permission is not granted
     * */
    if (shouldAskPermission(activity, permission)) {
        /*
         * If permission denied previously
         * */
        if (activity.shouldShowRequestPermissionRationale(permission)) {
            listener.onNeedPermission()
        } else {
            /*
             * Permission denied or first time requested
             * */
            if (isFirstTimeAskingPermission(activity, permission)) {
                firstTimeAskingPermission(activity, permission, false)
                listener.onNeedPermission()
            } else {
                /*
                 * Handle the feature without permission or ask user to manually allow permission
                 * */
                listener.onPermissionDisabled()
            }
        }
    } else {
        listener.onPermissionGranted()
    }
}

@SuppressLint("NewApi")
fun checkPermission(activity: Activity, permission: String, permissionGranted: () -> Unit) {
    checkPermission(activity, permission, object : PermissionAskListener {
        override fun onNeedPermission() {
            requestPermission(activity,permission, DEFAULT_REQUEST_CODE)
        }

        override fun onPermissionDisabled() {
            getPermissionDisableDialog(activity).show()
        }

        override fun onPermissionGranted() {
            permissionGranted()
        }
    })
}

private fun firstTimeAskingPermission(context: Context, permission: String, isFirstTime: Boolean) {
    val sharedPreference = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE)
    sharedPreference.edit().putBoolean(permission, isFirstTime).apply()
}

private fun isFirstTimeAskingPermission(context: Context, permission: String): Boolean {
    return context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE).getBoolean(permission, true)
}

interface PermissionAskListener {
    /*
     * Callback to ask permission
     * */
    fun onNeedPermission()

    /*
     * Callback on permission "Never show again" checked and denied
     * */
    fun onPermissionDisabled()

    /*
     * Callback on permission granted
     * */
    fun onPermissionGranted()
}

