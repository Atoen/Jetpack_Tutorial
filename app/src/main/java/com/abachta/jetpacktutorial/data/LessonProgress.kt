package com.abachta.jetpacktutorial.data

import androidx.annotation.FloatRange

class LessonProgress(private val pageCount: Int) {

    var pagesCompleted: Int = 0
        private set

    @get:FloatRange(from = 0.0, to = 1.0)
    var value: Float = 0.0f
        private set

    val isCompleted = value == 1.0f
}