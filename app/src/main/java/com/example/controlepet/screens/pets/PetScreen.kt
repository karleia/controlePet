package com.example.controlepet.screens.pets

import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.controlepet.base.Routes
import com.example.controlepet.components.DropDownFields
import com.example.controlepet.components.SearchableDropdown
import com.example.controlepet.dataBase.AppDatabase
import com.example.controlepet.helpers.pet.PetViewModelFactory
import com.example.controlepet.helpers.provideViewModel
import com.example.controlepet.repository.OfflineClientRepository
import com.example.controlepet.repository.OfflinePetRepository

@Composable
fun PetScreen(
    paddingValues: PaddingValues,
    navController: NavController,
    petIdToEdit: Int = 0
) {

    val context = LocalContext.current
    val db = remember { AppDatabase.getDatabase(context) }
    val repo = remember { OfflinePetRepository(db.PetDAO()) }
    val repoClient = remember { OfflineClientRepository(db.ClientDAO()) }
    val factory = remember { PetViewModelFactory(repo, repoClient, petIdToEdit) }
    val vm: PetViewModel = provideViewModel(factory)

    //selecionar os clientes
    val clients by vm.clientList.collectAsState()
    val clientMap = remember(clients) { clients.associateBy({ it.name }, { it.id }) }
    var clientSelected by rememberSaveable{ mutableStateOf("") }
    var clientIdSelected by rememberSaveable { mutableStateOf(0) }

    val isSuccess by remember { derivedStateOf { vm.isSuccess } }

    var name by remember { mutableStateOf("") }

    var typePetSelected by remember { mutableStateOf(vm.typePet) }
    var sexSelected by remember { mutableStateOf(vm.sex) }

    LaunchedEffect(petIdToEdit) {
        if (petIdToEdit != 0) {
            vm.loadPetForEdit(petIdToEdit)
        }
    }

    val idToNameMap = remember(clients) { clients.associateBy({ it.id }, { it.name }) }
    val nameToIdMap = remember(clients) { clients.associateBy({ it.name }, { it.id }) }
    LaunchedEffect(vm.clientId, clients) {
        if (vm.clientId != 0 && clients.isNotEmpty()) {
            idToNameMap[vm.clientId]?.let { name ->
                clientSelected = name
                clientIdSelected = vm.clientId
            }
        }
    }

    //se o cadastro for bem sucedido mostrar uma mensagem de sucesso para o usuario
    //e retorna para a tela de lista de pets
    LaunchedEffect(isSuccess) {
        if (isSuccess) {
            Toast.makeText(context, "Dados salvos com sucesso", Toast.LENGTH_LONG).show()
            navController.navigate(Routes.ListPetScreen.route)
        }
    }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .fillMaxHeight()
            .padding(paddingValues)
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {

        SearchableDropdown(
            label = "Cliente",
            options = clients.map { it.name },
            selectedOption = clientSelected,
            onOptionSelected = { name ->
                if(vm.editingId == null) {
                    clientSelected = name
                    nameToIdMap[name]?.let { id ->
                        vm.setClientIdSelected(id)
                    }
                }
            },
            enabled = vm.editingId == null
        )

        OutlinedTextField(
            value = vm.name,
            onValueChange = { vm.name = it },
            modifier = Modifier
                .fillMaxWidth(),
            label = { Text(text = "Nome do Pet") },
            placeholder = { Text(text = "Nome do pet", Modifier.alpha(0.3f)) }
        )

        Spacer(modifier = Modifier.height(12.dp))

        DropDownFields(
            label = "Tipo",
            options =listOf("Cachorro", "Gato"),
            selectedOption =  vm.typePet,
            onOptionSelected = { selected ->
                typePetSelected = selected
                vm.typePet = selected
            }
        )

        OutlinedTextField(
            value = vm.breed,
            onValueChange = { vm.breed = it },
            modifier = Modifier
                .fillMaxWidth(),
            label = { Text(text = "Raça") },
            placeholder = { Text(text = "Raça", Modifier.alpha(0.3f)) }
        )
        OutlinedTextField(
            value = vm.color,
            onValueChange = { vm.color = it },
            modifier = Modifier
                .fillMaxWidth(),
            label = { Text(text = "Cor") },
            placeholder = { Text(text = "Cor", Modifier.alpha(0.3f)) }
        )
        OutlinedTextField(
            value = vm.pelagem,
            onValueChange = { vm.pelagem = it },
            modifier = Modifier
                .fillMaxWidth(),
            label = { Text(text = "Pelagem") },
            placeholder = { Text(text = "Pelagem", Modifier.alpha(0.3f)) }
        )

        DropDownFields(
            label = "Sexo",
            options = listOf("Fêmea", "Macho"),
            selectedOption =  vm.sex,
            onOptionSelected = { selected ->
                sexSelected = selected
                vm.sex = selected
            }
        )

        OutlinedTextField(
            value = vm.observation,
            onValueChange = { vm.observation = it },
            modifier = Modifier
                .fillMaxWidth()
                .weight(10f),
            label = { Text(text = "Observações sobre o pet") },
            placeholder = { Text(text = "Informe as observações sobre o pet", Modifier.alpha(0.3f)) }
        )

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    vm.savePet()
                },
                modifier = Modifier
                    .width(140.dp)
            ) {
                Text(text = "Salvar")
            }
        }
    }
}