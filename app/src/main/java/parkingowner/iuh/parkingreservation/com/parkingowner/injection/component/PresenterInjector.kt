package parkingowner.iuh.parkingreservation.com.parkingowner.injection.component

import android.content.SharedPreferences
import dagger.BindsInstance
import dagger.Component
import parkingowner.iuh.parkingreservation.com.parkingowner.base.BaseView
import parkingowner.iuh.parkingreservation.com.parkingowner.injection.module.ContextModule
import parkingowner.iuh.parkingreservation.com.parkingowner.injection.module.NetworkModule
import parkingowner.iuh.parkingreservation.com.parkingowner.ui.scanner.QRScannerPresenter
import javax.inject.Singleton

/**
* Created by Kushina on 25/03/2018.
*/

@Singleton
@Component(modules = [(ContextModule::class), (NetworkModule::class)])
interface PresenterInjector {

    fun inject(presenter: QRScannerPresenter)
    /**
     * using for get saved data from client
     */
    fun getSharedPreferences(): SharedPreferences

    @Component.Builder
    interface Builder {
        fun build(): PresenterInjector

        fun contextModule(contextModule: ContextModule): Builder
        fun networkModule(networkModule: NetworkModule): Builder

        @BindsInstance
        fun baseView(baseView: BaseView): Builder
    }

}
