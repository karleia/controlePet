package com.example.controleagendaServices.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.controlepet.model.AgendaServices
import kotlinx.coroutines.flow.Flow

@Dao
interface AgendaServicesDAO {

    @Query("SELECT * FROM agendaServices")
    fun getAll(): Flow<List<AgendaServices>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(agendaServices: AgendaServices)

    @Update
    suspend fun update(agendaServices: AgendaServices)

    @Delete
    suspend fun delete(agendaServices: AgendaServices)

    @Query("SELECT * from AgendaServices WHERE id = :id")
    fun getAgendaServices(id: Int): Flow<AgendaServices>

    @Query("SELECT * from AgendaServices WHERE id = :id")
    suspend fun getAgendaServicesNow(id: Int): AgendaServices?


}