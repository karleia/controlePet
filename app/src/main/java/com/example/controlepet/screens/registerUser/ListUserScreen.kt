package com.example.controlepet.screens.registerUser

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.controlepet.base.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListUserScreen(paddingValues: PaddingValues,
                   navController: NavController,
                   viewModel: ListUserViewModel
) {

    val users by viewModel.userList.collectAsState()

    LazyColumn(
        modifier = Modifier.fillMaxSize()
                .padding(paddingValues)
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        items(items = users, key = { it.id }) { user ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Nome: ${user.name}", style = MaterialTheme.typography.titleMedium)
                    Text("E-mail: ${user.email}", style = MaterialTheme.typography.bodyMedium)
                    Text("Endereço: ${user.address}", style = MaterialTheme.typography.bodySmall)
                    Text("Senha: ${user.password}", style = MaterialTheme.typography.bodySmall)
                }
            }
        }
    }

}