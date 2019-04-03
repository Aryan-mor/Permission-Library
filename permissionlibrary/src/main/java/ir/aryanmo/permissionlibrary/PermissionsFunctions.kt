package ir.aryanmo.permissionlibrary

import android.Manifest
import android.app.Activity
import android.content.Context

const val REQUEST_INTERNET_CODE = 11
const val REQUEST_READ_EXTERNAL_STORAGE_CODE = 22
const val REQUEST_WRITE_EXTERNAL_STORAGE_CODE = 33
const val REQUEST_CAMERA_CODE = 44
const val REQUEST_LOCATION_CODE = 55
const val REQUEST_VIBRATION_CODE = 66
const val REQUEST_RECORD_AUDIO_CODE = 77

fun checkInternetPermission(activity: Activity, listener: PermissionAskListener) {
    checkPermission(activity, Manifest.permission.INTERNET, listener)
}

fun requestInternetPermission(activity: Activity) {
    requestPermission(activity, Manifest.permission.INTERNET, REQUEST_INTERNET_CODE)
}

fun hasInternetPermission(context: Context): Boolean {
    return hasPermission(context, Manifest.permission.INTERNET)
}

fun checkReadExternalStoragePermission(activity: Activity, listener: PermissionAskListener) {
    checkPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE, listener)
}

fun requestReadExternalStoragePermission(activity: Activity) {
    requestPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE, REQUEST_READ_EXTERNAL_STORAGE_CODE)
}

fun hasReadExternalStoragePermission(context: Context): Boolean {
    return hasPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE)
}

fun checkWriteExternalStoragePermission(activity: Activity, listener: PermissionAskListener) {
    checkPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE, listener)
}

fun requestWriteExternalStoragePermission(activity: Activity) {
    requestPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE, REQUEST_WRITE_EXTERNAL_STORAGE_CODE)
}

fun hasWriteExternalStoragePermission(context: Context): Boolean {
    return hasPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)
}

fun checkCameraPermission(activity: Activity, listener: PermissionAskListener) {
    checkPermission(activity, Manifest.permission.CAMERA, listener)
}

fun requestCameraPermission(activity: Activity) {
    requestPermission(activity, Manifest.permission.CAMERA, REQUEST_CAMERA_CODE)
}

fun hasCameraPermission(context: Context): Boolean {
    return hasPermission(context, Manifest.permission.CAMERA)
}

fun checkLocationPermission(activity: Activity, listener: PermissionAskListener) {
    checkPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION, listener)
}

fun requestLocationPermission(activity: Activity) {
    requestPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION, REQUEST_LOCATION_CODE)
}

fun hasLocationPermission(context: Context): Boolean {
    return hasPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
}

fun checkVibrationPermission(activity: Activity, listener: PermissionAskListener) {
    checkPermission(activity, Manifest.permission.VIBRATE, listener)
}

fun requestVibrationPermission(activity: Activity) {
    requestPermission(activity, Manifest.permission.VIBRATE, REQUEST_VIBRATION_CODE)
}

fun hasVibrationPermission(context: Context): Boolean {
    return hasPermission(context, Manifest.permission.VIBRATE)
}

fun checkRecordAudioPermission(activity: Activity, listener: PermissionAskListener) {
    checkPermission(activity, Manifest.permission.RECORD_AUDIO, listener)
}

fun requestRecordAudioPermission(activity: Activity) {
    requestPermission(activity, Manifest.permission.RECORD_AUDIO, REQUEST_CAMERA_CODE)
}

fun hasRecordAudioPermission(context: Context): Boolean {
    return hasPermission(context, Manifest.permission.RECORD_AUDIO)
}