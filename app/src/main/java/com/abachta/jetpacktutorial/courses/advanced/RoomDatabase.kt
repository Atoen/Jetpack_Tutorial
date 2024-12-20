package com.abachta.jetpacktutorial.courses.advanced

import com.abachta.jetpacktutorial.R
import com.abachta.jetpacktutorial.data.LessonPage
import com.abachta.jetpacktutorial.ui.components.CodeListing
import com.abachta.jetpacktutorial.ui.components.ResText

private val room_1 = LessonPage(
    headingResId = R.string.room_1_heading
) {

    ResText(R.string.room_1_1)

    CodeListing(
        code = """
            plugins {
                ...
                // for using ksp
                id("com.google.devtools.ksp") version "2.0.20-1.0.24" apply false
            }
        """.trimIndent()
    )

    CodeListing(
        code = """
            plugins {
                ...
                // for using ksp
                id("com.google.devtools.ksp")
            }
            
            android {
                ...
                ksp {
                    arg("room.schemaLocation", "${'$'}projectDir/schemas")
                }
            }
            
            dependencies {
                ...
                val room_version = "2.6.1"

                implementation("androidx.room:room-runtime:${'$'}room_version")
                annotationProcessor("androidx.room:room-compiler:${'$'}room_version")
                
                // Kotlin Extensions and Coroutines support
                implementation("androidx.room:room-ktx:${'$'}room_version")
            }
        """.trimIndent()
    )
}

private val room_2 = LessonPage(
    headingResId = R.string.room_2_heading
) {

    ResText(R.string.room_2_1)

    CodeListing(
        code = """
            @Entity
            data class User(
                @PrimaryKey val uid: Int,
                val firstName: String?,
                val lastName: String?
            )
        """.trimIndent()
    )

    ResText(R.string.room_2_2)

    CodeListing(
        code = """
            @Entity(tableName = "users")
            data class User(
                @PrimaryKey val uid: Int,
                @ColumnInfo(name = "first_name") val firstName: String?,
                @ColumnInfo(name = "last_name") val lastName: String?
            )
        """.trimIndent()
    )
}

private val room_3 = LessonPage(
    headingResId = R.string.room_3_heading
) {

    ResText(R.string.room_3_1)

    CodeListing(
        code = """
            @Entity
            data class User(
                @PrimaryKey(autoGenerate = true) val uid: Int,
                val firstName: String?,
                val lastName: String?
            )
        """.trimIndent()
    )

    CodeListing(
        code = """
            @Entity(primaryKeys = ["firstName", "lastName"])
            data class User(
                val firstName: String?,
                val lastName: String?
            )
        """.trimIndent()
    )
}

private val room_4 = LessonPage(
    headingResId = R.string.room_4_heading
) {

    ResText(R.string.room_4_1)

    CodeListing(
        code = """
            @Entity
            data class User(
                @PrimaryKey val id: Int,
                val firstName: String?,
                val lastName: String?,
                @Ignore val someField: SomeClass?
            )
        """.trimIndent()
    )

    ResText(R.string.room_4_2)

    CodeListing(
        code = """
            open class Base {
                var someField: SomeClass?
            }
            
            @Entity(ignoredColumns = ["someField"])
            data class User(
                @PrimaryKey val id: Int
            ) : Base()
        """.trimIndent()
    )
}

private val room_5 = LessonPage(
    headingResId = R.string.room_5_heading
) {

    ResText(R.string.room_5_1)

    CodeListing(
        code = """
            @Entity(indices = [Index(value = ["firstName", "lastName"])])
            data class User(
                @PrimaryKey val id: Int,
                val firstName: String?,
                val lastName: String?,
                @Ignore val someField: SomeClass?
            )
        """.trimIndent()
    )
}

private val room_6 = LessonPage(
    headingResId = R.string.room_6_heading
) {

    ResText(R.string.room_6_1)

    CodeListing(
        code = """
            @Dao
            interface UserDao {
                @Insert(onConflict = OnConflictStrategy.REPLACE)
                fun insertMany(vararg users: User)
                
                @Delete
                fun delete(user: User)
                
                @Query("SELECT * FROM user")
                fun getAll(): List<User>
            }
        """.trimIndent()
    )

    ResText(R.string.room_6_2)

}

private val room_7 = LessonPage(
    headingResId = R.string.room_7_heading
) {

    ResText(R.string.room_7_1)

    CodeListing(
        code = """
            data class UserName(
                @ColumnInfo(name = "first_name") val firstName: String?
            )
        """.trimIndent()
    )

    CodeListing(
        code = """
        @Query("SELECT first_name FROM user")
        fun getUserNames(): List<UserName>

        """.trimIndent()
    )

    ResText(R.string.room_7_2)

    CodeListing(
        code = """
        @Query("SELECT * FROM user WHERE first_name like :name")
        fun getUsersByName(name: String): List<User>

        """.trimIndent()
    )
}

private val room_8 = LessonPage(
    headingResId = R.string.room_8_heading
) {

    ResText(R.string.room_8_1)

    CodeListing(
        code = """
            @Dao
            interface UserDao {
                @Insert(onConflict = OnConflictStrategy.REPLACE)
                suspend fun insertMany(vararg users: User)
                
                @Query("SELECT * FROM user")
                suspend fun getAll(): List<User>
            }
        """.trimIndent()
    )

    ResText(R.string.room_8_2)

    CodeListing(
        code = """
            @Dao
            interface UserDao {
                @Query("SELECT * FROM user WHERE id = :id")
                fun getById(id: Int): Flow<User>
            }
        """.trimIndent()
    )
}

private val room_9 = LessonPage(
    headingResId = R.string.room_9_heading
) {

    ResText(R.string.room_9_1)

    CodeListing(
        code = """
            @Database(entities = [User::class], version = 1)
            abstract class UserDatabase : RoomDatabase() {
                abstract fun userDao(): UserDao
            }
        """.trimIndent()
    )

    ResText(R.string.room_9_2)

    CodeListing(
        code = """
            val db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java,
                "db-name"
            ).build()
        """.trimIndent()
    )
}

private val room_10 = LessonPage(
    headingResId = R.string.room_10_heading
) {

    ResText(R.string.room_10_1)

    CodeListing(
        code = """
            @Query(
                "SELECT * FROM user" +
                "JOIN message ON user.id = message.user_id"
            )
            fun loadUserAndMessages(): Map<User, List<Message>>
        """.trimIndent()
    )
}

private val room_11 = LessonPage(
    headingResId = R.string.room_11_heading
) {

    ResText(R.string.room_11_1)

    CodeListing(
        code = """
            data class Address(
                val street: String?,
                val city: String?
            )
            
            @Entity
            data class User(
                @PrimaryKey val id: Int,
                val name: String?,
                @Embedded val address: Address?
            )
        """.trimIndent()
    )

    ResText(R.string.room_11_2)

    ResText(R.string.room_11_3)

    CodeListing(
        code = """            
            @Entity
            data class User(
                @PrimaryKey val id: Int,
                val name: String?,
                @Embedded(prefix = address) val address: Address?
                @Embedded(prefix = billing) val billing: Billing?
            )
        """.trimIndent()
    )
}

private val room_12 = LessonPage(
    headingResId = R.string.room_12_heading
) {

    ResText(R.string.room_12_1)

    CodeListing(
        code = """
            @Entity
            data class User(
                @PrimaryKey val userId: Int,
                val name: String?
            )
            
            @Entity
            data class Message(
                @PrimaryKey val messageId: Int,
                val authorId: Int,
                val content: String?
            )
        """.trimIndent()
    )

    ResText(R.string.room_12_2)

    CodeListing(
        code = """
            data class UserWithMessages(
                @Embedded val user: User,
                @Relation(
                      parentColumn = "userId",
                      entityColumn = "authorId"
                )
                val messages: List<Message>
            )
        """.trimIndent()
    )

    ResText(R.string.room_12_3)

    CodeListing(
        code = """
            @Transaction
            @Query("SELECT * FROM User")
            fun getUserWithMessages(): List<UserWithMessages>
        """.trimIndent()
    )
}

private val room_13 = LessonPage(
    headingResId = R.string.room_13_heading
) {

    ResText(R.string.room_13_1)

    CodeListing(
        code = """
            @Entity
            data class User(
                @PrimaryKey val userId: Int,
                val name: String?
            )
            
            @Entity
            data class GroupChat(
                @PrimaryKey val groupId: Int,
                val groupName: String?
            )
            
            @Entity(primaryKeys = ["userId", "groupId"])
            data class UserGroupCrossRef(
                val userId: Int,
                val groupId: Int
            )
        """.trimIndent()
    )

    ResText(R.string.room_13_2)

    CodeListing(
        code = """
            data class UserWithGroups(
                @Embedded val user: User,
                @Relation(
                    parentColumn = "userId",
                    entityColumn = "groupId",
                    associateBy = Junction(
                        UserGroupCrossRef::class
                    )
                )
                val groups: List<GroupChat>
            )
        """.trimIndent()
    )

    ResText(R.string.room_13_3)

    CodeListing(
        code = """
            @Transaction
            @Query("SELECT * FROM User")
            fun getUserWithGroups(): List<UserWithGroups>
        """.trimIndent()
    )
}

val roomPages = listOf(
    room_1,
    room_2,
    room_3,
    room_4,
    room_5,
    room_6,
    room_7,
    room_8,
    room_9,
    room_10,
    room_11,
    room_12,
    room_13,
)