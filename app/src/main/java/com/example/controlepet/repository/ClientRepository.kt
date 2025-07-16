package com.example.controlepet.repository

import com.example.controlepet.model.Client
import kotlinx.coroutines.flow.Flow

interface ClientRepository {
    fun getAllClients(): Flow<List<Client>>

    fun getClient(id: Int): Flow<Client?>

    suspend fun getClientNow(id: Int): Client?

    fun getClientFilter(filter: String): Flow<List<Client>>

    suspend fun insertClient(client: Client)

    suspend fun deleteClient(client: Client)

    suspend fun deleteClientById(id: Int)

    suspend fun updateClient(client: Client)

}