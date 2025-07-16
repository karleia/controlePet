package com.example.controlepet.base

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Person2
import androidx.compose.material.icons.filled.Person3
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.filled.RoomService
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route: String, val label: String, val icon: ImageVector) {
    object Agenda : BottomNavItem(Routes.ListAgendaScreen.route, "Agenda", Icons.Default.Check)
    object Client : BottomNavItem(Routes.ListClientScreen.route, "Cliente", Icons.Default.Person3)
    object Pets : BottomNavItem(Routes.ListPetScreen.route, "Pets", Icons.Default.Pets)
    object Service : BottomNavItem(Routes.ListServiceScreen.route, "Serviços", Icons.Default.RoomService)
    object Profile : BottomNavItem(Routes.PerfilUserScreen.route, "Perfil", Icons.Default.Person)

    companion object {
        val items = listOf(Agenda, Client, Pets, Service, Profile)
    }
}