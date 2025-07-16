package com.example.controlepet.screens.agenda

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.controlepet.model.AgendaCompleta
import com.example.controlepet.repository.OfflineAgendaRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ListAgendaViewModel(
    private val repo: OfflineAgendaRepository
): ViewModel() {

    val agendaList: StateFlow<List<AgendaCompleta>> = repo.getAllAgendasCompletas()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    private val _toastMessage = MutableSharedFlow<String>()
    val toastMessage = _toastMessage.asSharedFlow()

    private var _showDialog = MutableStateFlow(false)
    val showDialog: StateFlow<Boolean> = _showDialog

    fun setShowDialog(state: Boolean) {
        _showDialog.value = state
    }

    fun onDelete(agendaId: Int) {
        viewModelScope.launch {
            try {
                repo.deleteAgendaById(agendaId)
                _toastMessage.emit("Agenda excluída com sucesso")
            } catch (e: Exception) {
                _toastMessage.emit("Erro ao excluir: ${e.localizedMessage}")
            }
        }

    }
}