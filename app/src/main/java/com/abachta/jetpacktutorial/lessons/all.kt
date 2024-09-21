package com.abachta.jetpacktutorial.lessons

import android.util.Log
import com.abachta.jetpacktutorial.data.Lesson
import com.abachta.jetpacktutorial.data.LessonId

private val lessons = listOf(
    gettingStartedLessons
).flatten()

private val lessonMap = lessons.associateBy { it.id }

fun getFirstLesson() = lessons.first()

fun getLessonById(lessonId: LessonId): Lesson {
    return lessonMap.getOrElse(lessonId) {
        Log.e(null, "Unable to retrieve lesson with id $lessonId")
        lessons.first()
    }
}

fun tryGetNextLesson(lessonId: LessonId): Lesson? {
    return lessonMap[lessonId.next()]
}