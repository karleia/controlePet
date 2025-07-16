package com.example.controlepet.screens.client


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
import com.example.controlepet.screens.registerUser.RegisterUserViewModel

@Composable
fun ClientScreen_step1(
    paddingValues: PaddingValues,
    navController: NavController,
    vm: ClientViewModel
) {
    var numberWhatsapp by remember { mutableStateOf(1) }

    //val whatsappNumber = if (whatsappOption == 1) cellPhone else cellPhone2
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
            Text("Passo 1: Dados do cliente")
        }
        Spacer(modifier = Modifier.height(15.dp))

        OutlinedTextField(
            value = vm.name,
            onValueChange = { vm.name = it },
            modifier = Modifier
                .fillMaxWidth(),
            label = { Text(text = "Nome") },
            placeholder = { Text(text = "Nome completo", Modifier.alpha(0.3f)) }
        )
         OutlinedTextField(
            value = vm.document,
            onValueChange = { vm.document = it },
            modifier = Modifier
                .fillMaxWidth(),
            label = { Text(text = "CPF") },
            placeholder = { Text(text = "CPF", Modifier.alpha(0.3f)) }
        )

        OutlinedTextField(
            value = vm.email,
            onValueChange = { vm.email = it },
            modifier = Modifier
                .fillMaxWidth(),
            label = { Text(text = "E-mail") },
            placeholder = { Text(text = "E-mail", Modifier.alpha(0.3f)) }
        )


        Row(verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(
                value = vm.cellPhone,
                onValueChange = { vm.cellPhone = it },
                modifier = Modifier.weight(1f),
                label = { Text("Celular") },
                placeholder = { Text("Celular", Modifier.alpha(0.3f)) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                RadioButton(
                    selected = numberWhatsapp == 1,
                    onClick = { numberWhatsapp = 1; vm.numberWhatsapp = "1" }
                )
                Text("WhatsApp", style = MaterialTheme.typography.labelSmall)
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(
                value = vm.cellPhone2,
                onValueChange = { vm.cellPhone2 = it },
                modifier = Modifier.weight(1f),
                label = { Text("Celular 2") },
                placeholder = { Text("Celular 2", Modifier.alpha(0.3f)) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                RadioButton(
                    selected = numberWhatsapp == 2,
                    onClick = { numberWhatsapp = 2; vm.numberWhatsapp = "2" }
                )
                Text("WhatsApp", style = MaterialTheme.typography.labelSmall)
            }
        }

        OutlinedTextField(
            value = vm.observation,
            onValueChange = { vm.observation = it },
            modifier = Modifier
                .fillMaxWidth()
                .weight(5f),
            label = { Text(text = "Observações do cliente ") },
            placeholder = { Text(text = "Informe observações do cliente", Modifier.alpha(0.3f)) }
        )

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {  vm.nextStep() },
                modifier = Modifier
                    .width(140.dp)
            ) {
                Text(text = "Seguir")
            }
        }
    }
}