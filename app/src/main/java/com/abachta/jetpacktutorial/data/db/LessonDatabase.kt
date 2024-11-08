package com.abachta.jetpacktutorial.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.abachta.jetpacktutorial.data.db.daos.LessonDao
import com.abachta.jetpacktutorial.data.db.entities.DbLesson

@Database(entities = [DbLesson::class], version = 2, exportSchema = false)
abstract class LessonDatabase : RoomDatabase() {
    abstract fun lessonDao(): LessonDao
}