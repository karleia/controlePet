package com.example.controlepet.screens.client

import android.net.Uri
import android.widget.Toast
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.controlepet.base.Routes

@Composable
fun ClientScreen_step2(
    paddingValues: PaddingValues,
    navController: NavController,
    vm: ClientViewModel
) {

    val context = LocalContext.current
    val isSuccess by remember { derivedStateOf { vm.isSuccess } }

    LaunchedEffect(isSuccess) {
        if (isSuccess) {

            Toast.makeText(context, "Dados salvos com sucesso", Toast.LENGTH_LONG).show()
            navController.navigate(Routes.ListClientScreen.route)
           /* if(vm.tipoCadastro === "cadastrar") {
                navController.navigate("success/${Uri.encode("Cliente cadastrado com sucesso!")}/ListClientScreen/ClientScreen_step2")
            } else {
                navController.navigate("success/${Uri.encode("Cliente editado com sucesso!")}/ListClientScreen/ClientScreen_step2")
            }
            vm.resetState()*/
        }
    }
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
            Text("Passo 2: Endereço")
        }
        Spacer(modifier = Modifier.height(16.dp))

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
            value = vm.observationAddress,
            onValueChange = { vm.observationAddress = it },
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
                onClick = {
                    vm.previousStep()
                },
                modifier = Modifier
                    .width(140.dp)
            ) {
                Text(text = "Voltar")
            }
            Button(
                onClick = {  vm.saveClient() },
                modifier = Modifier
                    .width(140.dp)
            ) {
                Text(text = "Salvar")
            }
        }
    }
}