package com.example.controlepet

import com.example.controlepet.model.Agenda
import com.example.controlepet.model.AgendaCompleta
import com.example.controlepet.model.Pet
import com.example.controlepet.model.ServiceSelecionado
import com.example.controlepet.viewModel.agenda.AgendaViewModel
import com.example.controlepet.test.FakeAgendaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.assertNotNull
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class AgendaViewModelTest {
    private lateinit var fakeRepo: FakeAgendaRepository
    private lateinit var viewModel: AgendaViewModel

    private val testDispatcher = StandardTestDispatcher()

    private val fixedTime = 1_658_000_000_000L // timestamp fixo para testes

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        fakeRepo = FakeAgendaRepository(fixedTime, agendasMock = listOf(mockAgendaCompleta))
        viewModel = AgendaViewModel(fakeRepo)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `loadAgendaForEdit should load correct agenda`() = runTest {
        viewModel.loadAgendaForEdit(agendaId = 1)
        testDispatcher.scheduler.advanceUntilIdle()

        val loadedAgenda = viewModel.agendaCompleta.value
        assertNotNull(loadedAgenda)
        assertEquals(1, loadedAgenda?.agenda?.id)
        assertEquals("Teste", loadedAgenda?.agenda?.observation)
    }

    @Test
    fun `saveAgenda should insert new agenda and mark success`() = runTest {
        viewModel.dataHoraSelecionada = fixedTime
        viewModel.isSuccess = false

        val servicosSelecionados = listOf(
            ServiceSelecionado(idService = 1, name = "banho", price = 100.0),
            ServiceSelecionado(idService = 2, name = "tosa", price = 150.0)
        )

        viewModel.saveAgenda(servicosSelecionados, petId = 1)
        testDispatcher.scheduler.advanceUntilIdle()

        assertTrue(viewModel.isSuccess)
    }

    companion object {
        private val mockAgendaCompleta = AgendaCompleta(
            agenda = Agenda(
                id = 1,
                idPet = 1,
                date_time = 1_658_000_000_000L,
                createdAt = 1_658_000_000_000L,
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
    }
}