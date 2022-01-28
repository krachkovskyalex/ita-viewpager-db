package com.krachkovsky.viewpagerdbhomework.db.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.krachkovsky.viewpagerdbhomework.db.model.User

@Database(
    entities = [User::class],
    version = 1,
    exportSchema = false
)
abstract class UsersDatabase : RoomDatabase() {

    companion object {

        private var db: UsersDatabase? = null
        private val LOCK = Any()

        fun getInstance(context: Context): UsersDatabase {
            synchronized(LOCK) {
                db?.let { return it }
                val instance = Room.databaseBuilder(
                    context,
                    UsersDatabase::class.java,
                    "users_database"
                ).build()
                db = instance
                return instance
            }
        }
    }

    abstract fun usersDao(): UserDao
}