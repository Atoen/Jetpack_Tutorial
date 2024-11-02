package com.abachta.jetpacktutorial.courses.advanced

import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.Lesson

private val permissions = Lesson(
    titleResId = R.string.lesson_permissions_title,
    descriptionResId = R.string.TODO,
    pages = permissionsPages
)

private val notifications = Lesson(
    titleResId = R.string.lesson_notification_title,
    descriptionResId = R.string.TODO,
    pages = notificationPages
)

private val camera = Lesson(
    titleResId = R.string.lesson_camera_titile,
    descriptionResId = R.string.TODO,
    pages = cameraPages
)

private val biometrics = Lesson(
    titleResId = R.string.lesson_biometrics_title,
    descriptionResId = R.string.TODO,
    pages = biometricsPages
)

private val hilt = Lesson(
    titleResId = R.string.lesson_hilt_title,
    descriptionResId = R.string.TODO,
    pages = hiltPages
)

private val room = Lesson(
    titleResId = R.string.lesson_room_title,
    descriptionResId = R.string.TODO,
    pages = listOf()
)

val advancedLessons = listOf(
    permissions,
    notifications,
    camera,
    biometrics,
    hilt,
    room
)