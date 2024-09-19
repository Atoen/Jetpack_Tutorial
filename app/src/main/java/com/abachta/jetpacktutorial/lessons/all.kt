package com.abachta.jetpacktutorial.lessons

import android.util.Log
import com.abachta.jetpacktutorial.data.Lesson

private val lessons = listOf(
    gettingStartedLessons
).flatten()

private val lessonMap = lessons.associateBy { it.id }

fun getLessonById(lessonId: Int): Lesson {
    return lessonMap.getOrElse(lessonId) {
        Log.e(null, "Unable to retrieve lesson with id $lessonId")
        lessons.first()
    }
}

fun tryGetLessonById(lessonId: Int): Lesson? {
    return lessonMap[lessonId]
}