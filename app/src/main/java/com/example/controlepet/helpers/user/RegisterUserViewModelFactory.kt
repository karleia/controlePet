package com.example.controlepet.helpers.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.controlepet.repository.OfflineUserRepository
import com.example.controlepet.viewModel.cadastroUsuario.RegisterUserViewModel

class RegisterUserViewModelFactory(
    private val repository: OfflineUserRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterUserViewModel::class.java)) {
            return RegisterUserViewModel(repository) as T
        }
        throw IllegalArgumentException("Classe desconhecida: $modelClass")
    }
}