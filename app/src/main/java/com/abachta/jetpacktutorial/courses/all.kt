package com.abachta.jetpacktutorial.courses

import android.util.Log
import com.abachta.jetpacktutorial.courses.advanced.advancedLessons
import com.abachta.jetpacktutorial.courses.animations.animationsLessons
import com.abachta.jetpacktutorial.courses.getting_started.gettingStartedLessons
import com.abachta.jetpacktutorial.courses.jetpack_basics.jetpackBasicsLessons
import com.abachta.jetpacktutorial.courses.layout.layoutLessons
import com.abachta.jetpacktutorial.courses.navigation.navigationLessons
import com.abachta.jetpacktutorial.courses.state_lifecycle.stateLessons
import com.abachta.jetpacktutorial.courses.styling.styingLessons
import com.abachta.jetpacktutorial.data.ChallengeId
import com.abachta.jetpacktutorial.data.CodeChallenge
import com.abachta.jetpacktutorial.data.Lesson
import com.abachta.jetpacktutorial.data.LessonId
import com.abachta.jetpacktutorial.data.Quiz
import com.abachta.jetpacktutorial.data.QuizId

val allLessons = listOf(
    gettingStartedLessons,
    jetpackBasicsLessons,
    layoutLessons,
    styingLessons,
    stateLessons,
    navigationLessons,
    animationsLessons,
    advancedLessons
).flatten()

val allQuizzes = allLessons.mapNotNull { it.quiz }
val allChallenges = allLessons.mapNotNull { it.challenge }

private val lessonMap = allLessons.associateBy { it.id }
private val quizMap = allQuizzes.associateBy { it.id }
private val challengeMap = allChallenges.associateBy { it.id }

fun getFirstLesson() = allLessons.first()

fun getLessonById(lessonId: LessonId): Lesson {
    return lessonMap.getOrElse(lessonId) {
        Log.e(null, "Unable to retrieve lesson with id $lessonId")
        allLessons.first()
    }
}

fun getQuizById(quizId: QuizId): Quiz {
    return quizMap.getOrElse(quizId) {
        Log.e(null, "Unable to retrieve quiz with id $quizId")
        allQuizzes.first()
    }
}

fun getChallengeById(challengeId: ChallengeId): CodeChallenge {
    return challengeMap.getOrElse(challengeId) {
        Log.e(null, "Unable to retrieve challenge with id $challengeId")
        allChallenges.first()
    }
}