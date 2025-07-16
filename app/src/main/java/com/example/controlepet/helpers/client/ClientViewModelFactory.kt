package com.example.controlepet.helpers.client

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.controlepet.repository.OfflineClientRepository
import com.example.controlepet.screens.client.ClientViewModel
import com.example.controlepet.screens.client.PerfilClientViewModel

class ClientViewModelFactory(
    private val repository: OfflineClientRepository,
    private val clientId: Int
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ClientViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ClientViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(PerfilClientViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PerfilClientViewModel(repository, clientId) as T
        }
        throw IllegalArgumentException("Classe desconhecida: $modelClass")
    }
}