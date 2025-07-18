package com.example.controlepet.test

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.controlepet.model.Agenda
import com.example.controlepet.model.AgendaCompleta
import com.example.controlepet.model.Pet
import com.example.controlepet.screens.agenda.PerfilAgendaViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FakePerfilAgendaViewModel(
    iniciarComValor: Boolean = false
) : PerfilAgendaViewModel(
    repo = FakeAgendaRepository(System.currentTimeMillis()),
    agendaId = 1
) {

    private val _fakeAgendaCompleta = MutableStateFlow<AgendaCompleta?>(if (iniciarComValor) createFakeAgendaCompleta() else null)
    override val agendaCompleta: StateFlow<AgendaCompleta?> get() = _fakeAgendaCompleta

    init {
        Log.d("FakeVM", "FakePerfilAgendaViewModel init")
    }

    companion object {
        fun createFakeAgendaCompleta(id: Int = 1): AgendaCompleta {
            return AgendaCompleta(
                agenda = Agenda(
                    id = id,
                    idPet = 1,
                    date_time = 1720000000000L,
                    createdAt = 1720000000000L,
                    observation = "Observação de teste"
                ),
                pet = Pet(
                    id = 1,
                    clientId = 1,
                    name = "Pet Fake",
                    typePet = "Cachorro",
                    breed = "Labrador",
                    color = "Preto",
                    pelagem = "Curta",
                    sex = "Macho",
                    observation = "Saudável"
                ),
                clientName = "Cliente Fake",
                servicosSelecionados = listOf()
            )
        }
    }

    override fun loadAgendaCompleta(id: Int) {
        Log.d("FakeVM", "Loading fake agenda completa for id $id")
        viewModelScope.launch {
            _fakeAgendaCompleta.update { createFakeAgendaCompleta(id) }
        }
    }
}