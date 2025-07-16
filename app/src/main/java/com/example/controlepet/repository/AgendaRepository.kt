package com.example.controlepet.repository

import com.example.controlepet.model.Agenda
import com.example.controlepet.model.AgendaCompleta
import com.example.controlepet.model.PetWithClientName
import com.example.controlepet.model.Service
import com.example.controlepet.model.AgendaServices
import kotlinx.coroutines.flow.Flow

interface AgendaRepository {

    fun getAll(): Flow<List<Agenda>>

    fun getAgenda(id: Int): Flow<Agenda?>

    suspend fun getAgendaNow(id: Int): Agenda?

    suspend fun insertAgenda(agenda: Agenda): Long

    suspend fun insertAgendaServices(service: List<AgendaServices>)

    suspend fun insertAgendaWithServices(agenda: Agenda, services: List<AgendaServices>)

    suspend fun deleteAgenda(agenda: Agenda)

    suspend fun deleteAgendaById(id: Int)

    suspend fun updateAgenda(agenda: Agenda)

    fun getAllPetsWithClientName(): Flow<List<PetWithClientName>>

    fun getAllService(): Flow<List<Service>>

    suspend fun getAgendaCompleta(agendaId: Int): AgendaCompleta?

    fun getAllAgendasCompletas(): Flow<List<AgendaCompleta>>
}