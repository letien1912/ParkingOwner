package parkingowner.iuh.parkingreservation.com.parkingowner.base

import parkingowner.iuh.parkingreservation.com.parkingowner.injection.component.DaggerPresenterInjector
import parkingowner.iuh.parkingreservation.com.parkingowner.injection.component.PresenterInjector
import parkingowner.iuh.parkingreservation.com.parkingowner.injection.module.ContextModule
import parkingowner.iuh.parkingreservation.com.parkingowner.injection.module.NetworkModule
import parkingowner.iuh.parkingreservation.com.parkingowner.ui.scanner.QRScannerPresenter

abstract class BasePresenter<out V: BaseView>(protected val view: V) {

    private val injector: PresenterInjector = DaggerPresenterInjector
            .builder()
            .baseView(view)
            .contextModule(ContextModule)
            .networkModule(NetworkModule)
            .build()
    init {
        inject()
    }

    /**
     *  THis method may be called when the presenter is created
     */
    open fun onViewCreated(){}

    /**
     * This method may be called when the presenter view is destroyed
     */
    open fun onViewDestroyed(){}

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is QRScannerPresenter -> injector.inject(this)
        }
    }
}