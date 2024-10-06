package com.abachta.jetpacktutorial.viewmodels

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abachta.jetpacktutorial.courses.getQuizById
import com.abachta.jetpacktutorial.data.Course
import com.abachta.jetpacktutorial.data.Lesson
import com.abachta.jetpacktutorial.data.LessonId
import com.abachta.jetpacktutorial.data.QuizId
import com.abachta.jetpacktutorial.data.allCourses
import com.abachta.jetpacktutorial.data.db.LessonRepository
import com.abachta.jetpacktutorial.data.models.QuizModel
import com.abachta.jetpacktutorial.ui.components.LessonPopupData
import com.abachta.jetpacktutorial.ui.components.LessonPopupType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CourseViewModel @Inject constructor(
    private val lessonRepository: LessonRepository
) : ViewModel() {

    private val popupLesson = mutableStateOf<Lesson?>(null)
    private val popupType = mutableStateOf(LessonPopupType.Start)
    private var currentQuizModel by mutableStateOf<QuizModel?>(null)

    lateinit var courses: List<Course>

    val lessonPopupData by derivedStateOf {
        popupLesson.value?.let {
            LessonPopupData(it, popupType.value)
        }
    }

    var shouldAnimatePopup by mutableStateOf(true)

    init {
        viewModelScope.launch {
            courses = allCourses

            lessonRepository.updateLessonsProgress()
            getLessonToShowOnPopup()
        }
    }

    private suspend fun getLessonToShowOnPopup() {
        val lesson = lessonRepository.getNextLesson()

        if (lesson == null) {
            popupLesson.value = null
            return
        }

        popupLesson.value = lesson
        if (lesson.id.value == LessonId.START_ID) {
            popupType.value = LessonPopupType.Start
        } else {
            popupType.value = LessonPopupType.Continue
        }
    }

    fun getQuizModel(quizId: QuizId): QuizModel {
        val model = currentQuizModel
        if (model == null || model.id != quizId) {
            val newModel = QuizModel.crate(getQuizById(quizId))

            currentQuizModel = newModel
            return newModel
        }

        return model
    }

    fun refreshLessonPopup() {
        viewModelScope.launch {
            getLessonToShowOnPopup()
        }
    }

    fun markLessonCompleted(lesson: Lesson) {
        viewModelScope.launch {
            lesson.complete()
            lessonRepository.insertLesson(lesson)
        }
    }

    fun clearProgress() {
        courses.forEach {
            it.progress.resetProgress()
        }

        viewModelScope.launch {
            lessonRepository.clearAllProgress()
        }
    }
}