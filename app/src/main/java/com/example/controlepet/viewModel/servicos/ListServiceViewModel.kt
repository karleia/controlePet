package com.example.controlepet.viewModel.servicos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.controlepet.model.Service
import com.example.controlepet.repository.OfflineServiceRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ListServiceViewModel(
    private val repo: OfflineServiceRepository
): ViewModel() {

     val serviceList: StateFlow<List<Service>> = repo.getAll()
       .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    private val _toastMessage = MutableSharedFlow<String>()
    val toastMessage = _toastMessage.asSharedFlow()

    private var _showDialog = MutableStateFlow(false)
    val showDialog: StateFlow<Boolean> = _showDialog

    fun setShowDialog(state: Boolean) {
        _showDialog.value = state
    }

    fun onDelete(serviceId: Int) {

        viewModelScope.launch {
            try {
                repo.deleteServiceById(serviceId)
                _toastMessage.emit("Serviço excluído com sucesso")
            } catch (e: Exception) {
                _toastMessage.emit("Erro ao excluir: ${e.localizedMessage}")
            }
        }

    }
}