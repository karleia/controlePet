package com.example.controlepet.screens.splashScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.controlepet.dataBase.AppDatabase
import com.example.controlepet.dataBase.UserPreferences
import com.example.controlepet.helpers.user.UserViewModelFactory
import com.example.controlepet.repository.OfflineUserRepository
import com.example.controlepet.viewModel.cadastroUsuario.PerfilUserViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.firstOrNull

@Composable
fun SplashScreen(navController: NavController, userPrefs: UserPreferences) {

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val userId = remember { mutableStateOf<Int?>(null) }

    val db = remember { AppDatabase.getDatabase(context) }
    val repo = remember { OfflineUserRepository(db.UserDAO()) }
    val prefs = remember { UserPreferences(context) }
    val factory = remember { UserViewModelFactory(repo, prefs) }
    val viewModel: PerfilUserViewModel = viewModel(factory = factory)

    LaunchedEffect(true) {
        userPrefs.loggedInUserId.firstOrNull()?.let {
            userId.value = it
        }
    }

    LaunchedEffect(userId.value) {

        userId.value?.let { id ->
            delay(3000)
            viewModel.checkIfUserExists(id) { exists ->
                if (exists) {
                    navController.navigate("AgendaScreen")
                } else {
                    viewModel.logout()
                    navController.navigate("LoginScreen")
                }
            }
        } ?: run {
            navController.navigate("LoginScreen")
        }

    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }

}
