package com.abachta.jetpacktutorial.courses.advanced

import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.courses.advanced.quizzes.biometricsQuiz
import com.abachta.jetpacktutorial.courses.advanced.quizzes.cameraQuiz
import com.abachta.jetpacktutorial.courses.advanced.quizzes.hiltQuiz
import com.abachta.jetpacktutorial.courses.advanced.quizzes.notificationQuiz
import com.abachta.jetpacktutorial.courses.advanced.quizzes.permissionsQuiz
import com.abachta.jetpacktutorial.courses.advanced.quizzes.roomQuiz
import com.abachta.jetpacktutorial.data.Lesson

private val permissions = Lesson(
    titleResId = R.string.lesson_permissions_title,
    descriptionResId = R.string.TODO,
    pages = permissionsPages,
    quiz = permissionsQuiz
)

private val notifications = Lesson(
    titleResId = R.string.lesson_notification_title,
    descriptionResId = R.string.TODO,
    pages = notificationPages,
    quiz = notificationQuiz
)

private val camera = Lesson(
    titleResId = R.string.lesson_camera_titile,
    descriptionResId = R.string.TODO,
    pages = cameraPages,
    quiz = cameraQuiz
)

private val biometrics = Lesson(
    titleResId = R.string.lesson_biometrics_title,
    descriptionResId = R.string.TODO,
    pages = biometricsPages,
    quiz = biometricsQuiz
)

private val hilt = Lesson(
    titleResId = R.string.lesson_hilt_title,
    descriptionResId = R.string.TODO,
    pages = hiltPages,
    quiz = hiltQuiz
)

private val room = Lesson(
    titleResId = R.string.lesson_room_title,
    descriptionResId = R.string.TODO,
    pages = roomPages,
    quiz = roomQuiz
)

val advancedLessons = listOf(
    permissions,
    notifications,
    camera,
    biometrics,
    hilt,
    room
)