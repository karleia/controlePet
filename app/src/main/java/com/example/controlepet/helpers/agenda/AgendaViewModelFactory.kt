package com.example.controlepet.helpers.agenda
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.controlepet.repository.OfflineAgendaRepository
import com.example.controlepet.screens.agenda.AgendaViewModel
import com.example.controlepet.screens.agenda.ListAgendaViewModel
import com.example.controlepet.screens.agenda.PerfilAgendaViewModel

class AgendaViewModelFactory (
    private val repository: OfflineAgendaRepository,
    private val agendaId: Int
): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AgendaViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AgendaViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(ListAgendaViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ListAgendaViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(PerfilAgendaViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PerfilAgendaViewModel(repository, agendaId) as T
        }
        throw IllegalArgumentException("Classe desconhecida: $modelClass")
    }
}