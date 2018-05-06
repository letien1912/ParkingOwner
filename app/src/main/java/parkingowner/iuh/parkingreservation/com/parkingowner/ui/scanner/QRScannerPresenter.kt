package parkingowner.iuh.parkingreservation.com.parkingowner.ui.scanner

import io.reactivex.disposables.Disposable
import parkingowner.iuh.parkingreservation.com.parkingowner.base.BasePresenter

class QRScannerPresenter(view: QRScannerContract.View): BasePresenter<QRScannerContract.View>(view), QRScannerContract.Presenter {

    private val subscription: Disposable? = null

    override fun onViewCreated() {
        subscription?.dispose()
    }

    override fun onViewDestroyed() {
        super.onViewDestroyed()
    }

    override fun scanQR() {

    }
}