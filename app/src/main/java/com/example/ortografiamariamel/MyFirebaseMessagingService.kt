package com.example.ortografiamariamel

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.RemoteMessage
import com.google.firebase.messaging.FirebaseMessagingService



class MyFirebaseMessagingService: FirebaseMessagingService() {
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        val notification = remoteMessage.notification
        notification?.let {
            Log.d("FCM", "Mensaje: ${it.title} - ${it.body}")
            // Aquí puedes mostrar una notificación local si quieres
        }

        val data = remoteMessage.data
        val title = data["title"]
        val body = data["body"]
        data.let {
            Log.d("FCM", "Data: $title - $body ")
        }

        showNotification(title?:"Sin titulo", body?:"Sin contenido")

    }

    private fun showNotification(title: String, body:String){
        val channelId= "default"
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.O){
            val channel = NotificationChannel(channelId, "Notificaciones", NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(channel)
        }

        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.carta1) // cambiar el icono please
            .setContentTitle(title).setContentText(body)
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        notificationManager.notify(0, notificationBuilder.build())
    }
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("FCM", "Nuevo token: $token")
        // Envía este token a tu servidor Laravel
    }
}