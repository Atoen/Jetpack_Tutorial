package com.abachta.jetpacktutorial.lessons

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.Lesson
import com.abachta.jetpacktutorial.data.LessonPage
import com.abachta.jetpacktutorial.ui.components.CodeListing

val Introduction_1 = LessonPage {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "What is Jetpack Compose?",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Jetpack Compose is Android's modern, declarative UI toolkit designed to simplify and accelerate UI development.",
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Unlike the traditional XML-based approach, Compose enables developers to build UIs directly in Kotlin code, using a more intuitive and flexible paradigm.",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

val Introduction_2 = LessonPage {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Declarative UI with Composables",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "In Compose, UI elements (called Composables) are built as functions.",
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "These functions describe the UI's structure and appearance in a declarative manner, where changes in state automatically trigger updates to the UI, removing the need for manual refreshes.",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

val Introduction_3 = LessonPage {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Key Benefits of Jetpack Compose",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Compose offers several key benefits:",
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(8.dp))

        Column(modifier = Modifier.padding(start = 16.dp)) {
            Text("• Better code readability", style = MaterialTheme.typography.bodyLarge)
            Text("• Improved performance", style = MaterialTheme.typography.bodyLarge)
            Text("• Easier maintainability", style = MaterialTheme.typography.bodyLarge)
        }
    }
}

val Introduction_4 = LessonPage {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        CodeListing(
            code = """
                @Composable
                fun Greeting(name: String) {
                    // This is a greeting function with a very long comment text line
                    val greeting = "Hello, $\name!"
                    Text(text = greeting)
                }
            """.trimIndent(),
        )
    }
}

val SettingUpEnvironment_1 = LessonPage {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Step 1: Install Android Studio with Compose support.",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

val SettingUpEnvironment_2 = LessonPage {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Step 2: Set up the necessary SDK and tools for Jetpack Compose.",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

val CreatingFirstComponent_1 = LessonPage {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Create your first Composable function to display a text component.",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

val CreatingFirstComponent_2 = LessonPage {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Composable functions describe the UI in a declarative manner.",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

private val ComposeIntroduction = Lesson(
    titleResId = R.string.compose_introduction_title,
    descriptionResId = R.string.TODO,
    pages = listOf(Introduction_1, Introduction_2, Introduction_3, Introduction_4)
)

private val SettingUpEnvironment = Lesson(
    titleResId  = R.string.setting_up_environment_title,
    descriptionResId = R.string.TODO,
    pages = listOf(SettingUpEnvironment_1, SettingUpEnvironment_2)
)

private val CreatingFirstComponent = Lesson(
    titleResId  = R.string.creating_first_component_title,
    descriptionResId = R.string.TODO,
    pages = listOf(CreatingFirstComponent_1, CreatingFirstComponent_2)
)

val gettingStartedLessons = listOf(
    ComposeIntroduction,
    SettingUpEnvironment,
    CreatingFirstComponent
)
