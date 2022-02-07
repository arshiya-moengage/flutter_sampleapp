package com.example.flutter_sampleapp

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.moengage.firebase.MoEFireBaseHelper
import com.moengage.pushbase.MoEPushHelper

/**
 * @author Arshiya Khanum
 */
class CustomFirebaseReceiver: FirebaseMessagingService() {
    private val tag = "SampleApplication"

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        Log.d(tag, " onMessageReceived(): $message")
        if (MoEPushHelper.getInstance().isFromMoEngagePlatform(message.data)) {
            MoEFireBaseHelper.getInstance().passPushPayload(applicationContext, message.data)
        }
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d(tag, " onNewToken(): $token")
        MoEFireBaseHelper.getInstance().passPushToken(applicationContext, token)
    }
}