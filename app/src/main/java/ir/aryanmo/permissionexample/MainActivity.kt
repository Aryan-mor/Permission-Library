package ir.aryanmo.permissionexample

import android.Manifest
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import ir.aryanmo.permissionlibrary.PermissionAskListener
import ir.aryanmo.permissionlibrary.checkPermission
import ir.aryanmo.permissionlibrary.requestPermission
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv.setOnClickListener {
            checkPermission(this, Manifest.permission.READ_CONTACTS){
                    Log.e("Ari", "OKKKKKK")
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

    }
}
