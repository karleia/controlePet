package com.example.controlepet

import com.example.controlepet.screens.agenda.PerfilAgendaViewModel
import com.example.controlepet.testdoubles.FakeAgendaRepository
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
    val fakeRepo = FakeAgendaRepository(fixedTime)

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
        assertEquals("pincher", agendaCompleta?.pet?.breed)
        assertEquals("curta", agendaCompleta?.pet?.pelagem)
        assertEquals("Cliente Mock", agendaCompleta?.clientName)
    }
}