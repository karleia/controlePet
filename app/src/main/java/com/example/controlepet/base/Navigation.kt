package com.example.controlepet.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.controlepet.dataBase.UserPreferences
import com.example.controlepet.screens.Mensagens.SuccessScreen
import com.example.controlepet.screens.splashScreen.SplashScreen

class Navigation {
    private lateinit var navController: NavHostController

    @Composable
    fun Create() {
        navController = rememberNavController()

        val context = LocalContext.current
        val navController = rememberNavController()
        val userPreferences = remember { UserPreferences(context) }

        NavHost(navController = navController, startDestination = Routes.SplashScreen.route) {
            composable(
                route = "${Routes.UserScreen.route}/{userId}",
                arguments = listOf(navArgument("userId") {
                    type = NavType.IntType
                })
            ) { backStackEntry ->
                val userId = backStackEntry.arguments?.getInt("userId") ?: 0
                CallScaffold(navController = navController)
                    .CreateScreen(screen = Routes.UserScreen.route, idEdit = userId)
            }
            composable(Routes.ListUserScreen.route) {
                CallScaffold(navController = navController)
                    .CreateScreen(screen = Routes.ListUserScreen.route)
            }
            composable(Routes.PerfilUserScreen.route) {
                CallScaffold(navController = navController).CreateScreen(screen = Routes.PerfilUserScreen.route)
            }
            composable(Routes.ListClientScreen.route) {
                CallScaffold(navController = navController)
                    .CreateScreen(screen = Routes.ListClientScreen.route)
            }
            composable(
                route = "${Routes.PerfilClientScreen.route}/{clientId}",
                arguments = listOf(navArgument("clientId") {
                    type = NavType.IntType
                })
            ) { backStackEntry ->
                val clientId = backStackEntry.arguments?.getInt("clientId") ?: 0
                CallScaffold(navController = navController)
                    .CreateScreen(screen = Routes.PerfilClientScreen.route, idEdit = clientId)
            }

            composable(
                route = "${Routes.ClientScreen.route}/{clientId}",
                arguments = listOf(navArgument("clientId") {
                    type = NavType.IntType
                })
            ) { backStackEntry ->
                val clientId = backStackEntry.arguments?.getInt("clientId") ?: 0
                CallScaffold(navController = navController)
                    .CreateScreen(screen = Routes.ClientScreen.route, idEdit = clientId)
            }

            composable(
                route = "${Routes.ServiceScreen.route}/{serviceId}",
                arguments = listOf(navArgument("serviceId") {
                    type = NavType.IntType
                })
            ) { backStackEntry ->
                val serviceId = backStackEntry.arguments?.getInt("serviceId") ?: 0
                CallScaffold(navController = navController)
                    .CreateScreen(screen = Routes.ServiceScreen.route, idEdit = serviceId)
            }

            composable(Routes.ListPetScreen.route) {
                CallScaffold(navController = navController).CreateScreen(screen = Routes.ListPetScreen.route)
            }
            composable(Routes.ListServiceScreen.route) {
                CallScaffold(navController = navController).CreateScreen(screen = Routes.ListServiceScreen.route)
            }
            composable(
                route = "${Routes.PetScreen.route}/{petId}",
                arguments = listOf(navArgument("petId") {
                    type = NavType.IntType
                })
            ) { backStackEntry ->
                val petId = backStackEntry.arguments?.getInt("petId") ?: 0
                CallScaffold(navController = navController)
                    .CreateScreen(screen = Routes.PetScreen.route, idEdit = petId)
            }
            composable(
                route = "${Routes.PerfilPetScreen.route}/{petId}",
                arguments = listOf(navArgument("petId") {
                    type = NavType.IntType
                })
            ) { backStackEntry ->
                val petId = backStackEntry.arguments?.getInt("petId") ?: 0
                CallScaffold(navController = navController)
                    .CreateScreen(screen = Routes.PerfilPetScreen.route, idEdit = petId)
            }

            composable(
                route = "${Routes.AgendaScreen.route}/{agendaId}",
                arguments = listOf(navArgument("agendaId") {
                    type = NavType.IntType
                })
            ) { backStackEntry ->
                val agendaId = backStackEntry.arguments?.getInt("agendaId") ?: 0
                CallScaffold(navController = navController)
                    .CreateScreen(screen = Routes.AgendaScreen.route, idEdit = agendaId)
            }
            composable(Routes.ListAgendaScreen.route) {
                CallScaffold(navController = navController)
                    .CreateScreen(screen = Routes.ListAgendaScreen.route)
            }
            composable(
                route = "${Routes.PerfilAgendaScreen.route}/{agendaId}",
                arguments = listOf(navArgument("agendaId") {
                    type = NavType.IntType
                })
            ) { backStackEntry ->
                val agendaId = backStackEntry.arguments?.getInt("agendaId") ?: 0
                CallScaffold(navController = navController)
                    .CreateScreen(screen = Routes.PerfilAgendaScreen.route, idEdit = agendaId)
            }
            composable(Routes.LoginScreen.route) {
                CallScaffold(navController = navController).CreateScreen(screen = Routes.LoginScreen.route)
            }
            composable("SplashScreen") {
                SplashScreen(navController, userPreferences)
            }
            composable(
                    route = "success/{message}/{nextRoute}/{popUpToRoute}",
                    arguments = listOf(
                        navArgument("message") { type = NavType.StringType },
                        navArgument("nextRoute") { type = NavType.StringType },
                        navArgument("popUpToRoute") { type = NavType.StringType }
                    )
                ) {
                    backStackEntry ->
                    val message = backStackEntry.arguments?.getString("message") ?: ""
                    val nextRoute = backStackEntry.arguments?.getString("nextRoute") ?: ""
                    val popUpToRoute = backStackEntry.arguments?.getString("popUpToRoute") ?: ""

                    SuccessScreen(
                        message = message,
                        onContinue = {
                            navController.navigate(nextRoute) {
                                popUpTo(popUpToRoute) { inclusive = true }
                            }
                        }
                    )
                }
            }
        }
}