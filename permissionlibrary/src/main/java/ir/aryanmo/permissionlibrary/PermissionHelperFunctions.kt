package ir.aryanmo.permissionlibrary

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.support.v4.app.ActivityCompat
import android.util.Log


fun permissionGoToSettingIntent(packageName: String): Intent {
    val intent = Intent()
    intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
    val uri = Uri.fromParts("package", packageName, null)
    intent.data = uri
    return intent
}

fun requestPermission(activity: Activity, manifestPermission: String, requestCode: Int) {
    if (!shouldAskPermission())
        return

    ActivityCompat.requestPermissions(
        activity,
        arrayOf(manifestPermission),
        requestCode
    )
}

fun getPermissionDisableDialog(activity: Activity, manifestPermission: String): AlertDialog.Builder {
    return getPermissionDisableDialog(activity)
}

fun getPermissionDisableDialog(
    activity: Activity,
    title: String? = "Permission Denied",
    message: String? = "Do you want to provide the permission access?",
    positiveButtonText: String? = "Go To Setting",
    negativeButtonText: String? = "Cancel"
): AlertDialog.Builder {
    val dialog = AlertDialog.Builder(activity)
    dialog.setTitle(title)
    dialog.setMessage(message)
    dialog.setPositiveButton(positiveButtonText){ _: DialogInterface, _: Int ->
        activity.startActivity(permissionGoToSettingIntent(activity.packageName))
    }
    dialog.setNegativeButton(negativeButtonText) { _: DialogInterface, _: Int -> }
    dialog.setCancelable(true)
    return dialog
}

