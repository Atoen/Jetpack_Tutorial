package com.abachta.jetpacktutorial.lessons

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.Lesson
import com.abachta.jetpacktutorial.data.LessonPage

val Introduction_1 = LessonPage {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Jetpack Compose is Android's modern toolkit for building native UI.",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

val Introduction_2 = LessonPage {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Compose simplifies and accelerates UI development on Android.",
            style = MaterialTheme.typography.bodyLarge
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
    pages = listOf(Introduction_1, Introduction_2)
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
