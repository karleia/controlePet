package com.example.controlepet.screens.cadastroUsuario

import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.controlepet.components.PasswordFields
import com.example.controlepet.viewModel.cadastroUsuario.RegisterUserViewModel

@Composable
fun RegisterUserScreen_step4(
    paddingValues: PaddingValues,
    navController: NavController,
    vm: RegisterUserViewModel
) {

    var password by remember { mutableStateOf("") }
    var repeatPassword by remember { mutableStateOf("") }

    val isSuccess by remember { derivedStateOf { vm.isSuccess } }

    LaunchedEffect(isSuccess) {
        if (isSuccess) {
             if(vm.tipoCadastro === "editar") {
                 navController.navigate("success/${Uri.encode("Usuário editado com sucesso!")}/PerfilUserScreen/RegisterUserScreen_step4")
             } else {
                 navController.navigate("success/${Uri.encode("Usuário cadastrado com sucesso!")}/LoginScreen/RegisterUserScreen_step4")
             }
            vm.resetState()
         }
        //navController.navigate("success/${Uri.encode("Usuário cadastrado com sucesso!")}/LoginScreen/RegisterUserScreen_step4")
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(paddingValues)
            .padding(16.dp)
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Text("Passo 4: Dados de acesso")
        }
        Spacer(modifier = Modifier.height(15.dp))

        OutlinedTextField(
            value = vm.email,
            onValueChange = { vm.email = it },
            modifier = Modifier
                .fillMaxWidth(),
            label = { Text(text = "E-mail") },
            leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) },
            placeholder = { Text(text = "E-mail", Modifier.alpha(0.3f)) }
        )

        PasswordFields(
            password = vm.password,
            repeatPassword = repeatPassword,
            onPasswordChange = { vm.password = it },
            onRepeatPasswordChange = { repeatPassword = it },
            repeat = true
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .weight(1f),
            contentAlignment = Alignment.BottomCenter
        ) {
            Button(
                onClick = { vm.previousStep() },
                modifier = Modifier.width(140.dp)
            ) {
                Text(text = "Voltar")
            }
            Button(
                onClick = {
                    vm.saveUser()
                },
                modifier = Modifier.width(140.dp)
            ) {
                Text(text = "Salvar")
            }
        }
    }
}