package com.example.controlepet.helpers.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.controlepet.dataBase.UserPreferences
import com.example.controlepet.repository.OfflineUserRepository
import com.example.controlepet.viewModel.cadastroUsuario.PerfilUserViewModel

class UserViewModelFactory(
    private val repository: OfflineUserRepository,
    private val prefs: UserPreferences
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PerfilUserViewModel::class.java)) {
            return PerfilUserViewModel(repository, prefs) as T
        }
        throw IllegalArgumentException("Classe desconhecida: $modelClass")
    }
}