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

    private var popupLesson by mutableStateOf<Lesson?>(null)
    private var popupType by mutableStateOf(LessonPopupType.Start)
    private var currentQuizModel by mutableStateOf<QuizModel?>(null)

    var shouldAnimatePopup by mutableStateOf(true)
    val lessonPopupData by derivedStateOf {
        popupLesson?.let {
            LessonPopupData(it, popupType)
        }
    }

    lateinit var courses: List<Course>

    init {
        viewModelScope.launch {
            courses = allCourses

            lessonRepository.updateLessonsProgress()
            updateLessonPopup()
        }
    }

    private suspend fun updateLessonPopup() {
        val lesson = lessonRepository.getNextLesson()

        if (lesson == null) {
            popupLesson= null
            return
        }

        popupLesson = lesson
        popupType = if (lesson.id.value == LessonId.START_ID) {
            LessonPopupType.Start
        } else LessonPopupType.Continue
    }

    fun getQuizModel(quizId: QuizId): QuizModel {
        val model = currentQuizModel
        if (model == null || model.id != quizId) {
            val newModel = QuizModel.crate(getQuizById(quizId))

            currentQuizModel = newModel
            return newModel
        }

        model.reset()
        return model
    }

    fun refreshLessonPopup() {
        viewModelScope.launch {
            updateLessonPopup()
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