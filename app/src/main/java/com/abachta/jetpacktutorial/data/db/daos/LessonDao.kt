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

    @Query("SELECT * FROM DbLesson WHERE completed = 1 ORDER BY id DESC LIMIT 1")
    suspend fun getLastCompletedLesson(): DbLesson?

    @Query("DELETE FROM DbLesson")
    suspend fun removeAll()

    @Query("DELETE FROM DbLesson WHERE id IN (:ids)")
    suspend fun removeByIds(ids: List<Int>)

    @Query("DELETE FROM DbLesson WHERE id = :id")
    suspend fun removeById(id: Int)

}