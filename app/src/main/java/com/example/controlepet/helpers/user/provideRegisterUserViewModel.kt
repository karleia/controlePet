package com.example.controlepet.helpers.user

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.controlepet.dataBase.AppDatabase
import com.example.controlepet.repository.OfflineUserRepository
import com.example.controlepet.viewModel.cadastroUsuario.RegisterUserViewModel

@Composable
fun provideRegisterUserViewModel(): RegisterUserViewModel {
    val context = LocalContext.current
    val db = AppDatabase.getDatabase(context)
    val factory = RegisterUserViewModelFactory(OfflineUserRepository(db.UserDAO()))
    return viewModel(factory = factory)
}