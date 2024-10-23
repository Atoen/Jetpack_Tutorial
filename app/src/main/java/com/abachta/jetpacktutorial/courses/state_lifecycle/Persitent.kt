package com.abachta.jetpacktutorial.courses.state_lifecycle

import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.LessonPage
import com.abachta.jetpacktutorial.ui.components.CodeListing

private val persistent_1 = LessonPage (
    headingResId = R.string.persistent_1_heading
) {

    CodeListing(
        code = """
        dependencies {
            // ...
            implementation("androidx.datastore:datastore-preferences:1.1.1")
        }
        """.trimIndent()
    )
}

private val persistent_2 = LessonPage (
    headingResId = R.string.persistent_2_heading
) {

    CodeListing(
        code = """
            private const val PREFERENCES_NAME = "settings"
            private val Context.dataStore by preferencesDataStore(
                name = PREFERENCES_NAME
            )
        """.trimIndent()
    )

    CodeListing(
        code = """
            class Preferences {
                suspend fun setString(context: Context, key: String, value: String) {
                    val preferencesKey = stringPreferencesKey(key)
                    context.dataStore.edit { preferences ->
                        preferences[preferencesKey] = value
                    }
                }
                
                suspend fun getString(context: Context, key: String): String? {
                    val preferencesKey = stringPreferencesKey(key)
                    val preferences = context.dataStore.data.firstOrNull()
                    return preferences?.get(preferencesKey)
                }
                
                // other methods
            }
        """.trimIndent()
    )
}

private val persistent_3 = LessonPage (
    headingResId = R.string.persistent_3_heading
) {

    CodeListing(
        code = """
            class AppViewModel : ViewModel() {
                private val preferences = Preferences()
                
                fun saveUsername(context: Context, username: String) {
                    viewModelScope.launch {
                        preferences.setString(
                            context = context,
                            key = "USERNAME",
                            value = username
                        )
                    }
                }
            }
        """.trimIndent()
    )

    CodeListing(
        code = """
            class AppViewModel(
                private val application: Application
            ) : AndroidViewModel(application) {
                private val preferences = Preferences()
                
                // access the context
                private fun getContext() = application.applicationContext
                
                fun saveUsername(username: String) {
                    // ...
                }
            }
        """.trimIndent()
    )
}

private val persistent_4 = LessonPage (
    headingResId = R.string.persistent_4_heading
) {

    CodeListing(
        code = """
            @Composable
            fun LoginScreen() {
                val viewModel = c-viewModel<AppViewModel>()
                val context = LocalContext.c-current
                
                val username by c-remember { mutableStateOf("") }
                
                c-LaunchedEffect(Unit) { 
                    username = viewModel.getUsername(context)
                }
                
                if (username.isNotEmpty()) {
                    // welcome back screen
                }
            }
            
        """.trimIndent()
    )
}

val persistentPages = listOf(
    persistent_1,
    persistent_2,
    persistent_3,
    persistent_4
)