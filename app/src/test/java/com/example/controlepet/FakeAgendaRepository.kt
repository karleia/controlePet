package com.example.controlepet.testdoubles

import com.example.controlepet.model.Agenda
import com.example.controlepet.model.AgendaCompleta
import com.example.controlepet.model.AgendaServices
import com.example.controlepet.model.Pet
import com.example.controlepet.model.PetWithClientName
import com.example.controlepet.model.Service
import com.example.controlepet.repository.AgendaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeAgendaRepository(
    private val fixedTime: Long
) : AgendaRepository {

    override fun getAll(): Flow<List<Agenda>> = flowOf(emptyList())

    override fun getAgenda(id: Int): Flow<Agenda?> = flowOf(null)

    override suspend fun getAgendaNow(id: Int): Agenda? = null

    override suspend fun insertAgenda(agenda: Agenda): Long = 0L

    override suspend fun insertAgendaServices(service: List<AgendaServices>) {}

    override suspend fun insertAgendaWithServices(agenda: Agenda, services: List<AgendaServices>) {}

    override suspend fun deleteAgenda(agenda: Agenda) {}

    override suspend fun deleteAgendaById(id: Int) {}

    override suspend fun updateAgenda(agenda: Agenda) {}

    override fun getAllPetsWithClientName(): Flow<List<PetWithClientName>> = flowOf(emptyList())

    override fun getAllService(): Flow<List<Service>> = flowOf(emptyList())

    override suspend fun getAgendaCompleta(agendaId: Int): AgendaCompleta? {
        return AgendaCompleta(
            agenda = Agenda(
                id = agendaId,
                idPet = 1,
                date_time = fixedTime,
                createdAt = fixedTime,
                observation = "Teste"
            ),
            pet = Pet(
                id = 1,
                clientId = 1,
                name = "Mock Pet",
                typePet = "cachorro",
                breed = "Pincher",
                color = "preto",
                pelagem = "curta",
                sex = "femea",
                observation = "Nenhuma"
            ),
            clientName = "Cliente Mock",
            servicosSelecionados = listOf()
        )
    }

    override fun getAllAgendasCompletas(): Flow<List<AgendaCompleta>> = flowOf(emptyList())

    override suspend fun updateAgendaWithServices(agenda: Agenda, servicos: List<AgendaServices>) {
        // não implementado para testes simples
    }
}