package com.example.controlepet.viewModel.cliente

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.controlepet.model.Client
import com.example.controlepet.repository.OfflineClientRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class PerfilClientViewModel(
    private val repo: OfflineClientRepository,
    clientId: Int
): ViewModel() {

    val client: StateFlow<Client?> = repo.getClient(clientId)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

}