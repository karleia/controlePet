package com.example.controlepet.screens.pets

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
import androidx.compose.runtime.LaunchedEffect
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
import com.example.controlepet.helpers.pet.PetViewModelFactory
import com.example.controlepet.helpers.provideViewModel
import com.example.controlepet.repository.OfflineClientRepository
import com.example.controlepet.repository.OfflinePetRepository
import com.example.controlepet.viewModel.pets.PerfilPetViewModel

@Composable
fun PerfilPetScreen(
    paddingValues: PaddingValues,
    navController: NavController,
    petId: Int
) {

    val context = LocalContext.current
    val db = remember { AppDatabase.getDatabase(context) }
    val repo = remember { OfflinePetRepository(db.PetDAO()) }
    val repoClient = remember { OfflineClientRepository(db.ClientDAO()) }
    val factory = remember { PetViewModelFactory(repo, repoClient, petId) }

    val vm: PerfilPetViewModel = provideViewModel(factory)

    val pet by vm.petWithClientName.collectAsState()

    LaunchedEffect(petId) {
        vm.loadPetWithClientName(petId)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {

        if (pet != null) {
            Column(
                modifier = Modifier
                    .weight(5f)
                    .fillMaxSize()
                    .fillMaxHeight()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
            ) {
                DividerLabel("Pet")
                LabelWithValue("Nome", pet?.pet?.name?: "")
                LabelWithValue("Raça", pet?.pet?.breed ?: "")
                LabelWithValue("Pelagem", pet?.pet?.pelagem ?: "")
                LabelWithValue("Cor", pet?.pet?.color ?: "")
                LabelWithValue("Cliente", pet?.clientName ?: "")

                DividerLabel("Observações sobre o pet")
                LabelWithValue("Observação sobre o pet", pet?.pet?.observation ?: "")
            }

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .weight(1f),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = { navController.navigate(Routes.ListPetScreen.route) },
                    modifier = Modifier
                        .width(140.dp)
                ) {
                    Text("Voltar")
                }
                Button(
                    onClick = { navController.navigate(Routes.PetScreen.withId(pet?.pet?.id)) },
                    modifier = Modifier
                        .width(140.dp)
                ) {
                    Text(text = "Editar")
                }
            }
        } else {
            Text(text = "Carregando...")
        }

    }
}