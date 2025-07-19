package com.example.controlepet.viewModel.agenda

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.controlepet.model.AgendaCompleta
import com.example.controlepet.repository.AgendaRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
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

    val agendaLista: StateFlow<List<AgendaCompleta>> = repo.getAllAgendasCompletas()
        .stateIn(viewModelScope, SharingStarted.Companion.WhileSubscribed(5000), emptyList())

    private val _toastMessageFlow = MutableSharedFlow<String>(replay = 1)
    val toastMessage = _toastMessageFlow.asSharedFlow()

    private var _showDialog = MutableStateFlow(false)
    val showDialog: StateFlow<Boolean> = _showDialog

    fun setShowDialog(state: Boolean) {
        _showDialog.value = state
    }

    fun onDelete(agendaId: Int) {
        viewModelScope.launch(dispatcher) {
            try {
                repo.deleteAgendaById(agendaId)
                _toastMessageFlow.emit(MENSAGEM_SUCESSO)
            } catch (e: Exception) {
                _toastMessageFlow.emit("$MENSAGEM_ERRO: ${e.localizedMessage}")
            }
        }
    }

    companion object {
        private const val MENSAGEM_SUCESSO = "Agenda excluída com sucesso"
        private const val MENSAGEM_ERRO = "Erro ao excluir"
    }
}