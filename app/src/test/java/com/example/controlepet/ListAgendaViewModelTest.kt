package com.example.controlepet

import app.cash.turbine.test
import com.example.controlepet.model.Agenda
import com.example.controlepet.model.AgendaCompleta
import com.example.controlepet.model.Pet
import com.example.controlepet.viewModel.agenda.ListAgendaViewModel
import com.example.controlepet.test.FakeAgendaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertTrue
import org.junit.Assert.assertEquals

class ListAgendaViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private val fixedTime = System.currentTimeMillis()

    private lateinit var viewModel: ListAgendaViewModel
    private lateinit var fakeRepository: FakeAgendaRepository

    //itens para mock
    val agenda1 = AgendaCompleta(
        agenda = Agenda(id = 1, idPet = 1, date_time = fixedTime, observation = "Obs 1", createdAt = fixedTime),
        pet = Pet (
            id = 1,
            clientId = 1,
            name = "Kita",
            typePet = "cachorro",
            breed = "Jack Russel",
            color = "branco",
            pelagem = "curta",
            sex = "femea",
            observation = "Nenhuma"
        ),
        clientName = "Cliente Mock",
        servicosSelecionados = listOf()
    )
    val agenda2 = AgendaCompleta(
        agenda = Agenda(id = 2, idPet = 2, date_time = fixedTime, observation = "Obs 2", createdAt = fixedTime),
        pet = Pet (
            id = 2,
            clientId = 1,
            name = "Simba",
            typePet = "cachorro",
            breed = "Pequines",
            color = "marrom",
            pelagem = "longa",
            sex = "macho",
            observation = "Nenhuma"
        ),
        clientName = "Cliente Mock",
        servicosSelecionados = listOf()
    )

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `agendaList should emit empty list`() = runTest {
        fakeRepository = FakeAgendaRepository(fixedTime)
        viewModel = ListAgendaViewModel(fakeRepository)
        viewModel.agendaLista.test {
            assertEquals(emptyList<AgendaCompleta>(), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `agendaList should emit list with items`() = runTest {

        // cria o repositório com as agendas mockadas
        fakeRepository = FakeAgendaRepository( fixedTime, listOf(agenda1, agenda2))
        viewModel = ListAgendaViewModel(fakeRepository)

        viewModel.agendaLista.test {

            //inicia com uma lista vazia
            val initialList = awaitItem()
            assertEquals(0, initialList.size)

            // Avança o dispatcher para que o novo valor seja emitido
            testDispatcher.scheduler.advanceUntilIdle()

            val list = awaitItem()
            assertEquals(2, list.size)
            assertTrue(list.contains(agenda1))
            assertTrue(list.contains(agenda2))
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `onDelete should emit success toast message`() = runTest {
        fakeRepository = FakeAgendaRepository(fixedTime,  listOf(agenda1, agenda2))
        viewModel = ListAgendaViewModel(fakeRepository, testDispatcher)

        // Avança o dispatcher para que o novo valor seja emitido
        testDispatcher.scheduler.advanceUntilIdle()

        viewModel.onDelete(1)
        // Avança o tempo
        testDispatcher.scheduler.advanceUntilIdle()

        viewModel.toastMessage.test {
            val message = awaitItem()
            assertEquals("Agenda excluída com sucesso", message)
            cancelAndIgnoreRemainingEvents()
        }
    }
}