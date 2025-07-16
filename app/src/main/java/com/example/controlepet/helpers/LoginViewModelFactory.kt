package com.example.controlepet.helpers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.controlepet.dataBase.UserPreferences
import com.example.controlepet.repository.OfflineUserRepository
import com.example.controlepet.screens.login.LoginViewModel

class LoginViewModelFactory(
    private val repository: OfflineUserRepository,
    private val prefs: UserPreferences
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(repository, prefs) as T
        }
        throw IllegalArgumentException("Classe desconhecida: $modelClass")
    }
}