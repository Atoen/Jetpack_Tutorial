package com.abachta.jetpacktutorial

import android.content.Context
import androidx.room.Room
import com.abachta.jetpacktutorial.data.db.LessonDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

//    @Provides
//    @Singleton
//    fun provideLessonRepository(lessonDao: LessonDao): LessonRepository {
//        return LessonRepositoryImpl(lessonDao)
//    }

    @Singleton
    @Provides
    fun provideLessonDatabase(@ApplicationContext context: Context): LessonDatabase {
        return Room.databaseBuilder(context, LessonDatabase::class.java, "lesson_database")
            .build()
    }

    @Singleton
    @Provides
    fun provideLessonDao(database: LessonDatabase) = database.lessonDao()

}