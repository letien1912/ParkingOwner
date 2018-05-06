package parkingowner.iuh.parkingreservation.com.parkingowner.injection.module

import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Module which provides all required dependencies about network
 */
@Module
@Suppress("unused")
object NetworkModule {

    private const val BASE_URL = "http://45.119.81.16:8080/parking_reservation_1.0-1.0.0/"

//    /**
//     * Using for log-in, log-out user
//     * Provides the Login service implementation.
//     * @param retrofit the Retrofit object used to instantiate the service
//     * @return the Login service implementation.
//     */
//    @Provides
//    @Reusable
//    @JvmStatic
//    internal fun provideLoginService(retrofit: Retrofit): LoginService {
//        return retrofit.create(LoginService::class.java)
//    }


    /**
     * Provides the Retrofit object.
     * @return the Retrofit object
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofitInterface(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build()
    }
}