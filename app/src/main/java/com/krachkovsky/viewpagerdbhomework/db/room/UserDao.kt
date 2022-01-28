package com.krachkovsky.viewpagerdbhomework.db.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.krachkovsky.viewpagerdbhomework.db.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun getAll(): Flow<List<User>>

    @Insert(onConflict = REPLACE)
    suspend fun insert(user: User)

    @Delete
    suspend fun delete(user: User)
}