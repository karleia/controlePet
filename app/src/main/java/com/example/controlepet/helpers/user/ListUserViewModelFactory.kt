package com.example.controlepet.helpers.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.controlepet.repository.OfflineUserRepository
import com.example.controlepet.viewModel.cadastroUsuario.ListUserViewModel

class ListUserViewModelFactory(
    private val repository: OfflineUserRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListUserViewModel::class.java)) {
            return ListUserViewModel(repository) as T
        }
        throw IllegalArgumentException("Classe desconhecida: $modelClass")
    }
}