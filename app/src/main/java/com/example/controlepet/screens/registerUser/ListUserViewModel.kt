package com.example.controlepet.screens.registerUser

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.controlepet.model.User
import com.example.controlepet.repository.OfflineUserRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class ListUserViewModel(
    private val repo: OfflineUserRepository
): ViewModel() {
    val userList: StateFlow<List<User>> = repo.getAllUsers()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
}