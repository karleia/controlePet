package com.example.controlepet.viewModel.cadastroUsuario

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.controlepet.dataBase.UserPreferences
import com.example.controlepet.model.User
import com.example.controlepet.repository.OfflineUserRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlin.String

class PerfilUserViewModel(
    private val repo: OfflineUserRepository,
    private val userPreferences: UserPreferences
): ViewModel() {

    val user: StateFlow<User?> = userPreferences.loggedInUserId
        .filterNotNull()
        .flatMapLatest { id ->
            repo.getUser(id)
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            null
        )

    val id: StateFlow<Int> = user.map { it?.id ?: 0 }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    val name: StateFlow<String> = user.map { it?.name ?: "" }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), "")

    val email: StateFlow<String> = user.map { it?.email ?: "" }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), "")

    val typePeople: StateFlow<String> = user.map { it?.typePeople ?: "" }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), "")

    val document: StateFlow<String> = user.map { it?.document ?: "" }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), "")

    val cellPhone: StateFlow<String> = user.map { it?.cellPhone?: "" }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), "")

    val cellPhone2: StateFlow<String> = user.map { it?.cellPhone2 ?: "" }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), "")

    val description: StateFlow<String> = user.map { it?.description ?: "" }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), "")

    val cep: StateFlow<String> = user.map { it?.cep ?: "" }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), "")

    val address: StateFlow<String> = user.map { it?.address ?: "" }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), "")

    val neighborhood: StateFlow<String> = user.map { it?.neighborhood ?: "" }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), "")

    val city: StateFlow<String> = user.map { it?.city ?: "" }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), "")

    val state: StateFlow<String> = user.map { it?.state ?: "" }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), "")

    val number: StateFlow<String> = user.map { it?.number ?: "" }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), "")

    val complement: StateFlow<String> = user.map { it?.complement ?: "" }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), "")

    val observationAddress: StateFlow<String> = user.map { it?.observationAddress ?: "" }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), "")

    val password: StateFlow<String> = user.map { it?.password ?: "" }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), "")


    fun checkIfUserExists(id: Int, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            val user = repo.getUserNow(id)
            onResult(user != null)
        }
    }


    fun logout() {
        viewModelScope.launch {
            userPreferences.clearUser()
        }
    }
}