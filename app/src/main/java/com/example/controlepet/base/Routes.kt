package com.example.controlepet.base

sealed class Routes(val route: String) {
    data object UserScreen: Routes("UserScreen") {
        fun withId(userId: Int) = "UserScreen/$userId"
    }
    data object ListUserScreen: Routes("ListUserScreen")
    data object PerfilUserScreen: Routes("PerfilUserScreen")

    data object ServiceScreen: Routes("ServiceScreen") {
        fun withId(serviceId: Int) = "ServiceScreen/$serviceId"
    }
    data object ListServiceScreen: Routes("ListServiceScreen")

    data object ListClientScreen: Routes("ListClientScreen")

    data object ClientScreen: Routes("ClientScreen") {
        fun withId(clientId: Int) = "ClientScreen/$clientId"
    }
    data object PerfilClientScreen: Routes("PerfilClientScreen") {
        fun withId(clientId: Int) = "PerfilClientScreen/$clientId"
    }

    data object PetScreen: Routes("PetScreen") {
        fun withId(petId: Int?) = "PetScreen/$petId"
    }
    data object ListPetScreen: Routes("ListPetScreen")
    data object PerfilPetScreen: Routes("PerfilPetScreen") {
        fun withId(petId: Int) = "PerfilPetScreen/$petId"
    }

    data object ListAgendaScreen: Routes("ListAgendaScreen")
    data object AgendaScreen: Routes("AgendaScreen") {
        fun withId(agendaId: Int?) = "AgendaScreen/$agendaId"
    }

    data object PerfilAgendaScreen: Routes("PerfilAgendaScreen") {
        fun withId(agendaId: Int) = "PerfilAgendaScreen/$agendaId"
    }
    data object LoginScreen: Routes("LoginScreen")

    data object SplashScreen: Routes("SplashScreen")

}