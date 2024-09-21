package com.abachta.jetpacktutorial.viewmodels

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abachta.jetpacktutorial.data.Lesson
import com.abachta.jetpacktutorial.data.Preferences
import com.abachta.jetpacktutorial.data.courses
import com.abachta.jetpacktutorial.data.db.LessonRepository
import com.abachta.jetpacktutorial.lessons.getFirstLesson
import com.abachta.jetpacktutorial.lessons.tryGetNextLesson
import com.abachta.jetpacktutorial.ui.AppTheme
import com.abachta.jetpacktutorial.ui.components.LessonPopupData
import com.abachta.jetpacktutorial.ui.components.LessonPopupType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val preferences: Preferences,
    private val lessonRepository: LessonRepository
) : ViewModel() {

    private val _isReady = MutableStateFlow(false)
    private val _theme = mutableStateOf(AppTheme.Auto)

    val isReady = _isReady.asStateFlow()

    var popupLesson by mutableStateOf<Lesson?>(null)
        private set

    var popupType by mutableStateOf(LessonPopupType.Start)
        private set

    val lessonPopupData by derivedStateOf {
        popupLesson?.let {
            LessonPopupData(it, popupType)
        }
    }

    var theme: AppTheme
        get() = _theme.value
        set(value) {
            _theme.value = value
            viewModelScope.launch {
                preferences.setInt("theme", theme.value)
            }
        }

    init {
        viewModelScope.launch {
            theme = preferences.getInt("theme")?.let { AppTheme.fromInt(it) } ?: AppTheme.Auto

            getLessonToShowOnPopup()

            _isReady.value = true
        }
    }

    private suspend fun getLessonToShowOnPopup() {
        lessonRepository.updateLessonsProgress()

        val lastCompletedLesson = lessonRepository.getLastCompletedLesson()
        if (lastCompletedLesson == null) {
            popupLesson = getFirstLesson()
            popupType = LessonPopupType.Start
        } else {
            popupLesson = tryGetNextLesson(lastCompletedLesson.id)?.also {
                popupType = LessonPopupType.Continue
            }
        }
    }

    fun refreshLessonPopup() {
        viewModelScope.launch {
            val lastCompletedLesson = lessonRepository.getLastCompletedLesson()
            if (lastCompletedLesson == null) {
                popupLesson = getFirstLesson()
                popupType = LessonPopupType.Start
            } else {
                popupLesson = tryGetNextLesson(lastCompletedLesson.id)?.also {
                    popupType = LessonPopupType.Continue
                }
            }
        }
    }

    fun completeLesson(lesson: Lesson) {
        viewModelScope.launch {
            lessonRepository.insertLesson(lesson)
        }
    }

    fun clearLessons() {
        courses.forEach {
            it.progress.resetProgress()
        }

        viewModelScope.launch {
            lessonRepository.removeAllLessons()
        }
    }
}