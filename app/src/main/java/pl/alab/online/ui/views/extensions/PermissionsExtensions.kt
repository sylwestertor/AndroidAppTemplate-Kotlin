package pl.alab.online.ui.views.extensions

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import org.jetbrains.anko.alert
import pl.alab.online.R
import android.content.Intent
import android.net.Uri
import android.provider.Settings

val REQUEST_CAMERA = 0
val REQUEST_APP_SETTINGS = 1

fun Activity.isPermissionGranted(permission: String): Boolean {
    return ActivityCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
}

fun Activity.isCameraPermissionGranted(): Boolean {
    return isPermissionGranted(Manifest.permission.CAMERA)
}

fun Activity.requestCameraPermission() {
    ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), REQUEST_CAMERA)
}

fun Activity.onRequestCameraPermissionsResult(requestCode: Int, function: () -> Unit) {
    if (requestCode == REQUEST_CAMERA) {
        if (isCameraPermissionGranted()) {
            function.invoke()
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                alert("We need camera for this feature to work. Grant access?") {
                    positiveButton("Ok") { requestCameraPermission() }
                    negativeButton("Cancel") { }
                }.show()
            } else {
                alert("Wen asked for permission you chose \"Never ask again\". If you want to grant permission, you have to go to device settings and do it manualy.") {
                    positiveButton("Settings") { goToSettings() }
                    negativeButton("Cancel") { }
                }.show()
            }
        }
    }
}

internal fun Activity.goToSettings() {
    val myAppSettings = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:" + this.getPackageName()))
    myAppSettings.addCategory(Intent.CATEGORY_DEFAULT)
    myAppSettings.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    startActivityForResult(myAppSettings, REQUEST_APP_SETTINGS)
}

