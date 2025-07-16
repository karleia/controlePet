package com.example.controleservice.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.controlepet.model.Service
import kotlinx.coroutines.flow.Flow

@Dao
interface ServiceDAO {

    @Query("SELECT * FROM service")
    fun getAll(): Flow<List<Service>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(service: Service)

    @Update
    suspend fun update(service: Service)

    @Delete
    suspend fun delete(service: Service)

    @Query("DELETE FROM service WHERE id = :serviceId")
    suspend fun deleteById(serviceId: Int)

    @Query("SELECT * from Service WHERE id = :id")
    fun getService(id: Int): Flow<Service>

    @Query("SELECT * from Service WHERE id = :id")
    suspend fun getServiceNow(id: Int): Service?

    @Query("SELECT * FROM Service WHERE name LIKE '%' || :filter || '%' " )
    fun getServiceFilter(filter: String): Flow<List<Service>>

}