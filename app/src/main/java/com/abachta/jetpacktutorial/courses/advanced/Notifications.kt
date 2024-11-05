package com.abachta.jetpacktutorial.courses.advanced

import android.Manifest
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import androidx.compose.material3.Button
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.core.app.NotificationCompat
import androidx.core.content.getSystemService
import com.abachta.jetpacktutorial.MainActivity
import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.LessonPage
import com.abachta.jetpacktutorial.ui.components.CodeListing
import com.abachta.jetpacktutorial.ui.components.ResText

private fun sendBasicNotification(
    context: Context,
    title: String,
    content: String
) {
    val notification = NotificationCompat.Builder(context, "channel_id")
        .setSmallIcon(R.drawable.jetpack_compose)
        .setContentTitle(title)
        .setContentText(content)
        .build()

    val notificationManager = context.getSystemService<NotificationManager>()!!
    notificationManager.notify(1, notification)
}

private fun sendNotificationWithIntent(
    context: Context,
    title: String,
    content: String
) {
    val activityIntent = Intent(context, MainActivity::class.java)
    val pendingIntent = TaskStackBuilder.create(context).run {
        addNextIntentWithParentStack(activityIntent)
        getPendingIntent(0, PendingIntent.FLAG_IMMUTABLE)
    }

    val notification = NotificationCompat.Builder(context, "channel_id")
        .setSmallIcon(R.drawable.jetpack_compose)
        .setContentTitle(title)
        .setContentText(content)
        .setAutoCancel(true)
        .setContentIntent(pendingIntent)
        .build()

    val notificationManager = context.getSystemService<NotificationManager>()!!
    notificationManager.notify(2, notification)
}

private fun sendNotificationWithButton(
    context: Context,
    title: String,
    content: String,
    actionTitle: String
) {

    val activityIntent = Intent(context, MainActivity::class.java)
    val pendingIntent = TaskStackBuilder.create(context).run {
        addNextIntentWithParentStack(activityIntent)
        getPendingIntent(0, PendingIntent.FLAG_IMMUTABLE)
    }

    val notification = NotificationCompat.Builder(context, "channel_id")
        .setSmallIcon(R.drawable.jetpack_compose)
        .setContentTitle(title)
        .setContentText(content)
        .addAction(R.drawable.chart_histogram, actionTitle, pendingIntent)
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .build()

    val notificationManager = context.getSystemService<NotificationManager>()!!
    notificationManager.notify(3, notification)
}

const val GROUP_KEY_1 = "com.abachta.jetpacktutorial.GROUP_1"
const val GROUP_KEY_2 = "com.abachta.jetpacktutorial.GROUP_2"

private fun sendGroupedNotifications(context: Context) {
    val notification1 = NotificationCompat.Builder(context, "channel_id")
        .setSmallIcon(R.drawable.jetpack_compose)
        .setContentTitle("First notification")
        .setContentText("Group 1")
        .setGroup(GROUP_KEY_1)
        .build()

    val notification2 = NotificationCompat.Builder(context, "channel_id")
        .setSmallIcon(R.drawable.jetpack_compose)
        .setContentTitle("Second notification")
        .setContentText("Group 1")
        .setGroup(GROUP_KEY_1)
        .build()

    val notification3 = NotificationCompat.Builder(context, "channel_id")
        .setSmallIcon(R.drawable.jetpack_compose)
        .setContentTitle("Third notification")
        .setContentText("Group 2")
        .setGroup(GROUP_KEY_2)
        .build()

    val summaryNotification = NotificationCompat.Builder(context, "channel_id")
        .setSmallIcon(R.drawable.jetpack_compose)
        .setContentTitle("Notifications summary")
        .setContentText("Two new messages")
        .setGroup(GROUP_KEY_1)
        .setGroupSummary(true)
        .build()


    context.getSystemService<NotificationManager>()?.apply {
        notify(5, notification1)
        notify(6, notification2)
        notify(7, notification3)
        notify(8, summaryNotification)
    }
}

private fun sendTextBlockNotification(
    context: Context,
    title: String,
    content: String,
    bigText: String,
) {
    val notification = NotificationCompat.Builder(context, "channel_id")
        .setSmallIcon(R.drawable.jetpack_compose)
        .setContentTitle(title)
        .setContentText(content)
        .setStyle(NotificationCompat.BigTextStyle()
            .bigText(bigText))
        .build()

    val notificationManager = context.getSystemService<NotificationManager>()!!
    notificationManager.notify(9, notification)
}

private fun sendImageNotification(
    context: Context,
    title: String,
    content: String,
) {
    val imageBitmap = BitmapFactory.decodeResource(context.resources, R.drawable.image_dog_landscape)

    val notification = NotificationCompat.Builder(context, "channel_id")
        .setSmallIcon(R.drawable.jetpack_compose)
        .setContentTitle(title)
        .setContentText(content)
        .setLargeIcon(imageBitmap)
        .setStyle(NotificationCompat.BigPictureStyle()
            .bigPicture(imageBitmap)
            .bigLargeIcon(null as Bitmap?))
        .build()

    val notificationManager = context.getSystemService<NotificationManager>()!!
    notificationManager.notify(9, notification)
}

private fun sendPrivateNotification(
    context: Context,
    title: String,
    content: String,
    publicTitle: String,
) {
    val publicVersion = NotificationCompat.Builder(context, "channel_id")
        .setSmallIcon(R.drawable.jetpack_compose)
        .setContentTitle(publicTitle)
        .build()

    val notification = NotificationCompat.Builder(context, "channel_id")
        .setVisibility(NotificationCompat.VISIBILITY_PRIVATE)
        .setPublicVersion(publicVersion)
        .setSmallIcon(R.drawable.jetpack_compose)
        .setContentTitle(title)
        .setContentText(content)
        .build()

    val notificationManager = context.getSystemService<NotificationManager>()!!
    notificationManager.notify(10, notification)
}

private val notification_1 = LessonPage (
   headingResId = R.string.notification_1_heading
) {

    ResText(R.string.notification_1_1)

    CodeListing(
        code = """
            ...
            <uses-permission android:name="android.permission.POST_NOTIFICATIONS" />
            ...
        """.trimIndent()
    )

    ResText(R.string.notification_1_2)

    CodeListing(
        code = """
            class JetpackApplication : Application() {
            
                override fun onCreate() {
                    super.onCreate()
                    createNotificationChannel()
                }
            
                private fun createNotificationChannel() {
                    val channel = NotificationChannel(
                        "channel_id",
                        "channel_name",
                        NotificationManager.IMPORTANCE_DEFAULT
                    )
                    
                    getSystemService<NotificationManager>()
                        ?.createNotificationChannel(channel)
                }
            }
        """.trimIndent()
    )
}

private val notification_2 = LessonPage (
   headingResId = R.string.notification_2_heading
) {

    ResText(R.string.notification_2_1)

    ResText(R.string.notification_2_2)

    CodeListing(
        code = """
            val context = LocalContext.c-current 
            
            val notification = NotificationCompat
                .Builder(context, "channel_id")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Custom notification!")
                .setContentText("Notification content text")
                .build()
                
            val id = 1
            getSystemService<NotificationManager>()
                ?.notify(id, notification)
        """.trimIndent()
    )

    val context = LocalContext.current
    val title = stringResource(R.string.notification_2_title)
    val content = stringResource(R.string.notification_2_content)

    Button(
        onClick = {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                it.requestPermission(Manifest.permission.POST_NOTIFICATIONS)
            }
            sendBasicNotification(context, title, content)
        },
        modifier = Modifier.align(Alignment.CenterHorizontally)
    ) {
        ResText(R.string.notification_2_send)
    }
}

private val notification_3 = LessonPage (
   headingResId = R.string.notification_3_heading
) {

    ResText(R.string.notification_3_1)

    CodeListing(
        code = """
            ...
            val activityIntent = Intent(context, MainActivity::class.java)
            val pendingIntent = TaskStackBuilder.create(context).run {
                addNextIntentWithParentStack(activityIntent)
                getPendingIntent(0, PendingIntent.FLAG_IMMUTABLE)
            }
            
            val notification = NotificationCompat
                .Builder(context, "channel_id")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(title)
                .setContentText(content)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .build()
        """.trimIndent()
    )

    val context = LocalContext.current
    val title = stringResource(R.string.notification_2_title)
    val content = stringResource(R.string.notification_3_content)

    Button(
        onClick = {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                it.requestPermission(Manifest.permission.POST_NOTIFICATIONS)
            }
            sendNotificationWithIntent(context, title, content)
        },
        modifier = Modifier.align(Alignment.CenterHorizontally)
    ) {
        ResText(R.string.notification_2_send)
    }
}

private val notification_4 = LessonPage (
   headingResId = R.string.notification_4_heading
) {

    ResText(R.string.notification_4_1)

    CodeListing(
        code = """
            ...
            val pendingIntent = ...
            
            val notification = NotificationCompat
                .Builder(context, "channel_id")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(title)
                .setContentText(content)
                .addAction(R.drawable.action, title1, pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .build()
        """.trimIndent()
    )

    val context = LocalContext.current
    val title = stringResource(R.string.notification_2_title)
    val content = stringResource(R.string.notification_3_content)
    val actionTitle = stringResource(R.string.notification_4_action_label)

    Button(
        onClick = {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                it.requestPermission(Manifest.permission.POST_NOTIFICATIONS)
            }
            sendNotificationWithButton(context, title, content, actionTitle)
        },
        modifier = Modifier.align(Alignment.CenterHorizontally)
    ) {
        ResText(R.string.notification_2_send)
    }
}

private val notification_5 = LessonPage (
   headingResId = R.string.notification_5_heading
) {

    ResText(R.string.notification_5_1)

    ResText(R.string.notification_5_2)

    CodeListing(
        code = """
            const val GROUP_KEY_1 = "com.example.app.GROUP_1"
            
            val notification = NotificationCompat
                .Builder(context, "channel_id")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(title)
                .setContentText(content)
                .setGroup(GROUP_KEY_1)
                .build()
                
            val summary = NotificationCompat
                .Builder(context, "channel_id")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Notifications summary")
                .setContentText("Two new messages")
                .setGroup(GROUP_KEY_1)
                .setGroupSummary(true)
                .build()
        """.trimIndent()
    )

    val context = LocalContext.current

    Button(
        onClick = {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                it.requestPermission(Manifest.permission.POST_NOTIFICATIONS)
            }
            sendGroupedNotifications(context)
        },
        modifier = Modifier.align(Alignment.CenterHorizontally)
    ) {
        ResText(R.string.notification_2_send)
    }
}

private val notification_6 = LessonPage (
   headingResId = R.string.notification_6_heading
) {

    ResText(R.string.notification_6_1)

    ResText(R.string.notification_6_2)

    CodeListing(
        code = """            
            val notification = NotificationCompat
                ...
                .setStyle(NotificationCompat.BigTextStyle()
                    .bigText(bigText))
                .build()
        """.trimIndent()
    )

    CodeListing(
        code = """            
            val imageBitmap = BitmapFactory
                .decodeResource(
                    context.resources,
                    R.drawable.image
                )
        
            val notification = NotificationCompat
                ...
                .setLargeIcon(imageBitmap)
                .setStyle(NotificationCompat.BigPictureStyle()
                    .bigPicture(imageBitmap)
                    .bigLargeIcon(null))
                .build()
        """.trimIndent()
    )

    val context = LocalContext.current
    val titleText = stringResource(R.string.notification_6_block_title)
    val titleImage = stringResource(R.string.notification_6_image_title)
    val content = stringResource(R.string.notification_6_block_content)
    val loremIpsum = stringResource(R.string.lorem_ipsum)

    Button(
        onClick = {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                it.requestPermission(Manifest.permission.POST_NOTIFICATIONS)
            }
            sendTextBlockNotification(context, titleText, content, loremIpsum)
        },
        modifier = Modifier.align(Alignment.CenterHorizontally)
    ) {
        ResText(R.string.notification_6_text)
    }

    Button(
        onClick = {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                it.requestPermission(Manifest.permission.POST_NOTIFICATIONS)
            }
            sendImageNotification(context, titleImage, content)
        },
        modifier = Modifier.align(Alignment.CenterHorizontally)
    ) {
        ResText(R.string.notification_6_image)
    }
}

private val notification_7 = LessonPage (
   headingResId = R.string.notification_7_heading
) {

    ResText(R.string.notification_7_1)

    CodeListing(
        code = """            
            context.getSystemService<NotificationManager>()
                ?.cancelAll()
        """.trimIndent()
    )

    CodeListing(
        code = """  
            val notificationId = 5
            context.getSystemService<NotificationManager>()
                ?.cancel(notificationId)
        """.trimIndent()
    )

    val context = LocalContext.current
    val title = stringResource(R.string.notification_2_title)
    val content = stringResource(R.string.notification_2_content)

    Button(
        onClick = {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                it.requestPermission(Manifest.permission.POST_NOTIFICATIONS)
            }
            sendBasicNotification(context, title, content)
        },
        modifier = Modifier.align(Alignment.CenterHorizontally)
    ) {
        ResText(R.string.notification_2_send)
    }

    Button(
        onClick = {
            context.getSystemService<NotificationManager>()?.cancel(5)
        },
        modifier = Modifier.align(Alignment.CenterHorizontally)
    ) {
        ResText(R.string.notification_cancel_all)
    }
}

private val notification_8 = LessonPage (
   headingResId = R.string.notification_8_heading
) {

    ResText(R.string.notification_8_1)

    ResText(R.string.notification_8_1)

    CodeListing(
        code = """          
              val publicVersion = NotificationCompat.Builder(context, "channel_id")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(publicTitle)
                .build()
                
            val notification = NotificationCompat
                .setVisibility(NotificationCompat.VISIBILITY_PRIVATE)
                .setPublicVersion(publicVersion)
                .Builder(context, "channel_id")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(title)
                .setContentText(content)
                .build()
        """.trimIndent()
    )

    val context = LocalContext.current
    val title = stringResource(R.string.notification_private_title)
    val content = stringResource(R.string.notification_2_content)
    val publicTitle = stringResource(R.string.notification_public_title)

    Button(
        onClick = {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                it.requestPermission(Manifest.permission.POST_NOTIFICATIONS)
            }
            sendPrivateNotification(context, title, content, publicTitle)
        },
        modifier = Modifier.align(Alignment.CenterHorizontally)
    ) {
        ResText(R.string.notification_2_send)
    }
}

val notificationPages = listOf(
    notification_1,
    notification_2,
    notification_3,
    notification_4,
    notification_5,
    notification_6,
    notification_7,
    notification_8
)