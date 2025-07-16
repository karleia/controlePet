package com.example.controlepet.repository

import com.example.controlepet.DAO.ClientDAO
import com.example.controlepet.model.Client
import kotlinx.coroutines.flow.Flow

class OfflineClientRepository (
    private val clientDAO: ClientDAO
): ClientRepository {

    override fun getAllClients(): Flow<List<Client>> = clientDAO.getAll()

    override fun getClient(id: Int): Flow<Client?> = clientDAO.getClient(id)

    override suspend fun getClientNow(id: Int): Client? = clientDAO.getClientNow(id)

    override suspend fun insertClient(client: Client) = clientDAO.insert(client)

    override suspend fun deleteClient(client: Client) = clientDAO.delete(client)

    override suspend fun deleteClientById(id: Int)  = clientDAO.deleteById(id)

    override suspend fun updateClient(client: Client) = clientDAO.update(client)

    override fun getClientFilter(filter: String): Flow<List<Client>> = clientDAO.getClientFilter(filter)

}