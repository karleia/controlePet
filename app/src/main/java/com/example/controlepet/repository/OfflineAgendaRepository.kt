package com.example.controlepet.repository

import com.example.controlepet.model.Agenda
import com.example.controlepet.DAO.AgendaDAO
import com.example.controlepet.model.AgendaCompleta
import com.example.controlepet.model.AgendaServices
import com.example.controlepet.model.PetWithClientName
import com.example.controlepet.model.Service
import kotlinx.coroutines.flow.Flow


class OfflineAgendaRepository(
    private val agendaDAO: AgendaDAO,
): AgendaRepository {
    override fun getAll(): Flow<List<Agenda>> = agendaDAO.getAll()

    override fun getAgenda(id: Int): Flow<Agenda?> = agendaDAO.getAgenda(id)

    override suspend fun getAgendaNow(id: Int): Agenda? = agendaDAO.getAgendaNow(id)

    override suspend fun insertAgenda(agenda: Agenda): Long = agendaDAO.insert(agenda)

    override suspend fun insertAgendaServices(service: List<AgendaServices>) = agendaDAO.insertAgendaServices(service)

    override suspend fun deleteAgenda(agenda: Agenda)  = agendaDAO.delete(agenda)

    override suspend fun deleteAgendaById(id: Int) =  agendaDAO.deleteById(id)

    override suspend fun updateAgenda(agenda: Agenda) = agendaDAO.update(agenda)

    override fun getAllPetsWithClientName(): Flow<List<PetWithClientName>> {
        return agendaDAO.getAllPetsWithClientName()
    }

    override fun getAllService(): Flow<List<Service>> {
        return agendaDAO.getAllServices()
    }

    override suspend fun getAgendaCompleta(agendaId: Int): AgendaCompleta? {
        return agendaDAO.getAgendaCompleta(agendaId)
    }

    override suspend fun insertAgendaWithServices(agenda: Agenda, servicos: List<AgendaServices>) {
        val agendaId = agendaDAO.insert(agenda).toInt()
        val listComAgendaId = servicos.map {
            it.copy(idAgenda = agendaId)
        }
        agendaDAO.insertAgendaServices(listComAgendaId)
    }

    override fun getAllAgendasCompletas(): Flow<List<AgendaCompleta>> {
        return agendaDAO.getAllAgendasCompletas()
    }

    override suspend fun updateAgendaWithServices(agenda: Agenda, servicos: List<AgendaServices>) {
        agendaDAO.update(agenda)
        agendaDAO.deleteServicesByAgendaId(agenda.id)
        agendaDAO.insertAgendaServices(servicos.map { it.copy(idAgenda = agenda.id) })
    }
}