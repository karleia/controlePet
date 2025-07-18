package com.example.controlepet.helpers.client

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.controlepet.repository.OfflineClientRepository
import com.example.controlepet.viewModel.cliente.ListClientViewModel

class ListClientViewModelFactory(
    private val repository: OfflineClientRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListClientViewModel::class.java)) {
            return ListClientViewModel(repository) as T
        }
        throw IllegalArgumentException("Classe desconhecida: $modelClass")
    }
}