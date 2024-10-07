package com.abachta.jetpacktutorial.data.models

import androidx.annotation.FloatRange
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import com.abachta.jetpacktutorial.data.Lesson

class CourseProgress(private val lessons: List<Lesson>) {

    val isCompleted by derivedStateOf { lessons.all { it.isCompleted } }

    @get:FloatRange(from = 0.0, to = 1.0)
    val value by derivedStateOf { lessons.completedProgress() }

    fun resetProgress() {
        lessons.forEach {
            it.progress.reset()
        }
    }
}

@FloatRange(from = 0.0, to = 1.0)
fun List<Lesson>.completedProgress(): Float =
    count { it.isCompleted } / count().toFloat()

fun List<Lesson>.completedCount(): Int =
    count { it.isCompleted }