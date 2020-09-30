package com.kp.notificationreaderapp.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.kp.notificationreaderapp.R

class NotificationActivity2 : AppCompatActivity() {

    lateinit var btnBundleNotification: Button
    lateinit var btnSingleNotification: Button
    lateinit var notificationManager: NotificationManager

    var bundleNotificationId = 100
    var singleNotificationId = 100

    lateinit var summaryNotificationBuilder: NotificationCompat.Builder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification2)

        var bundle_notification_id:String
        var resultIntent:Intent
        var resultPendingIntent:PendingIntent

        notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        btnBundleNotification = findViewById(R.id.btnBundleNotification)
        btnSingleNotification = findViewById(R.id.btnSingleNotification)

        btnBundleNotification.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val groupChannel = NotificationChannel("bundle_channel_id",
                        "bundle_channel_name", NotificationManager.IMPORTANCE_LOW)
                notificationManager.createNotificationChannel(groupChannel)
            }

            bundleNotificationId += 100
            singleNotificationId = bundleNotificationId
            bundle_notification_id = "bundle_notification_$bundleNotificationId"
            resultIntent = Intent(this, MainActivity::class.java)
            resultIntent.putExtra("notification", "Summary Notification Clicked")
            resultIntent.putExtra("notification_id", bundleNotificationId)
            resultIntent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP
            resultPendingIntent = PendingIntent.getActivity(this, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT)
            summaryNotificationBuilder = NotificationCompat.Builder(this, "bundle_channel_id")
                    .setGroup(bundle_notification_id)
                    .setGroupSummary(true)
                    .setContentTitle("Bundled Notification. $bundleNotificationId")
                    .setContentText("Content Text for bundle notification")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentIntent(resultPendingIntent)
            notificationManager.notify(bundleNotificationId, summaryNotificationBuilder.build())
        }

        btnSingleNotification.setOnClickListener {
            bundle_notification_id = "bundle_notification_$bundleNotificationId"
            resultIntent = Intent(this, MainActivity::class.java)
            resultIntent.putExtra("notification", "Summary Notification Clicked")
            resultIntent.putExtra("notification_id", bundleNotificationId)
            resultIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP)
            resultPendingIntent = PendingIntent.getActivity(this, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT)

            //We need to update the bundle notification every time a new notification comes up.
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                if (notificationManager.notificationChannels.size < 2) {
                    val groupChannel = NotificationChannel("bundle_channel_id", "bundle_channel_name", NotificationManager.IMPORTANCE_LOW)
                    notificationManager.createNotificationChannel(groupChannel)
                    val channel = NotificationChannel("channel_id", "channel_name", NotificationManager.IMPORTANCE_DEFAULT)
                    notificationManager.createNotificationChannel(channel)
                }
            }

            summaryNotificationBuilder = NotificationCompat.Builder(this, "bundle_channel_id")
                    .setGroup(bundle_notification_id)
                    .setGroupSummary(true)
                    .setContentTitle("Bundled Notification $bundleNotificationId")
                    .setContentText("Content Text for group summary")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentIntent(resultPendingIntent)

            if (singleNotificationId == bundleNotificationId) singleNotificationId = bundleNotificationId + 1
            else singleNotificationId++

            resultIntent = Intent(this, MainActivity::class.java)
            resultIntent.putExtra("notification", "Single notification clicked")
            resultIntent.putExtra("notification_id", singleNotificationId)
            resultIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP)
            resultPendingIntent = PendingIntent.getActivity(this, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT)
            val notification = NotificationCompat.Builder(this, "channel_id")
                    .setGroup(bundle_notification_id)
                    .setContentTitle("New Notification $singleNotificationId")
                    .setContentText("Content for the notification")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setGroupSummary(false)
                    .setContentIntent(resultPendingIntent)
            notificationManager.notify(singleNotificationId, notification.build())
            notificationManager.notify(bundleNotificationId, summaryNotificationBuilder.build())
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        val extras = intent.extras
        if (extras != null) {
            val notification_id = extras.getInt("notification_id")
            Toast.makeText(applicationContext, "Notification with ID $notification_id is cancelled", Toast.LENGTH_LONG).show()
            notificationManager.cancel(notification_id)
        }
    }

}