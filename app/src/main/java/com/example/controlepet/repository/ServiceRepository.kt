package com.example.controlepet.repository

import com.example.controlepet.model.Service
import kotlinx.coroutines.flow.Flow

interface ServiceRepository {

    fun getAll(): Flow<List<Service>>

    fun getService(id: Int): Flow<Service?>

    suspend fun getServiceNow(id: Int): Service?

    fun getServiceFilter(filter: String): Flow<List<Service>>

    suspend fun insertService(service: Service)

    suspend fun deleteService(service: Service)

    suspend fun deleteServiceById(id: Int)

    suspend fun updateService(service: Service)
}