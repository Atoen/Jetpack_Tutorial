package com.abachta.jetpacktutorial.data.db

import com.abachta.jetpacktutorial.data.Lesson
import com.abachta.jetpacktutorial.data.LessonId
import com.abachta.jetpacktutorial.data.db.daos.LessonDao
import com.abachta.jetpacktutorial.data.db.entities.DbLesson
import com.abachta.jetpacktutorial.courses.allLessons
import com.abachta.jetpacktutorial.courses.getLessonById
import javax.inject.Inject

//interface LessonRepository {
//    suspend fun getAllLessons(): List<Lesson>
//    suspend fun insertLesson(lesson: Lesson)
//    suspend fun removeAllLessons()
//    suspend fun removeLessonsByIds(ids: List<Int>)
//    suspend fun removeLessonById(id: Int)
//}

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
        }

        val mapped = allLessons.map { lesson ->
            DbLesson(lesson.id.value, lesson.isCompleted)
        }

        lessonDao.insertBulk(mapped)
    }

    suspend fun insertLesson(lesson: Lesson) {
        lessonDao.insert(DbLesson(lesson.id.value, lesson.isCompleted))
    }

    suspend fun getNextLesson(): Lesson? {
        val id = lessonDao.getFirstUncompletedLessonId()
        return id?.let {
            getLessonById(LessonId(it))
        }
    }

    suspend fun clearAllProgress() {
        lessonDao.clearAll()
    }

    suspend fun removeLessonsByIds(ids: List<Int>) {
        lessonDao.clearByIds(ids)
    }

    suspend fun removeLessonById(id: Int) {
        lessonDao.clearById(id)
    }
}