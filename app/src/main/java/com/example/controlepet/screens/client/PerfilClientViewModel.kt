package com.example.controlepet.screens.client

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.controlepet.dataBase.UserPreferences
import com.example.controlepet.model.Client
import com.example.controlepet.repository.OfflineClientRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class PerfilClientViewModel(
    private val repo: OfflineClientRepository,
    clientId: Int
): ViewModel() {

    val client: StateFlow<Client?> = repo.getClient(clientId)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

}