package com.abachta.jetpacktutorial.courses.state_lifecycle

import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.LessonPage
import com.abachta.jetpacktutorial.ui.components.CodeListing

private val viewmodel_1 = LessonPage (
   headingResId = R.string.viewmodel_1_heading
) {

    CodeListing(
        code = """
            dependencies {
                // ...
                implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.6")
            }
        """.trimIndent()
    )

    CodeListing(
        code = """
        import androidx.lifecycle.ViewModel
        
        class AppViewModel : ViewModel() {
        }
        """.trimIndent()
    )
}

private val viewmodel_2 = LessonPage (
   headingResId = R.string.viewmodel_2_heading
) {

    CodeListing(
        code = """
            data class AppUiState(
                val isLoggedIn: Boolean = false,
                val username: String? = null
            )
            
            class AppViewModel : ViewModel() {
            
                // Expose screen ui state
                private val _uiState = MutableStateFlow(AppUiState())
                val uiState = _uiState.asStateFlow()
                
                // Handle business logic
                fun logIn(username: String) {
                    _uiState.update { currentState ->
                        currentState.copy(
                            isLoggedIn = true,
                            username = username
                        )
                    }
                }
            }
        """.trimIndent()
    )
}

private val viewmodel_3 = LessonPage (
   headingResId = R.string.viewmodel_3_heading
) {

    CodeListing(
        code = """
            @Composable
            fun LoginScreen(
                viewModel: AppViewModel = c-viewModel()
            ) {
                // or: val viewModel = viewModel<AppViewModel>()
                val uiState by viewModel.uiState.c-collectAsStateWithLifecycle()
            }
        """.trimIndent()
    )
}

private val viewmodel_4 = LessonPage (
   headingResId = R.string.viewmodel_4_heading
) {

    CodeListing(
        code = """
            class AppViewModel : ViewModel() {
                // ...
                
                fun doNetworkStuff() {
                    viewModelScope.launch {
                        suspendingNetworkStuff()
                    }
                }
            }
        """.trimIndent()
    )

    CodeListing(
        code = """
            class AppViewModel : ViewModel(
                private val coroutineScope: CoroutineScope = 
                    CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
            ) {
                // ...
                
                override fun onCleared() {
                    coroutineScope.cancel()
                }
            }
        """.trimIndent()
    )
}

private val viewmodel_5 = LessonPage (
    headingResId = R.string.viewmodel_5_heading
) {

}

val viewModelPages = listOf(
    viewmodel_1,
    viewmodel_2,
    viewmodel_3,
    viewmodel_4,
    viewmodel_5
)