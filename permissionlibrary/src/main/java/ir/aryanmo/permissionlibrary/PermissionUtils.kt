package ir.aryanmo.permissionlibrary

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.util.Log


private const val PREFS_FILE_NAME = "PermissionLibrarySharedPreference"
const val PERMISSION_DEFAULT_REQUEST_CODE = 8075

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
fun checkPermission(activity: Activity, permissions: List<String>, listener: PermissionAskListener) {

    if (permissions.isEmpty()) {
        return
    }
    val permission = permissions[0]
    val perList = permissions as ArrayList<String>
    perList.removeAt(0)
    /*
     * If permission is not granted
     * */
    if (shouldAskPermission(activity, permission)) {
        /*
         * If permission denied previously
         * */
        if (activity.shouldShowRequestPermissionRationale(permission)) {
            listener.onNeedPermission(permission)
        } else {
            /*
             * Permission denied or first time requested
             * */
            if (isFirstTimeAskingPermission(activity, permission)) {
                firstTimeAskingPermission(activity, permission, false)
                listener.onNeedPermission(permission)
            } else {
                /*
                 * Handle the feature without permission or ask user to manually allow permission
                 * */
                listener.onPermissionDisabled(permission)
            }
        }
    } else {
        listener.onPermissionGranted(permission)
        checkPermission(activity, perList, listener)
    }
}

@SuppressLint("NewApi")
fun checkPermission(activity: Activity, permission: String, listener: PermissionAskListener) {
    val permissions = ArrayList<String>()
    permissions.add(permission)
    checkPermission(activity, permissions, listener)
}

@SuppressLint("NewApi")
fun checkPermission(activity: Activity, permissions: List<String>, permissionGranted: (permission: String) -> Unit) {
    checkPermission(activity, permissions, object : PermissionAskListener {
        override fun onNeedPermission(permission: String) {
            requestPermission(activity, permission, PERMISSION_DEFAULT_REQUEST_CODE)
        }

        override fun onPermissionDisabled(permission: String) {
            getPermissionDisableDialog(activity).show()
        }

        override fun onPermissionGranted(permission: String) {
            permissionGranted(permission)
        }
    })
}

@SuppressLint("NewApi")
fun checkPermission(activity: Activity, permission: String, permissionGranted: (permission: String) -> Unit) {
    val permissions = ArrayList<String>()
    permissions.add(permission)
    checkPermission(activity, permissions, permissionGranted)
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
    fun onNeedPermission(permission: String)

    /*
     * Callback on permission "Never show again" checked and denied
     * */
    fun onPermissionDisabled(permission: String)

    /*
     * Callback on permission granted
     * */
    fun onPermissionGranted(permission: String)
}

