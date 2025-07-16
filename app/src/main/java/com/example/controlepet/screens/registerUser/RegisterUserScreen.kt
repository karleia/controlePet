package com.example.controlepet.screens.registerUser

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun RegisterUserScreen(
    paddingValues: PaddingValues,
    navController: NavController,
    vm: RegisterUserViewModel
) {

    val opcoesTipo = listOf("F", "J")
    var typePeople by remember { mutableStateOf(opcoesTipo[0]) }

    var document by remember { mutableStateOf("CPF") }

     Column (
         modifier = Modifier
             .fillMaxSize()
             .fillMaxHeight()
             .padding(paddingValues)
             .verticalScroll(rememberScrollState())
             .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Text("Passo 1: Dados pessoais")
        }
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = vm.name,
            onValueChange = { vm.name = it },
            modifier = Modifier
                .fillMaxWidth(),
            label = { Text(text = "Nome") },
            placeholder = { Text(text = "Nome completo", Modifier.alpha(0.3f)) }
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text("Tipo Pessoa:", style = MaterialTheme.typography.titleMedium)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(vertical = 4.dp)
        ) {
            opcoesTipo.forEach { tipo ->
                RadioButton(
                    selected = (if(vm.typePeople.isEmpty()) tipo == typePeople else tipo == vm.typePeople),
                    onClick = {
                        typePeople = tipo;
                        vm.typePeople = tipo;
                        document = if (tipo == "F") "CPF" else "CNPJ"
                    }
                )
                Text(text = tipo)
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(
            value = vm.document,
            onValueChange = { vm.document = it },
            modifier = Modifier
                .fillMaxWidth(),
            label = { Text(text = document) },
            placeholder = { Text(text = document, Modifier.alpha(0.3f)) }
        )
         Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(
            value = vm.email,
            onValueChange = { vm.email = it },
            modifier = Modifier
                .fillMaxWidth(),
            label = { Text(text = "E-mail") },
            placeholder = { Text(text = "E-mail", Modifier.alpha(0.3f)) }
        )
         Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(
            value = vm.cellPhone2,
            onValueChange = { vm.cellPhone2 = it },
            modifier = Modifier
                .fillMaxWidth(),
            label = { Text(text = "Telefone") },
            placeholder = { Text(text = "Telefone", Modifier.alpha(0.3f)) }
        )
         Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(
            value = vm.cellPhone,
            onValueChange = { vm.cellPhone = it },
            modifier = Modifier
                .fillMaxWidth(),
            label = { Text(text = "Celular") },
            placeholder = { Text(text = "Celular", Modifier.alpha(0.3f)) }
        )
         Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(
            value = vm.description,
            onValueChange = { vm.description = it },
            modifier = Modifier
                .fillMaxWidth()
                .weight(5f),
            label = { Text(text = "Descrição da empresa") },
            placeholder = { Text(text = "Digite a descrição da sua empresa", Modifier.alpha(0.3f)) }
        )

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
             onClick = {
                  vm.nextStep()
                },
                modifier = Modifier
                    .width(140.dp)
            ) {
                Text(text = "Seguir")
            }
        }
    }
}
