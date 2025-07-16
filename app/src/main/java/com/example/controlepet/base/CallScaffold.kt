package com.example.controlepet.base

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.controlepet.dataBase.AppDatabase
import com.example.controlepet.dataBase.UserPreferences
import com.example.controlepet.helpers.agenda.AgendaViewModelFactory
import com.example.controlepet.helpers.client.ListClientViewModelFactory
import com.example.controlepet.helpers.pet.PetViewModelFactory
import com.example.controlepet.helpers.service.ServiceViewModelFactory
import com.example.controlepet.helpers.user.ListUserViewModelFactory
import com.example.controlepet.helpers.user.UserViewModelFactory
import com.example.controlepet.repository.OfflineAgendaRepository
import com.example.controlepet.repository.OfflineClientRepository
import com.example.controlepet.repository.OfflinePetRepository
import com.example.controlepet.repository.OfflineServiceRepository
import com.example.controlepet.repository.OfflineUserRepository
import com.example.controlepet.screens.agenda.AgendaScreen
import com.example.controlepet.screens.agenda.ListAgendaScreen
import com.example.controlepet.screens.agenda.ListAgendaViewModel
import com.example.controlepet.screens.client.ClientScreen
import com.example.controlepet.screens.client.ListClientScreen
import com.example.controlepet.screens.client.ListClientViewModel
import com.example.controlepet.screens.client.PerfilClientScreen
import com.example.controlepet.screens.login.LoginScreen
import com.example.controlepet.screens.pets.ListPetScreen
import com.example.controlepet.screens.pets.ListPetViewModel
import com.example.controlepet.screens.pets.PerfilPetScreen
import com.example.controlepet.screens.pets.PetScreen
import com.example.controlepet.screens.registerUser.ListUserScreen
import com.example.controlepet.screens.registerUser.ListUserViewModel
import com.example.controlepet.screens.registerUser.PerfilUserScreen
import com.example.controlepet.screens.registerUser.PerfilUserViewModel
import com.example.controlepet.screens.registerUser.UserScreen
import com.example.controlepet.screens.services.ListServiceScreen
import com.example.controlepet.screens.services.ListServiceViewModel
import com.example.controlepet.screens.services.ServicesScreen
import com.example.controlepet.screens.agenda.PerfilAgendaScreen


class CallScaffold(val navController: NavController) {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun CreateScreen (screen: String, idEdit: Int? = null) {

        val showBottomBar = screen in BottomNavItem.items.map { it.route }

        Scaffold(
            topBar = {
                when (screen) {
                    Routes.UserScreen.route -> {
                        CenterAlignedTopAppBar(
                            title = { Text(text = "Cadastro de Usuário") },
                        )
                    }
                    Routes.ListUserScreen.route -> {
                        CenterAlignedTopAppBar(
                            title = { Text(text = "Lista de usuários") },
                        )
                    }
                    Routes.PerfilUserScreen.route -> {
                        CenterAlignedTopAppBar(
                            title = { Text(text = "Meus Dados") },
                        )
                    }
                    Routes.ListClientScreen.route -> {
                        CenterAlignedTopAppBar(
                            title = { Text(text = "Lista de clientes") },
                        )
                    }
                    Routes.PerfilClientScreen.route -> {
                        CenterAlignedTopAppBar(
                            title = { Text(text = "Dados do cliente") },
                        )
                    }
                    Routes.ClientScreen.route -> {
                        CenterAlignedTopAppBar(
                            title = { Text(text = "Cadastro de Cliente") },
                        )
                    }
                    Routes.ServiceScreen.route -> {
                        CenterAlignedTopAppBar(
                            title = { Text(text = "Cadastro de Serviço") },
                        )
                    }
                    Routes.ListServiceScreen.route -> {
                        CenterAlignedTopAppBar(
                            title = { Text(text = "Listagem de Serviço") },
                        )
                    }
                    Routes.ListPetScreen.route -> {
                        CenterAlignedTopAppBar(
                            title = { Text(text = "Listagem de pets") },
                        )
                    }
                    Routes.PetScreen.route -> {
                        CenterAlignedTopAppBar(
                            title = { Text(text = "Cadastro de Pet") },
                        )
                    }
                    Routes.PerfilPetScreen.route -> {
                        CenterAlignedTopAppBar(
                            title = { Text(text = "Dados do Pet") },
                        )
                    }
                    Routes.ListAgendaScreen.route -> {
                        CenterAlignedTopAppBar(
                            title = { Text(text = "Listagem de agenda") },
                        )
                    }
                    Routes.AgendaScreen.route -> {
                        CenterAlignedTopAppBar(
                            title = { Text(text = "Agendamento de serviço") },
                        )
                    }
                    Routes.PerfilAgendaScreen.route -> {
                        CenterAlignedTopAppBar(
                            title = { Text(text = "Dados do agendamento") },
                        )
                    }
                }
            },
            bottomBar = {
                if (showBottomBar) {
                    BottomNavigationBar(navController)
                }
            }
        ) { padding ->
            when (screen) {
                Routes.UserScreen.route -> {
                    UserScreen(padding, navController, userIdToEdit = idEdit ?: 0)
                }
                Routes.ListUserScreen.route -> {
                    val context = LocalContext.current
                    val db = remember { AppDatabase.getDatabase(context) }
                    val repo = remember { OfflineUserRepository(db.UserDAO()) }
                    val factory = remember { ListUserViewModelFactory(repo) }
                    val viewModel: ListUserViewModel = viewModel(factory = factory)
                    ListUserScreen(padding, navController, viewModel)
                }
                Routes.PerfilUserScreen.route -> {
                    val context = LocalContext.current
                    val db = remember { AppDatabase.getDatabase(context) }
                    val repo = remember { OfflineUserRepository(db.UserDAO()) }
                    val prefs = remember { UserPreferences(context) }
                    val factory = remember { UserViewModelFactory(repo, prefs) }
                    val viewModel: PerfilUserViewModel = viewModel(factory = factory)
                    PerfilUserScreen(padding, navController, viewModel)
                }

                Routes.ListClientScreen.route -> {
                    val context = LocalContext.current
                    val db = remember { AppDatabase.getDatabase(context) }
                    val repo = remember { OfflineClientRepository(db.ClientDAO()) }
                    val factory = remember { ListClientViewModelFactory(repo) }
                    val viewModel: ListClientViewModel = viewModel(factory = factory)
                    ListClientScreen(padding, navController, viewModel)
                }
                Routes.PerfilClientScreen.route -> {
                    PerfilClientScreen(padding, navController, clientId = idEdit ?: 0)
                }
                Routes.ClientScreen.route -> {
                    ClientScreen(padding, navController, clientIdToEdit = idEdit ?: 0)
                }
                Routes.ServiceScreen.route -> {
                    ServicesScreen(padding, navController, serviceIdToEdit = idEdit ?: 0)
                }
                Routes.ListServiceScreen.route -> {
                    val context = LocalContext.current
                    val db = remember { AppDatabase.getDatabase(context) }
                    val repo = remember { OfflineServiceRepository(db.ServiceDAO()) }
                    val factory = remember { ServiceViewModelFactory(repo, 0) }
                    val viewModel: ListServiceViewModel = viewModel(factory = factory)
                    ListServiceScreen(padding, navController = navController, viewModel )
                }

                Routes.ListPetScreen.route -> {
                    val context = LocalContext.current
                    val db = remember { AppDatabase.getDatabase(context) }
                    val repo = remember { OfflinePetRepository(db.PetDAO()) }
                    val repoClient = remember { OfflineClientRepository(db.ClientDAO()) }
                    val factory = remember { PetViewModelFactory(repo, repoClient, 0) }
                    val viewModel: ListPetViewModel = viewModel(factory = factory)
                    ListPetScreen(padding, navController, viewModel)
                }
                Routes.PetScreen.route -> {
                    PetScreen(padding, navController, petIdToEdit = idEdit ?: 0)
                }
                Routes.PerfilPetScreen.route -> {
                    PerfilPetScreen(padding, navController, petId = idEdit ?: 0)
                }

                Routes.AgendaScreen.route -> {
                    AgendaScreen(padding, navController, agendaIdToEdit = idEdit ?: 0)
                }
                Routes.PerfilAgendaScreen.route -> {
                    PerfilAgendaScreen(padding, navController, agendaId = idEdit ?: 0)
                }
                Routes.ListAgendaScreen.route -> {
                    val context = LocalContext.current
                    val db = remember { AppDatabase.getDatabase(context) }
                    val repo = remember { OfflineAgendaRepository(db.AgendaDAO()) }
                    val factory = remember { AgendaViewModelFactory(repo, 0) }
                    val viewModel: ListAgendaViewModel = viewModel(factory = factory)
                    ListAgendaScreen(padding, navController = navController, viewModel )
                }
                Routes.LoginScreen.route -> LoginScreen(padding, navController = navController )
            }
        }
    }
}
