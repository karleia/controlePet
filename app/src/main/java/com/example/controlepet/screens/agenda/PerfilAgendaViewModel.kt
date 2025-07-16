package com.example.controlepet.screens.agenda

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.controlepet.model.AgendaCompleta
import com.example.controlepet.model.PetWithClientName
import com.example.controlepet.repository.OfflineAgendaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PerfilAgendaViewModel(
    private val repo: OfflineAgendaRepository,
    agendaId: Int
): ViewModel() {

    private val _agendaCompleta = MutableStateFlow<AgendaCompleta?>(null)
    val agendaCompleta: StateFlow<AgendaCompleta?> = _agendaCompleta

    fun loadAgendaCompleta(id: Int) {
        viewModelScope.launch {
            val agenda = repo.getAgendaCompleta(id)
            _agendaCompleta.value = agenda
        }
    }

}