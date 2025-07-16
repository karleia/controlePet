package com.example.controlepet.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.controlepet.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDAO {

    @Query("SELECT * FROM user")
    fun getAll(): Flow<List<User>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: User)

    @Update
    suspend fun update(user: User)

    @Delete
    suspend fun delete(user: User)

    @Query("SELECT * from User WHERE id = :id")
    fun getUser(id: Int): Flow<User>

    @Query("SELECT * from User WHERE id = :id")
    suspend fun getUserNow(id: Int): User?

    @Query("SELECT * FROM User WHERE name LIKE '%' || :filter || '%' OR document LIKE '%' || :filter || '%' " )
    fun getUserFilter(filter: String): Flow<List<User>>

    @Query("SELECT * FROM User WHERE email = :email AND password = :password LIMIT 1")
    suspend fun login(email: String, password: String): User?
}