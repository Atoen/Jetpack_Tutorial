package com.abachta.jetpacktutorial.data.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.abachta.jetpacktutorial.data.db.entities.DbLesson

@Dao
interface LessonDao {

    @Query("SELECT * FROM DbLesson")
    suspend fun getALl(): List<DbLesson>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(lesson: DbLesson)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertBulk(lessons: List<DbLesson>)

    @Query("SELECT id FROM DbLesson WHERE completed = 0 ORDER BY id ASC LIMIT 1")
    suspend fun getFirstUncompletedLessonId(): Int?

    @Query("SELECT id FROM DbLesson WHERE bookmarked = 1 ORDER BY id ASC")
    suspend fun getBookmarkedLessonsId(): List<Int>

    @Query("UPDATE DbLesson SET completed = 0")
    suspend fun clearAll()
}
