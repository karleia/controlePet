package com.example.controlepet.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.controlepet.model.Agenda
import com.example.controlepet.model.AgendaCompleta
import com.example.controlepet.model.AgendaServices
import com.example.controlepet.model.PetWithClientName
import com.example.controlepet.model.Service
import kotlinx.coroutines.flow.Flow

@Dao
interface AgendaDAO {

    @Query("SELECT * FROM agenda")
    fun getAll(): Flow<List<Agenda>>

    @Insert
    suspend fun insert(agenda: Agenda): Long

    @Insert
    suspend fun insertAgendaServices(servicos: List<AgendaServices>)

    @Update
    suspend fun update(agenda: Agenda)

    @Delete
    suspend fun delete(agenda: Agenda)

    @Query("SELECT * from Agenda WHERE id = :id")
    fun getAgenda(id: Int): Flow<Agenda>

    @Query("SELECT * from Agenda WHERE id = :id")
    suspend fun getAgendaNow(id: Int): Agenda?

    @Query("DELETE FROM agenda WHERE id = :agendaId")
    suspend fun deleteById(agendaId: Int)

    @Query("DELETE FROM AgendaServices WHERE idAgenda = :agendaId")
    suspend fun deleteServicesByAgendaId(agendaId: Int)

    @Query("""
        SELECT pet.*, client.name AS client_name
        FROM pet 
        INNER JOIN client ON pet.clientId = client.id """)
    fun getAllPetsWithClientName(): Flow<List<PetWithClientName>>

    @Query("SELECT * FROM service")
    fun getAllServices(): Flow<List<Service>>

    /*@Transaction
    @Query("SELECT * FROM Agenda WHERE id = :agendaId")
    suspend fun getAgendaCompleta(agendaId: Int): AgendaCompleta?*/

    @Transaction
    @Query("""
    SELECT  
        agenda.id as agenda_id,
        agenda.idPet as agenda_idPet,
        agenda.date_time as agenda_date_time,
        agenda.createdAt as agenda_createdAt, 
        agenda.observation as agenda_observation, 
        pet.id AS pet_id, 
        pet.clientId AS pet_clientId, 
        pet.name AS pet_name, 
        pet.typePet AS pet_typePet, 
        pet.breed AS pet_breed, 
        pet.color AS pet_color, 
        pet.pelagem AS pet_pelagem, 
        pet.sex AS pet_sex, 
        pet.observation AS pet_observation,
        client.name AS client_name
    FROM agenda
    INNER JOIN pet ON agenda.idPet = pet.id
    INNER JOIN client ON pet.clientId = client.id
    WHERE agenda.id = :agendaId""")
    suspend fun getAgendaCompleta(agendaId: Int): AgendaCompleta?

    @Transaction
    @Query("""
    SELECT  
        agenda.id as agenda_id,
        agenda.idPet as agenda_idPet,
        agenda.date_time as agenda_date_time,
        agenda.createdAt as agenda_createdAt, 
        agenda.observation as agenda_observation, 
        pet.id AS pet_id, 
        pet.clientId AS pet_clientId, 
        pet.name AS pet_name, 
        pet.typePet AS pet_typePet, 
        pet.breed AS pet_breed, 
        pet.color AS pet_color, 
        pet.pelagem AS pet_pelagem, 
        pet.sex AS pet_sex, 
        pet.observation AS pet_observation,
        client.name AS client_name
    FROM agenda
    INNER JOIN pet ON agenda.idPet = pet.id
    INNER JOIN client ON pet.clientId = client.id""")
    fun getAllAgendasCompletas(): Flow<List<AgendaCompleta>>

}