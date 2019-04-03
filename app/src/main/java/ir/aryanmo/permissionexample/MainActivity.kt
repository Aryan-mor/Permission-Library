package ir.aryanmo.permissionexample

import android.Manifest
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import ir.aryanmo.permissionlibrary.PERMISSION_DEFAULT_REQUEST_CODE
import ir.aryanmo.permissionlibrary.checkPermission
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv.setOnClickListener {
            checkPermission()
        }
    }

    fun checkPermission() {
        val a = ArrayList<String>()
        a.add(Manifest.permission.READ_CONTACTS)
        a.add(Manifest.permission.CAMERA)
        a.add(Manifest.permission.READ_EXTERNAL_STORAGE)
        a.add(Manifest.permission.VIBRATE)
        a.add(Manifest.permission.RECORD_AUDIO)
        checkPermission(this, a) {

        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_DEFAULT_REQUEST_CODE) {
            checkPermission()
        }
    }
}
