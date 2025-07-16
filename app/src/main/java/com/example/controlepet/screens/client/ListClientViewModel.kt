package com.example.controlepet.screens.client

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.controlepet.model.Client
import com.example.controlepet.repository.OfflineClientRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ListClientViewModel(
    private val repo: OfflineClientRepository
): ViewModel() {

    val clientList: StateFlow<List<Client>> = repo.getAllClients()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    private val _toastMessage = MutableSharedFlow<String>()
    val toastMessage = _toastMessage.asSharedFlow()

    private var _showDialog = MutableStateFlow(false)
    val showDialog: StateFlow<Boolean> = _showDialog

    fun setShowDialog(state: Boolean) {
        _showDialog.value = state
    }

    fun onDelete(clientId: Int) {

        viewModelScope.launch {
            try {
                repo.deleteClientById(clientId)
                _toastMessage.emit("Cliente excluído com sucesso")
            } catch (e: Exception) {
                _toastMessage.emit("Erro ao excluir: ${e.localizedMessage}")
            }
        }

    }

}