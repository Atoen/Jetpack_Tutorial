package com.abachta.jetpacktutorial.viewmodels

import androidx.compose.material3.SnackbarDuration
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abachta.jetpacktutorial.data.Lesson
import com.abachta.jetpacktutorial.data.Preferences
import com.abachta.jetpacktutorial.data.db.LessonRepository
import com.abachta.jetpacktutorial.lessons.getLessonById
import com.abachta.jetpacktutorial.lessons.tryGetLessonById
import com.abachta.jetpacktutorial.ui.AppTheme
import com.abachta.jetpacktutorial.ui.SnackbarController
import com.abachta.jetpacktutorial.ui.SnackbarEvent
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
    var lessonToContinue by mutableStateOf<Lesson?>(null)
        private set

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

            lessonRepository.getAllLessons()

            val lastCompletedLesson = lessonRepository.getLastCompletedLesson()
            lastCompletedLesson?.let {
                lessonToContinue = tryGetLessonById(it.id + 1)
            }

            _isReady.value = true
        }
    }

    fun completeLesson(lesson: Lesson) {
        viewModelScope.launch {
            lessonRepository.insertLesson(lesson)
        }
    }

    fun clearLessons() {
        viewModelScope.launch {
            lessonRepository.removeAllLessons()
        }
    }
}