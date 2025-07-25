package com.example.controlepet.helpers.agenda
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.controlepet.repository.AgendaRepository
import com.example.controlepet.viewModel.agenda.AgendaViewModel
import com.example.controlepet.viewModel.agenda.ListAgendaViewModel
import com.example.controlepet.viewModel.agenda.PerfilAgendaViewModel

class AgendaViewModelFactory (
    private val repository: AgendaRepository,
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