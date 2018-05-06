package parkingowner.iuh.parkingreservation.com.parkingowner.ui.scanner

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector
import parkingowner.iuh.parkingreservation.com.parkingowner.R
import parkingowner.iuh.parkingreservation.com.parkingowner.base.BaseActivity
import parkingowner.iuh.parkingreservation.com.parkingowner.databinding.ActivityQrscannerBinding
import android.content.Intent
import android.util.Log


class QRScannerActivity : BaseActivity<QRScannerPresenter>(), QRScannerContract.View {


    lateinit var binding: ActivityQrscannerBinding

    @BindView(R.id.qr_view)
    lateinit var qrView: SurfaceView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_qrscanner)
        ButterKnife.bind(this)
        setUpQR()
    }

    private fun setUpQR() {
        qrView.setZOrderMediaOverlay(true)
        val barcode = BarcodeDetector.Builder(this)
                .setBarcodeFormats(Barcode.QR_CODE)
                .build()
        if(!barcode.isOperational){
            Toast.makeText(applicationContext, "Sorry, Couldn't setup the detector", Toast.LENGTH_LONG).show();
            this.finish()
        }

        val cameraSource = CameraSource.Builder(this, barcode)
                .setFacing(CameraSource.CAMERA_FACING_BACK)
                .setRequestedFps(24f)
                .setAutoFocusEnabled(true)
                .setRequestedPreviewSize(1366, 720)
                .build()
        qrView.holder.addCallback(object: SurfaceHolder.Callback {
            override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {}

            override fun surfaceDestroyed(holder: SurfaceHolder?) {}

            override fun surfaceCreated(holder: SurfaceHolder?) {
                if(ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
                    cameraSource.start(qrView.holder)
                }
            }
        })
        barcode.setProcessor(object: Detector.Processor<Barcode>{
            override fun release() { }

            override fun receiveDetections(detection: Detector.Detections<Barcode>?) {
                val barcodes = detection?.detectedItems
                if(barcodes != null) {
                    Log.i("asdasd", "barcords != null")
                    if (barcodes.size() > 0) {
                        Log.i("asdasd", "barcord size > 0")
                        val intent = Intent()
                        intent.putExtra("barcode", barcodes.valueAt(0))
                        setResult(Activity.RESULT_OK, intent)
                        finish()
                    }
                }

            }

        })
    }

    override fun onQRscanned() {

    }

    override fun getContexts(): Context {
        return this
    }

    override fun showError(string: String) {
        showStatus(string)
    }

    override fun showSuccess(string: String) {
        showStatus(string)
    }

    private fun showStatus(s: String) {
        Toast.makeText(getContexts(), s, Toast.LENGTH_LONG).show()
    }

    override fun showLoading() {
//        binding.progressVisibility = View.VISIBLE
    }

    override fun hideLoading() {
//        binding.progressVisibility = View.GONE
    }


    override fun instantiatePresenter(): QRScannerPresenter {
        return QRScannerPresenter(this)
    }

}
