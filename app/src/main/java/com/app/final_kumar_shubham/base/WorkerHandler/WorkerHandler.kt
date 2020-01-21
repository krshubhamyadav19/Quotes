package com.app.final_kumar_shubham.base.WorkerHandler

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.app.final_kumar_shubham.R
import com.google.firebase.auth.FirebaseAuth

/**
 * @author Kumar Shubham
 * 10/1/20
 */
class WorkerHandler(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {



    override fun doWork(): Result {
        sendNotification()

        return Result.success()
    }

    fun sendNotification() {
        val notificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelId = "WorkManager_00"

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, "WorkManager", NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(channel)
        }
        var user=FirebaseAuth.getInstance().currentUser!!.email
        val notification = NotificationCompat.Builder(applicationContext, channelId)
            .setContentTitle("Quotes added succesfully")

            .setContentText("By-$user")
            .setSmallIcon(R.drawable.ic_event_black_24dp)

        notificationManager.notify(1, notification.build())
    }

}