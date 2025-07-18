package com.example.controlepet.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.controlepet.base.Routes
import com.example.controlepet.dataBase.AppDatabase
import com.example.controlepet.dataBase.UserPreferences
import com.example.controlepet.helpers.LoginViewModelFactory
import com.example.controlepet.repository.OfflineUserRepository
import com.example.controlepet.viewModel.login.LoginViewModel

@Composable
fun LoginScreen(paddingValues: PaddingValues, navController: NavController) {

    val context = LocalContext.current
    val db = remember { AppDatabase.getDatabase(context) }
    val repo = remember { OfflineUserRepository(db.UserDAO()) }
    val prefs = remember { UserPreferences(context) }
    val factory = remember { LoginViewModelFactory(repo, prefs) }
    val vm: LoginViewModel = viewModel(factory = factory)

    var passwordVisible by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(22.dp)
                .fillMaxHeight(0.7f)
                .align(Alignment.BottomCenter),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Login",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(bottom = 24.dp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = vm.email,
                onValueChange = { vm.email = it },
                label = { Text("E-mail") },
                leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedTextField(
                value = vm.password,
                onValueChange = { vm.password = it },
                label = { Text("Senha") },
                leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
                trailingIcon = {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        val icon =
                            if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff
                        Icon(icon, contentDescription = null)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(12.dp))
            vm.errorMessage?.let {
                Text(it, color = Color.Red)
                Spacer(modifier = Modifier.height(8.dp))
            }
            Button(
                onClick = {
                    vm.login {
                        navController.navigate("ListPetScreen")
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = vm.email.isNotBlank()
            ) {
                if (vm.isLoading)
                    CircularProgressIndicator(modifier = Modifier.size(20.dp), strokeWidth = 2.dp)
                else
                    Text("Entrar")
            }

            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Column(
                    modifier = Modifier.align(Alignment.BottomCenter), // 👉 aqui pode usar Alignment.BottomCenter
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Não tem cadastro",
                        style = MaterialTheme.typography.labelMedium.copy(
                            color = Color.Gray,
                            fontWeight = FontWeight.Bold
                        ),
                         modifier = Modifier.padding(bottom = 12.dp)
                    )
                    Button(
                        onClick = {  navController.navigate(Routes.UserScreen.withId(0)) },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Criar meu cadastro",
                            fontSize = 16.sp
                        )
                    }
                }
            }
        }
    }
}