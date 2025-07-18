package com.example.controlepet

import com.example.controlepet.model.Agenda
import com.example.controlepet.model.AgendaCompleta
import com.example.controlepet.model.Pet
import com.example.controlepet.viewModel.agenda.PerfilAgendaViewModel
import com.example.controlepet.test.FakeAgendaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class PerfilAgendaViewModelTest {

    private val fixedTime = System.currentTimeMillis()
    val mockAgendaCompleta = AgendaCompleta(
        agenda = Agenda(
            id = 1,
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
        servicosSelecionados = emptyList()
    )
    val fakeRepo = FakeAgendaRepository(fixedTime, agendasMock = listOf(mockAgendaCompleta))

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setupDispatcher() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDownDispatcher() {
        Dispatchers.resetMain()
    }

    @Test
    fun `loadAgendaCompleta updates state`() = runTest {
        val vm = PerfilAgendaViewModel(fakeRepo, agendaId = 1)
        vm.loadAgendaCompleta(1)
        testDispatcher.scheduler.advanceUntilIdle()

        val agendaCompleta = vm.agendaCompleta.value
        assertNotNull(agendaCompleta)
        assertEquals(fixedTime, agendaCompleta?.agenda?.date_time)
        assertEquals("Mock Pet", agendaCompleta?.pet?.name)
        assertEquals("Pincher", agendaCompleta?.pet?.breed)
        assertEquals("curta", agendaCompleta?.pet?.pelagem)
        assertEquals("Cliente Mock", agendaCompleta?.clientName)
    }
}