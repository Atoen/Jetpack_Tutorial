package com.abachta.jetpacktutorial.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.abachta.jetpacktutorial.data.db.daos.LessonDao
import com.abachta.jetpacktutorial.data.db.entities.DbLesson

@Database(entities = [DbLesson::class], version = 1, exportSchema = false)
abstract class LessonDatabase : RoomDatabase() {
    abstract fun lessonDao(): LessonDao

//    companion object {
//        @Volatile
//        private var Instance: LessonDatabase? = null
//
//        fun getDatabase(context: Context): LessonDatabase {
//            return Instance ?: synchronized(this) {
//                Room.databaseBuilder(context, LessonDatabase::class.java, "lesson_database")
//                    .build()
//                    .also { Instance = it }
//            }
//        }
//    }
}