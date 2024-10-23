package com.abachta.jetpacktutorial.courses.advanced

import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.NotificationCompat
import androidx.core.content.getSystemService
import com.abachta.jetpacktutorial.MainActivity
import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.LessonPage

private fun sendNotification(context: Context) {
    val activityIntent = Intent(context, MainActivity::class.java)
    val pendingIntent = TaskStackBuilder.create(context).run {
        addNextIntentWithParentStack(activityIntent)
        getPendingIntent(0, PendingIntent.FLAG_IMMUTABLE)
    }

    val notification = NotificationCompat.Builder(context, "channel_id")
        .setSmallIcon(R.drawable.ic_launcher_foreground)
        .setContentTitle("Custom notification!")
        .setContentText("Tap to open the app")
        .setPriority(NotificationCompat.PRIORITY_MAX)
        .setAutoCancel(true)
        .setContentIntent(pendingIntent)
        .build()

    val notificationManager = context.getSystemService<NotificationManager>()!!
    notificationManager.notify(1, notification)
}

private val notification_1 = LessonPage (
//    headingResId = R.string.notification_1_heading
) {
    val context = LocalContext.current

    Button(onClick = {
        sendNotification(context)
    }) {
        Text("Send notification")
    }
}

val notificationPages = listOf(
    notification_1
)