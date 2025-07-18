package com.example.controlepet.viewModel.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.controlepet.dataBase.UserPreferences
import com.example.controlepet.repository.OfflineUserRepository
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repo: OfflineUserRepository,
    private val prefs: UserPreferences
) : ViewModel() {

    var email by mutableStateOf("") 
    var password by mutableStateOf("")
    var isLoading by mutableStateOf(false)
    var errorMessage by mutableStateOf<String?>(null)

    fun login(onSuccess: () -> Unit) {
        isLoading = true
        viewModelScope.launch {
            val user = repo.login(email, password)
            isLoading = false
            if (user != null) {
                try {
                    prefs.saveUserId(user.id)
                    onSuccess()
                } catch (e: Exception) {
                    errorMessage = "Erro ao salvar login local: ${e.message}"
                }
            } else {
                errorMessage = "Email ou senha incorretos"
            }
        }
    }
}