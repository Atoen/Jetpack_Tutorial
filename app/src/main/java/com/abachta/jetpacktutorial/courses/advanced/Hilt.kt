package com.abachta.jetpacktutorial.courses.advanced

import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.LessonPage
import com.abachta.jetpacktutorial.ui.components.CodeListing

private val hilt_1 = LessonPage(
    headingResId = R.string.hilt_1_heading
) {

    CodeListing(
        code = """
            plugins {
                ...
                id("com.google.dagger.hilt.android") version "2.51.1" apply false
            }
        """.trimIndent()
    )

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

    CodeListing(
        code = """
            @HiltAndroidApp
            class JetpackApplication : Application() { ... }
        """.trimIndent()
    )
}

private val hilt_3 = LessonPage(
    headingResId = R.string.hilt_3_heading
) {

    CodeListing(
        code = """
            @AndroidEntryPoint
            class ExampleActivity : AppCompatActivity() { ... }
        """.trimIndent()
    )

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

    // Generated components
}

private val hilt_10 = LessonPage(
    headingResId = R.string.hilt_10_heading
) {

    // Lifetimes
}

private val hilt_11 = LessonPage(
    headingResId = R.string.hilt_11_heading
) {
    
    // Scopes
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
    hilt_9,
    hilt_10,
    hilt_11,
)
