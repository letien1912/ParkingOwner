package parkingowner.iuh.parkingreservation.com.parkingowner.ui.scanner

import parkingowner.iuh.parkingreservation.com.parkingowner.base.BaseView

class QRScannerContract {
    interface  Presenter {
        fun scanQR()
    }

    interface View: BaseView {
        fun onQRscanned()
    }
}