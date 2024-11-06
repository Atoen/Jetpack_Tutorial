package com.abachta.jetpacktutorial.courses.advanced

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.LessonPage
import com.abachta.jetpacktutorial.ui.components.CodeListing
import com.abachta.jetpacktutorial.ui.components.ResText

private val hilt_1 = LessonPage(
    headingResId = R.string.hilt_1_heading
) {

    ResText(R.string.hilt_1_1)

    ResText(R.string.hilt_1_2)

    CodeListing(
        code = """
            plugins {
                ...
                id("com.google.dagger.hilt.android") version "2.51.1" apply false
            }
        """.trimIndent()
    )

    ResText(R.string.hilt_1_3)

    CodeListing(
        code = """
            plugins {
                ...
                id("kotlin-kapt")
                id("com.google.dagger.hilt.android")
            }
            
            android {
                ...
                compileOptions {
                    sourceCompatibility = JavaVersion.VERSION_1_8
                    targetCompatibility = JavaVersion.VERSION_1_8
                }
            }
            
            dependencies {
                ...
                implementation("com.google.dagger:hilt-android:2.51.1")
                kapt("com.google.dagger:hilt-android-compiler:2.51.1")
            }
            
            kapt {
                correctErrorTypes = true
            }
        """.trimIndent()
    )
}

private val hilt_2 = LessonPage(
    headingResId = R.string.hilt_2_heading
) {

    ResText(R.string.hilt_2_1)

    CodeListing(
        code = """
            @HiltAndroidApp
            class JetpackApplication : Application() { ... }
        """.trimIndent()
    )

    ResText(R.string.hilt_2_2)
}

private val hilt_3 = LessonPage(
    headingResId = R.string.hilt_3_heading
) {

    ResText(R.string.hilt_3_1)

    CodeListing(
        code = """
            @AndroidEntryPoint
            class ExampleActivity : AppCompatActivity() { ... }
        """.trimIndent()
    )

    ResText(R.string.hilt_3_2)

    CodeListing(
        code = """
            @AndroidEntryPoint
            class MainActivity : AppCompatActivity() {
            
                @Inject lateinit var userSettings: UserSettingsAdapter
                ...
            }

        """.trimIndent()
    )
}

private val hilt_4 = LessonPage(
    headingResId = R.string.hilt_4_heading
) {

    ResText(R.string.hilt_4_1)

    CodeListing(
        code = """
            class UserSettingsAdapter @Inject constructor(
                private val service: UserSettingsService
            ) { ... }
        """.trimIndent()
    )
}

private val hilt_5 = LessonPage(
    headingResId = R.string.hilt_5_heading
) {

    ResText(R.string.hilt_5_1)

    ResText(R.string.hilt_5_2)

    ResText(R.string.hilt_5_3)

    CodeListing(
        code = """
            interface UserSettingService {
                fun someMethods()
            }
            
            class UserSettingsServiceImpl @Inject constructor(
                // dependencies
            ) : UserSettingService { ... }
        """.trimIndent()
    )

    CodeListing(
        code = """
            @Module
            @InstallIn(ActivityComponent::class)
            abstract class UserSettingsModule {

                @Binds
                abstract fun bindUserSettingsService(
                    impl: UserSettingsServiceImpl
                ): UserSettingService
            }
        """.trimIndent()
    )
}

private val hilt_6 = LessonPage(
    headingResId = R.string.hilt_6_heading
) {

    ResText(R.string.hilt_6_1)

    CodeListing(
        code = """
            @Module
            @InstallIn(ActivityComponent::class)
            object AnalyticsModule {
            
                @Provides
                fun provideUserSettingsService(
                    // dependencies
                ): UserSettingsService {
                    return UserSettings.Builder()
                        .someOption()
                        .build()
                }
            }
        """.trimIndent()
    )
}

private val hilt_7 = LessonPage(
    headingResId = R.string.hilt_7_heading
) {

    ResText(R.string.hilt_7_1)

    CodeListing(
        code = """
            @Qualifier
            @Retention(AnnotationRetention.BINARY)
            annotation class AuthInterceptorHttpClient
            
            @Qualifier
            @Retention(AnnotationRetention.BINARY)
            annotation class OtherHttpClient
        """.trimIndent()
    )

    ResText(R.string.hilt_7_2)

    CodeListing(
        code = """
            @Module
            @InstallIn(SingletonComponent::class)
            object NetworkModule {
            
                @Provides
                @AuthInterceptorHttpClient
                fun provideAuthInterceptorHttpClient(
                    authInterceptor: AuthInterceptor
                ): HttpClient { ... }
                
                @Provides
                @OtherHttpClient
                fun provideOtherHttpClient(
                    otherInterceptor: OtherInterceptor
                ): HttpClient { ... }
            }
        """.trimIndent()
    )

    ResText(R.string.hilt_7_3)

    CodeListing(
        code = """
            class ExampleServiceImpl @Inject constructor(
                @AuthInterceptorHttpClient private val authClient: HttpClient
            ) : {

                @OtherHttpClient
                @Inject lateinit var otherClient: HttpClient
            }
        """.trimIndent()
    )
}

private val hilt_8 = LessonPage(
    headingResId = R.string.hilt_8_heading
) {

    ResText(R.string.hilt_8_1)

    CodeListing(
        code = """
            class UserSettingsAdapter @Inject constructor(
                @ActivityContext private val context: Context,
                private val service: UserSettingsService
            ) { ... }
        """.trimIndent()
    )
}

private val hilt_9 = LessonPage(
    headingResId = R.string.hilt_9_heading
) {

    ResText(R.string.hilt_9_1)

    val table = remember {
        listOf(
            "SingletonComponent" to "Application",
            "ViewModelComponent" to "ViewModel",
            "ActivityComponent" to "Activity",
            "FragmentComponent" to "Fragment",
            "ViewComponent" to "View",
            "ServiceComponent" to "Service",
        )
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(8.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        ) {
            ResText(R.string.hilt_component)

            ResText(R.string.injector_for)
        }

        table.forEach { (first, second) ->
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                Text(
                    fontFamily = FontFamily.Monospace,
                    text = first
                )

                Text(second)
            }
        }
    }
}


val hiltPages = listOf(
    hilt_1,
    hilt_2,
    hilt_3,
    hilt_4,
    hilt_5,
    hilt_6,
    hilt_7,
    hilt_8,
    hilt_9
)
