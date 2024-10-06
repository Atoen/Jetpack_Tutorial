package com.abachta.jetpacktutorial.courses

import android.util.Log
import com.abachta.jetpacktutorial.data.Lesson
import com.abachta.jetpacktutorial.data.LessonId
import com.abachta.jetpacktutorial.courses.getting_started.gettingStartedLessons
import com.abachta.jetpacktutorial.courses.jetpack_basics.jetpackBasicsLessons
import com.abachta.jetpacktutorial.courses.jetpack_basics.jetpackBasicsQuizzes
import com.abachta.jetpacktutorial.data.Quiz
import com.abachta.jetpacktutorial.data.QuizId

val allLessons = listOf(
    gettingStartedLessons,
    jetpackBasicsLessons
).flatten()

private val lessonMap = allLessons.associateBy { it.id }

val allQuizzes = listOf(
    jetpackBasicsQuizzes
).flatten()

private val quizMap = allQuizzes.associateBy { it.id }

fun getFirstLesson() = allLessons.first()

fun getLessonById(lessonId: LessonId): Lesson {
    return lessonMap.getOrElse(lessonId) {
        Log.e(null, "Unable to retrieve lesson with id $lessonId")
        allLessons.first()
    }
}

fun tryGetNextLesson(lessonId: LessonId): Lesson? {
    return lessonMap[lessonId.next()]
}

fun getQuizById(quizId: QuizId): Quiz {
    return quizMap.getOrElse(quizId) {
        Log.e(null, "Unable to retrieve quiz with id $quizId")
        allQuizzes.first()
    }
}