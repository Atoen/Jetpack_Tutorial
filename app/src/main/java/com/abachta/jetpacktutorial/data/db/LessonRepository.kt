package com.abachta.jetpacktutorial.data.db

import com.abachta.jetpacktutorial.courses.allLessons
import com.abachta.jetpacktutorial.courses.getLessonById
import com.abachta.jetpacktutorial.data.Lesson
import com.abachta.jetpacktutorial.data.LessonId
import com.abachta.jetpacktutorial.data.db.daos.LessonDao
import com.abachta.jetpacktutorial.data.db.entities.DbLesson
import javax.inject.Inject

class LessonRepository @Inject constructor(
    private val lessonDao: LessonDao
) {
    suspend fun updateLessonsProgress() {
        lessonDao.getALl().forEach { dbLesson ->
            val lesson = getLessonById(LessonId(dbLesson.id))
            if (dbLesson.completed) {
                lesson.complete()
            } else {
                lesson.progress.reset()
            }

            lesson.isBookmarked = dbLesson.bookmarked
        }

        val mapped = allLessons.map { lesson ->
            DbLesson(lesson.id.value, lesson.isCompleted, lesson.isBookmarked)
        }

        lessonDao.insertBulk(mapped)
    }

    suspend fun insertLesson(lesson: Lesson) {
        lessonDao.insert(DbLesson(lesson.id.value, lesson.isCompleted, lesson.isBookmarked))
    }

    suspend fun getNextLesson(): Lesson? {
        val id = lessonDao.getFirstUncompletedLessonId()
        return id?.let {
            getLessonById(LessonId(it))
        }
    }

    suspend fun getBookmarkedLessons(): List<Lesson> {
        val ids = lessonDao.getBookmarkedLessonsId()
        return ids.map {
            getLessonById(LessonId(it))
        }
    }

    suspend fun clearAllProgress() {
        lessonDao.clearAll()
    }
}