package parkingowner.iuh.parkingreservation.com.parkingowner

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService: FirebaseMessagingService() {
    override fun onMessageReceived(p0: RemoteMessage?) {

        Log.d("TAG", "From + ${p0?.from}")
        Log.d("TAG", "Body + ${p0?.notification?.body}")
    }
}