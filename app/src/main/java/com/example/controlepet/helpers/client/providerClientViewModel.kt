package com.example.controlepet.helpers.client

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.controlepet.dataBase.AppDatabase
import com.example.controlepet.repository.OfflineClientRepository
import com.example.controlepet.viewModel.cliente.ClientViewModel

@Composable
fun provideClientViewModel(clientId: Int = 0): ClientViewModel {
    val context = LocalContext.current
    val db = AppDatabase.getDatabase(context)
    val factory = ClientViewModelFactory(OfflineClientRepository(db.ClientDAO()), clientId)
    return viewModel(factory = factory)
}