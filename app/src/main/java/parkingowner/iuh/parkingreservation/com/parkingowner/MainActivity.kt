package parkingowner.iuh.parkingreservation.com.parkingowner

import android.Manifest
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.databinding.adapters.TextViewBindingAdapter.setText
import com.google.android.gms.vision.barcode.Barcode
import android.content.Intent
import android.Manifest.permission
import android.app.Activity
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.google.firebase.iid.FirebaseInstanceId
import parkingowner.iuh.parkingreservation.com.parkingowner.ui.scanner.QRScannerActivity
import javax.sql.CommonDataSource


class MainActivity : AppCompatActivity() {

    lateinit var scanbtn: Button
    lateinit var result: TextView
    val REQUEST_CODE = 100
    val PERMISSION_REQUEST = 200

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        scanbtn = findViewById<Button>(R.id.scanbtn)
        result = findViewById<TextView>(R.id.result)
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf<String>(Manifest.permission.CAMERA), PERMISSION_REQUEST)
        }
        scanbtn.setOnClickListener {
            val intent = Intent(this@MainActivity, QRScannerActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE)
        }

        val token  = FirebaseInstanceId.getInstance().token
        Log.i("TAG", "token = $token")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                val barcode = data.getParcelableExtra<Barcode>("barcode")
                result.post { result.text = barcode.displayValue }
            }
        }
    }
}
