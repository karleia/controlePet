package com.example.controlepet.screens.cadastroUsuario

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.controlepet.viewModel.cadastroUsuario.RegisterUserViewModel

@Composable
fun RegisterUserScreen_step3(
    paddingValues: PaddingValues,
    navController: NavController,
    vm: RegisterUserViewModel
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize()
            .padding(paddingValues)
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Text("Passo 3: Endereço")
        }
        Spacer(modifier = Modifier.height(15.dp))

        OutlinedTextField(
            value = vm.cep,
            onValueChange = { vm.cep = it },
            modifier = Modifier
                .fillMaxWidth(),
            label = { Text(text = "Cep") },
            placeholder = { Text(text = "Cep", Modifier.alpha(0.3f)) }
        )
        OutlinedTextField(
            value = vm.address,
            onValueChange = { vm.address = it },
            modifier = Modifier
                .fillMaxWidth(),
            label = { Text(text = "Rua") },
            placeholder = { Text(text = "Rua", Modifier.alpha(0.3f)) }
        )
        OutlinedTextField(
            value = vm.neighborhood,
            onValueChange = { vm.neighborhood = it },
            modifier = Modifier
                .fillMaxWidth(),
            label = { Text(text = "Bairro") },
            placeholder = { Text(text = "Bairro", Modifier.alpha(0.3f)) }
        )
        OutlinedTextField(
            value = vm.number,
            onValueChange = { vm.number = it },
            modifier = Modifier
                .fillMaxWidth(),
            label = { Text(text = "Número") },
            placeholder = { Text(text = "Número", Modifier.alpha(0.3f)) }
        )
        OutlinedTextField(
            value = vm.complement,
            onValueChange = { vm.complement = it },
            modifier = Modifier
                .fillMaxWidth(),
            label = { Text(text = "Complemento") },
            placeholder = { Text(text = "Complemento", Modifier.alpha(0.3f)) }
        )
        OutlinedTextField(
            value = vm.city,
            onValueChange = { vm.city = it },
            modifier = Modifier
                .fillMaxWidth(),
            label = { Text(text = "Cidade") },
            placeholder = { Text(text = "Cidade", Modifier.alpha(0.3f)) }
        )
        OutlinedTextField(
            value = vm.state,
            onValueChange = { vm.state = it },
            modifier = Modifier
                .fillMaxWidth(),
            label = { Text(text = "Estado") },
            placeholder = { Text(text = "Estado", Modifier.alpha(0.3f)) }
        )

        OutlinedTextField(
            value = vm.observation_address,
            onValueChange = { vm.observation_address = it },
            modifier = Modifier
                .fillMaxWidth()
                .weight(2f),
            label = { Text(text = "Observação") },
            placeholder = { Text(text = "Observação do endereço", Modifier.alpha(0.3f)) }
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxSize()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { vm.previousStep() },
                modifier = Modifier
                    .width(140.dp)
            ) {
                Text(text = "Voltar")
            }
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