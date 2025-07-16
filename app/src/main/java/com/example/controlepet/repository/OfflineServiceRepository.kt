package com.example.controlepet.repository

import com.example.controlepet.model.Service
import com.example.controleservice.DAO.ServiceDAO
import kotlinx.coroutines.flow.Flow

class OfflineServiceRepository(
    private val serviceDAO: ServiceDAO
): ServiceRepository {
    override fun getAll(): Flow<List<Service>> = serviceDAO.getAll()

    override fun getService(id: Int): Flow<Service?> = serviceDAO.getService(id)

    override suspend fun getServiceNow(id: Int): Service? = serviceDAO.getServiceNow(id)

    override fun getServiceFilter(filter: String): Flow<List<Service>> = serviceDAO.getServiceFilter(filter)

    override suspend fun insertService(service: Service) = serviceDAO.insert(service)

    override suspend fun deleteService(service: Service)  = serviceDAO.delete(service)

    override suspend fun deleteServiceById(id: Int) =  serviceDAO.deleteById(id)

    override suspend fun updateService(service: Service) = serviceDAO.update(service)
}