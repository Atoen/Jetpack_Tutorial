package com.abachta.jetpacktutorial.data

import androidx.annotation.FloatRange
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.setValue

class CourseProgress(private val lessons: List<Lesson>) {

    var lessonsCompleted: Int = 0
        private set

    @get:FloatRange(from = 0.0, to = 1.0)
    var value by mutableFloatStateOf(0.0f)
        private set

    val isCompleted by derivedStateOf { value == 1.0f }

    fun completeLesson() {
        if (lessonsCompleted < lessons.count()) {
            lessonsCompleted++
            value = lessonsCompleted / lessons.count().toFloat()
        }
    }

    fun resetProgress() {
        lessonsCompleted = 0
        value = 0.0f
    }
}