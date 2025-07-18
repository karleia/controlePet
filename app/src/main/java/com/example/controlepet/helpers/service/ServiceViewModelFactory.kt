package com.example.controlepet.helpers.service

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.controlepet.repository.OfflineServiceRepository
import com.example.controlepet.viewModel.servicos.ListServiceViewModel
import com.example.controlepet.viewModel.servicos.ServiceViewModel

class ServiceViewModelFactory(
    private val repository: OfflineServiceRepository,
    private val serviceId: Int
): ViewModelProvider.Factory{
     override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ServiceViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ServiceViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(ListServiceViewModel::class.java)) {
             @Suppress("UNCHECKED_CAST")
             return ListServiceViewModel(repository) as T
        }
        throw IllegalArgumentException("Classe desconhecida: $modelClass")
    }
}