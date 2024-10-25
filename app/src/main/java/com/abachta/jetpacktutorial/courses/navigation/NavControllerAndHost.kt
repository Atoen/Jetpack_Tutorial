package com.abachta.jetpacktutorial.courses.navigation

import android.Manifest
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.NotificationCompat
import androidx.core.content.getSystemService
import androidx.core.net.toUri
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.abachta.jetpacktutorial.MainActivity
import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.LessonPage
import com.abachta.jetpacktutorial.ui.components.CodeListing
import com.abachta.jetpacktutorial.ui.components.Preview
import kotlinx.serialization.Serializable

private val navigation_1 = LessonPage (
   headingResId = R.string.navigation_1_heading
) {

    CodeListing(
        code = """
            dependencies {
                // ...
            
                implementation("androidx.navigation:navigation-compose:2.8.3")
                
                // Used in typesafe navigation
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.1")
            }
        """.trimIndent()
    )

    CodeListing(
        code = """
            [plugins]
            // ...
            kotlin-serialization = { id = "org.jetbrains.kotlin.plugin.serialization", version.ref = "kotlin" }
        """.trimIndent()
    )

    CodeListing(
        code = """
            plugins {
                // ...
                alias(libs.plugins.jetbrains.kotlin.serialization)
        """.trimIndent()
    )

    CodeListing(
        code = """
            val navController = c-rememberNavController()            
        """.trimIndent()
    )
}

private val navigation_2 = LessonPage (
   headingResId = R.string.navigation_2_heading
) {

    CodeListing(
        code = """
            @Serializable
            object HomeScreen
            
            @Serializable
            object SettingsScreen
              
            val navController = c-rememberNavController() 
              
            c-NavHost(
                navController = navController,
                startDestination = HomeScreen,
                modifier = ...
            ) {
                composable<HomeScreen> {
                    // Home content
                }
                composable<HomeScreen> {
                    // Settings content
                }
            }
        """.trimIndent()
    )

    CodeListing(
        code = """              
            val navGraph by c-remember(navController) {
                navController.createGraph(startDestination = Home) {
                    composable<HomeScreen> {
                        // ...
                    }
                    composable<HomeScreen> {
                        // ...
                    }
                }
            }
            
            c-NavHost(navController, navGraph)
        """.trimIndent()
    )
}

@Serializable
object HomeScreen

@Serializable
object SettingsScreen

@Composable
private fun NavScreen(
    isHome: Boolean,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
        modifier = Modifier
            .fillMaxSize()
            .background(
                if (isHome) {
                    MaterialTheme.colorScheme.primaryContainer
                } else MaterialTheme.colorScheme.tertiaryContainer
            )
    ) {

        Row {
            if (!isHome) {
                Icon(
                    imageVector = Icons.Filled.Settings,
                    contentDescription = null,
                    modifier = Modifier.padding(end = 8.dp)
                )
            }

            val text = if (isHome) "Home" else "Settings"
            Text(text)
        }

        Button(onClick = onClick) {
            val text = if (isHome) "Go to settings" else "Go back"
            Text(text)
        }
    }
}

private val navigation_3 = LessonPage (
   headingResId = R.string.navigation_3_heading
) {

    CodeListing(
        code = """              
            c-NavHost(
                navController = navController,
                startDestination = HomeScreen
            ) {
                composable<HomeScreen> { 
                    c-HomeScreen(
                        onGoToSettings = {
                            navController.navigate(SettingsScreen)
                        }
                    )
                }
                composable<SettingsScreen> {
                    c-SettingsScreen(
                        onBack = {
                            navController.navigateUp()
                        }
                    )
                }
            }
        """.trimIndent()
    )

    val navController = rememberNavController()

    Preview(
        modifier = Modifier
            .align(alignment = Alignment.CenterHorizontally)
            .fillMaxWidth()
            .height(200.dp)
    ) {
        NavHost(
            navController = navController,
            startDestination = HomeScreen
        ) {
            composable<HomeScreen> {
                NavScreen(
                    isHome = true,
                    onClick = {
                        navController.navigate(SettingsScreen)
                    }
                )
            }
            composable<SettingsScreen> {
                NavScreen(
                    isHome = false,
                    onClick = {
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}

@Serializable
data class ProfileScreen(val id: String)

@Composable
private fun DataHome(
    onClick: (String) -> Unit
) {
    var id by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically)
    ) {

        OutlinedTextField(
            value = id,
            onValueChange = { id = it },
            singleLine = true,
            placeholder = {
                Text("Enter user id")
            }
        )

        Button(
            onClick = { onClick(id) },
            enabled = id.isNotBlank()
        ) {
            Text("Go to user profile")
        }
    }
}

@Composable
private fun DataProfile(
    profile: ProfileScreen,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically)
    ) {

        Text(
            text = "User: ${profile.id}",
            style = MaterialTheme.typography.bodyLarge
        )

        Button(
            onClick = onBack,
        ) {
            Text("Go back")
        }
    }
}

private val navigation_4 = LessonPage (
   headingResId = R.string.navigation_4_heading
) {

    CodeListing(
        code = """ 
            @Serializable
            data class Profile(val id: String)
            
            c-NavHost(
                navController = navController,
                startDestination = HomeScreen
            ) {
                composable<HomeScreen> {
                    c-HomeScreen(
                        onGoToProfile = { id ->
                            navController.navigate(ProfileScreen(id))
                        }
                    )
                }
                composable<ProfileScreen> {
                    val arg = it.toRoute<ProfileScreen>()
                    c-ProfileScreen(
                        profile = arg,
                        onBack = {
                            navController.navigateUp()
                        }
                    )
                }
            }
        """.trimIndent()
    )

    val navController = rememberNavController()

    Preview(
        modifier = Modifier
            .align(alignment = Alignment.CenterHorizontally)
            .fillMaxWidth()
            .height(200.dp)
    ) {
        NavHost(
            navController = navController,
            startDestination = HomeScreen
        ) {
            composable<HomeScreen> {
                DataHome(
                    onClick = { id ->
                        navController.navigate(ProfileScreen(id))
                    }
                )
            }
            composable<ProfileScreen> {
                val arg = it.toRoute<ProfileScreen>()
                DataProfile(
                    profile = arg,
                    onBack = {
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}

private fun sendDeeplinkNotification(context: Context, deepLinkId: Int) {
    val activityIntent = Intent(context, MainActivity::class.java).apply {
        data = "custom-scheme://deeplink-host/$deepLinkId".toUri()
    }
    val pendingIntent = TaskStackBuilder.create(context).run {
        addNextIntentWithParentStack(activityIntent)
        getPendingIntent(0, PendingIntent.FLAG_IMMUTABLE)
    }

    val notification = NotificationCompat.Builder(context, "channel_id")
        .setSmallIcon(R.drawable.ic_launcher_foreground)
        .setContentTitle("Deeplink notification!")
        .setContentText("Tap to navigate to the deeplink")
        .setPriority(NotificationCompat.PRIORITY_MAX)
        .setAutoCancel(true)
        .setContentIntent(pendingIntent)
        .build()

    val notificationManager = context.getSystemService<NotificationManager>()!!
    notificationManager.notify(1, notification)
}

private val navigation_5 = LessonPage (
   headingResId = R.string.navigation_5_heading
) {

    val context = LocalContext.current

    Column(
        modifier = Modifier.align(Alignment.CenterHorizontally)
    ) {
        Button(onClick = {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                it.requestPermission(Manifest.permission.POST_NOTIFICATIONS)
            }

            sendDeeplinkNotification(context, 20)
        }) {
            Text("Send deeplink notification")
        }
    }

    CodeListing(
        code = """ 
            composable<DeepLinkScreen>(
                deepLinks = listOf(
                    navDeepLink<DeepLinkScreen>(
                        basePath = "custom-scheme://deeplink-host"
                    )
                )
            ) {
                val id = it.toRoute<DeepLinkScreen>().id
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "The ID is ${'$'}id")
                }
            }
        """.trimIndent()
    )

    CodeListing(
        code = """
            ...
            <activity>
                <intent-filter>
                    <data android:scheme="custom-scheme" />
                    <data android:host="deeplink-host" />
                    <data android:host="www.deeplink-host" />
    
                    <category android:name="android.intent.category.DEFAULT" />
                    
                    <action android:name="android.intent.action.VIEW" />
                </intent-filter>
            </activity>
            ...
        """.trimIndent()
    )

}

val navControllerPages = listOf(
    navigation_1,
    navigation_2,
    navigation_3,
    navigation_4,
    navigation_5
)