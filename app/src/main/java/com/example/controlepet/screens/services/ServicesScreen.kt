package com.example.controlepet.screens.services

import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.controlepet.base.Routes
import com.example.controlepet.dataBase.AppDatabase
import com.example.controlepet.helpers.provideViewModel
import com.example.controlepet.helpers.service.ServiceViewModelFactory
import com.example.controlepet.repository.OfflineServiceRepository

@Composable
fun ServicesScreen(
    paddingValues: PaddingValues,
    navController: NavController,
    serviceIdToEdit: Int = 0
) {

    val context = LocalContext.current
    val db = remember { AppDatabase.getDatabase(context) }
    val repo = remember { OfflineServiceRepository(db.ServiceDAO()) }
    val factory = remember { ServiceViewModelFactory(repo, serviceIdToEdit) }
    val vm: ServiceViewModel = provideViewModel(factory)

    val isSuccess by remember { derivedStateOf { vm.isSuccess } }

    LaunchedEffect(serviceIdToEdit) {
        if (serviceIdToEdit != 0) {
            vm.loadServiceForEdit(serviceIdToEdit)
        }
    }

    LaunchedEffect(isSuccess) {
        if (isSuccess) {
            Toast.makeText(context, "Dados salvos com sucesso", Toast.LENGTH_LONG).show()
            navController.navigate(Routes.ListServiceScreen.route)
        }
    }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .fillMaxHeight()
            .padding(paddingValues)
            .verticalScroll(rememberScrollState())
            .padding(15.dp)
    ) {

        OutlinedTextField(
            value = vm.name,
            onValueChange = { vm.name = it },
            modifier = Modifier
                .fillMaxWidth(),
            label = { Text(text = "Nome do serviço") },
            placeholder = { Text(text = "Nome do serviço", Modifier.alpha(0.3f)) }
        )

        OutlinedTextField(
            value = vm.price.toString(),
            onValueChange = {
                val newValue = it.replace(",", ".")
                vm.price = newValue.toDoubleOrNull() ?: 0.0
            },
            modifier = Modifier
                .fillMaxWidth(),
            label = { Text(text = "Preço") },
            placeholder = { Text(text = "Preço do serviço", Modifier.alpha(0.3f)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = { vm.saveService() },
                modifier = Modifier
                    .width(140.dp)
            ) {
                Text(text = "Salvar")
            }
        }
    }
}