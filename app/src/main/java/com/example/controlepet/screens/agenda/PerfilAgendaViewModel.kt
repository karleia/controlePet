package com.example.controlepet.screens.agenda

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.controlepet.model.AgendaCompleta
import com.example.controlepet.model.PetWithClientName
import com.example.controlepet.repository.AgendaRepository
import com.example.controlepet.repository.OfflineAgendaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

open class PerfilAgendaViewModel(
    private val repo: AgendaRepository,
    agendaId: Int
): ViewModel() {

    internal val _agendaCompleta = MutableStateFlow<AgendaCompleta?>(null)
    open val agendaCompleta: StateFlow<AgendaCompleta?> = _agendaCompleta

    open fun loadAgendaCompleta(id: Int) {
        viewModelScope.launch {
            val agenda = repo.getAgendaCompleta(id)
            _agendaCompleta.value = agenda
        }
    }
}