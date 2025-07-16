package com.example.controlepet.repository

import com.example.controlepet.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getAllUsers(): Flow<List<User>>

    fun getUser(id: Int): Flow<User?>

    suspend fun getUserNow(id: Int): User?

    fun getUserFilter(filter: String): Flow<List<User>>

    suspend fun insertUser(user: User)

    suspend fun deleteUser(user: User)

    suspend fun updateUser(user: User)

    suspend fun login(email: String, password: String): User?

}