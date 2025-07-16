package com.example.controlepet.screens.client

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.controlepet.base.Routes
import com.example.controlepet.components.DividerLabel
import com.example.controlepet.components.LabelWithValue
import com.example.controlepet.dataBase.AppDatabase
import com.example.controlepet.helpers.client.ClientViewModelFactory
import com.example.controlepet.helpers.provideViewModel
import com.example.controlepet.repository.OfflineClientRepository

@Composable
fun PerfilClientScreen(
    paddingValues: PaddingValues,
    navController: NavController,
    clientId: Int
) {

    val context = LocalContext.current
    val db = remember { AppDatabase.getDatabase(context) }
    val repo = remember { OfflineClientRepository(db.ClientDAO()) }
    val factory = remember { ClientViewModelFactory(repo, clientId) }

    val vm: PerfilClientViewModel = provideViewModel(factory)
    val client by vm.client.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {

        Column(
            modifier = Modifier
                .weight(5f)
                .fillMaxSize()
                .fillMaxHeight()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ){
            DividerLabel("Cliente")
            LabelWithValue("Nome", client?.name ?: "")
            LabelWithValue("Email", client?.email ?: "")
            LabelWithValue("CPF", client?.document ?: "")

            DividerLabel("Endereço")
            LabelWithValue("Cep", client?.cep ?: "")
            LabelWithValue("Address", client?.address ?: "")
            LabelWithValue("Bairro", client?.neighborhood ?: "")
            LabelWithValue("Número", client?.number ?: "")
            LabelWithValue("Complemento", client?.complement ?: "")
            LabelWithValue("Cidade", client?.city ?: "")
            LabelWithValue("Estado", client?.state ?: "")
            LabelWithValue("Observação do endereço", client?.observationAddress ?: "")

            DividerLabel("Observações sobre o cliente")
            LabelWithValue("Observação sobre o cliente", client?.observation ?: "")
        }

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .weight(1f),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {navController.navigate(Routes.ListClientScreen.route) },
                modifier = Modifier
                    .width(140.dp)
            ) {
                Text("Voltar")
            }
            Button(
                onClick = {  navController.navigate(Routes.ClientScreen.withId(clientId)) },
                modifier = Modifier
                    .width(140.dp)
            ) {
                Text(text = "Editar")
            }
        }
    }

}