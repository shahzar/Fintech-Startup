package com.shzlabs.mamopay.util.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.shzlabs.mamopay.MainActivity
import com.shzlabs.mamopay.R

object NotificationHelper {

    fun createChannel(context: Context, channelId: String, channelName: String) {

        val notificationMgr = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O &&
            notificationMgr.getNotificationChannel(channelId) == null) {

            val mChannel = NotificationChannel(
                channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationMgr.createNotificationChannel(mChannel)
        }
    }

    fun showNotification(context: Context, channelId: String, title: String, description: String) {

        val notificationMgr = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val intent = Intent(context, MainActivity::class.java)
        val contentIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        val builder = NotificationCompat.Builder(context, channelId)
        builder.apply {
            setSmallIcon(R.mipmap.ic_launcher)
            setContentTitle(title)
            setAutoCancel(true)
            setContentIntent(contentIntent)
            setContentText(description)
        }

        notificationMgr.notify(1, builder.build())
    }

}