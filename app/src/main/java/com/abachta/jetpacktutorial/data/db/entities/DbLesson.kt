package com.abachta.jetpacktutorial.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DbLesson(
    @PrimaryKey val id: Int,
    val completed: Boolean
)