package com.abachta.jetpacktutorial.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.COURSE_QUIZ_ID_OFFSET
import com.abachta.jetpacktutorial.data.CodeChallenge
import com.abachta.jetpacktutorial.data.Course
import com.abachta.jetpacktutorial.data.CourseId
import com.abachta.jetpacktutorial.data.Lesson
import com.abachta.jetpacktutorial.data.Quiz
import com.abachta.jetpacktutorial.data.QuizId
import com.abachta.jetpacktutorial.data.getCourseById
import com.abachta.jetpacktutorial.data.models.completedCount
import com.abachta.jetpacktutorial.ui.Screen
import com.abachta.jetpacktutorial.ui.SnackbarController
import com.abachta.jetpacktutorial.ui.SnackbarEvent
import com.abachta.jetpacktutorial.ui.components.CourseQuizCard
import com.abachta.jetpacktutorial.ui.components.LessonCard
import com.abachta.jetpacktutorial.ui.components.LessonPopupActions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun CourseScreen(
    courseData: Screen.Course,
    onLessonClick: (Lesson) -> Unit,
    onGoToQuiz: (Quiz) -> Unit,
    onGoToChallenge: (CodeChallenge) -> Unit,
    onBookmarkLesson: (Lesson) -> Unit,
    onUnbookmarkLesson: (Lesson) -> Unit,
) {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = stringResource(courseData.titleResId),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        val course = getCourseById(CourseId(courseData.courseId))
        val lessons = course.lessons

        Text(
            text = stringResource(R.string.completed_n_of, lessons.completedCount(), lessons.count()),
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(4.dp))

        val context = LocalContext.current
        val scope = rememberCoroutineScope()
        val interceptor = remember {
            BookmarkInterceptor(
                context = context,
                coroutineScope = scope,
                bookmarkCallback = onBookmarkLesson,
                unBookmarkCallback = onUnbookmarkLesson
            )
        }

        val actions = remember {
            LessonPopupActions.CourseScreen(
                onGoToQuiz = onGoToQuiz,
                onGoToChallenge = onGoToChallenge,
                onBookmarkLesson = interceptor::bookmark,
                onUnbookmarkLesson = interceptor::unbookmark
            )
        }

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            items(lessons) { lesson ->
                LessonCard(
                    lesson = lesson,
                    onClick = { onLessonClick(lesson) },
                    popupActions = actions
                )
            }

            item {
                CourseQuizCard(
                    onClick = {
                        val quiz = createCourseQuiz(course)
                        onGoToQuiz(quiz)
                    },
                    highlighted = course.isCompleted
                )
            }
        }
    }
}

private class BookmarkInterceptor(
    val context: Context,
    val coroutineScope: CoroutineScope,
    val bookmarkCallback: (Lesson) -> Unit,
    val unBookmarkCallback: (Lesson) -> Unit
) {

    fun bookmark(lesson: Lesson) = bookmarkCallback(lesson).also {
        notify(
            lesson = lesson,
            messageTemplateId = R.string.bookmarked_lesson_template
        )
    }

    fun unbookmark(lesson: Lesson) = unBookmarkCallback(lesson).also {
        notify(
            lesson = lesson,
            messageTemplateId = R.string.unbookmarked_lesson_template
        )
    }

    private fun notify(
        lesson: Lesson,
        messageTemplateId: Int
    ) {
        coroutineScope.launch {
            val resources = context.resources
            val lessonName = resources.getString(lesson.titleResId)
            val message = resources.getString(messageTemplateId, lessonName)

            SnackbarController.sendEvent(SnackbarEvent(
                message = message,
                SnackbarDuration.Short
            ))
        }
    }
}

private fun createCourseQuiz(course: Course): Quiz {
    val id = QuizId(course.id.value + COURSE_QUIZ_ID_OFFSET)

    return Quiz(
        titleResId = course.titleResId,
        questions = listOf(),
        id = id
    )
}