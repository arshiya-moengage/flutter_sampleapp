package com.example.flutter_sampleapp

import android.app.Application
import android.util.Log
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.FirebaseApp
import com.google.firebase.messaging.FirebaseMessaging
import com.moengage.core.LogLevel
import com.moengage.core.MoEngage
import com.moengage.core.config.FcmConfig
import com.moengage.core.config.LogConfig
import com.moengage.core.config.NotificationConfig
import com.moengage.firebase.MoEFireBaseHelper
import com.moengage.flutter.MoEInitializer

/**
 * @author Arshiya Khanum
 */
class SampleApplication: Application() {

    private val tag = "SampleApplication"

    override fun onCreate() {
        super.onCreate()
        val moEngage: MoEngage.Builder = MoEngage.Builder(this, "YOUR_APP_ID")
            .configureNotificationMetaData(
                NotificationConfig(
                    R.drawable.icon,
                    R.drawable.ic_launcher,
                    -1,
                    null,
                    isMultipleNotificationInDrawerEnabled = true,
                    isBuildingBackStackEnabled = false,
                    isLargeIconDisplayEnabled = true
                )
            )
            .configureLogs(LogConfig(LogLevel.VERBOSE, true))
            .configureFcm(FcmConfig(false))
        MoEInitializer.initialize(applicationContext, moEngage)

        fetchAndSendFCMToken()
    }

    private fun fetchAndSendFCMToken() {
        FirebaseApp.initializeApp(this)
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w(tag, "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token: String? = task.result
            Log.d(tag, "Fetching FCM registration token success. Token: $token")
            if (token != null) {
                MoEFireBaseHelper.getInstance().passPushToken(this@SampleApplication, token)
            }
        })
    }
}