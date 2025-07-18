package com.example.controlepet.screens.agenda

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.controlepet.model.AgendaCompleta
import com.example.controlepet.repository.AgendaRepository
import com.example.controlepet.repository.OfflineAgendaRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ListAgendaViewModel(
    private val repo: AgendaRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
): ViewModel() {

    val agendaList: StateFlow<List<AgendaCompleta>> = repo.getAllAgendasCompletas()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    private val _toastMessage = MutableSharedFlow<String>(replay = 1)
    val toastMessage = _toastMessage.asSharedFlow()

    private var _showDialog = MutableStateFlow(false)
    val showDialog: StateFlow<Boolean> = _showDialog

    fun setShowDialog(state: Boolean) {
        _showDialog.value = state
    }

    fun onDelete(agendaId: Int) {
        viewModelScope.launch(dispatcher) {
            try {
                repo.deleteAgendaById(agendaId)
                _toastMessage.emit("Agenda excluída com sucesso")
            } catch (e: Exception) {
                _toastMessage.emit("Erro ao excluir: ${e.localizedMessage}")
            }
        }

    }
}