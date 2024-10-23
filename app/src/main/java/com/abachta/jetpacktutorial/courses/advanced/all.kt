package com.abachta.jetpacktutorial.courses.advanced

import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.Lesson

private val notifications = Lesson(
    titleResId = R.string.lesson_notification_title,
    descriptionResId = R.string.TODO,
    pages = notificationPages
)

private val biometrics = Lesson(
    titleResId = R.string.lesson_biometrics_title,
    descriptionResId = R.string.TODO,
    pages = biometricsPages
)

val advancedLessons = listOf(
    notifications,
    biometrics,
)