package com.abachta.jetpacktutorial.data.db

import com.abachta.jetpacktutorial.data.Lesson
import com.abachta.jetpacktutorial.data.db.daos.LessonDao
import com.abachta.jetpacktutorial.data.db.entities.DbLesson
import com.abachta.jetpacktutorial.lessons.getLessonById
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
    suspend fun getAllLessons(): List<Lesson> {
        return lessonDao.getALl().map { dbLesson ->
            val lesson = getLessonById(dbLesson.id)
            if (dbLesson.completed) {
                lesson.complete()
            } else {
                lesson.progress.reset()
            }
            lesson
        }
    }

    suspend fun insertLesson(lesson: Lesson) {
        lessonDao.insert(DbLesson(lesson.id, lesson.isCompleted))
    }

    suspend fun getLastCompletedLesson(): Lesson? {
        val completed = lessonDao.getLastCompletedLesson()
        return completed?.let {
            getLessonById(it.id)
        }
    }

    suspend fun removeAllLessons() {
        lessonDao.removeAll()
    }

    suspend fun removeLessonsByIds(ids: List<Int>) {
        lessonDao.removeByIds(ids)
    }

    suspend fun removeLessonById(id: Int) {
        lessonDao.removeById(id)
    }
}