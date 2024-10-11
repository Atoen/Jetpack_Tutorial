package com.abachta.jetpacktutorial.courses.getting_started

import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.courses.getting_started.quizzes.introductionQuiz
import com.abachta.jetpacktutorial.courses.getting_started.quizzes.settingUpEnvironmentQuiz
import com.abachta.jetpacktutorial.data.Lesson

private val introduction = Lesson(
    titleResId = R.string.lesson_introduction_title,
    descriptionResId = R.string.TODO,
    pages = introductionPages,
    quiz = introductionQuiz
)

private val settingUpEnvironment = Lesson(
    titleResId = R.string.lesson_setting_up_environment_title,
    descriptionResId = R.string.TODO,
    pages = settingUpEnvironmentPages,
    quiz = settingUpEnvironmentQuiz
)

val gettingStartedLessons = listOf(
    introduction,
    settingUpEnvironment
)

val gettingStartedQuizzes = listOf(
    introductionQuiz,
    settingUpEnvironmentQuiz
)