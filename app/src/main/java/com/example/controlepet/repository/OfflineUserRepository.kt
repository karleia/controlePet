package com.example.controlepet.repository

import com.example.controlepet.DAO.UserDAO
import com.example.controlepet.model.PetWithClientName
import com.example.controlepet.model.User
import kotlinx.coroutines.flow.Flow

class OfflineUserRepository (
    private val userDAO: UserDAO
): UserRepository {
    override fun getAllUsers(): Flow<List<User>> = userDAO.getAll()

    override fun getUser(id: Int): Flow<User?> = userDAO.getUser(id)

    override suspend fun getUserNow(id: Int): User? = userDAO.getUserNow(id)

    override suspend fun insertUser(user: User) = userDAO.insert(user)

    override suspend fun deleteUser(user: User) = userDAO.delete(user)

    override suspend fun updateUser(user: User) = userDAO.update(user)

    override fun getUserFilter(filter: String): Flow<List<User>> = userDAO.getUserFilter(filter)

    override suspend fun login(email: String, password: String): User? {
        return userDAO.login(email, password)
    }
}