package com.example.controlepet.screens.cadastroUsuario

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import com.example.controlepet.components.LabelWithValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.controlepet.base.Routes
import com.example.controlepet.viewModel.cadastroUsuario.PerfilUserViewModel

@Composable
fun PerfilUserScreen(
    paddingValues: PaddingValues,
    navController: NavController,
    vm: PerfilUserViewModel
) {
    val idUser by vm.id.collectAsState()
    val name by vm.name.collectAsState()
    val email by vm.email.collectAsState()
    val typePeople by vm.typePeople.collectAsState()
    val document by vm.document.collectAsState()
    val description by vm.description.collectAsState()
    val cellPhone by vm.cellPhone.collectAsState()
    val cellPhone2 by vm.cellPhone2.collectAsState()
    val cep by vm.cep.collectAsState()
    val address by vm.address.collectAsState()
    val neighborhood by vm.neighborhood.collectAsState()
    val complement by vm.complement.collectAsState()
    val number by vm.number.collectAsState()
    val city by vm.city.collectAsState()
    val state by vm.state.collectAsState()
    val observationAddress by vm.observationAddress.collectAsState()
    val password by vm.password.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        Column(
            modifier = Modifier
                .weight(5f)
                .fillMaxSize()
                .fillMaxHeight()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            LabelWithValue("Nome:", name)
            LabelWithValue("Email:", email)
            LabelWithValue("Tipo:", if (typePeople == "F") "Física" else "Jurídica")
            LabelWithValue(if (typePeople == "F") "CPF" else "CNPJ", document)
            LabelWithValue("Telefone:", cellPhone)
            LabelWithValue("Celular:", cellPhone2)
            LabelWithValue("Cep:", cep)
            LabelWithValue("Rua:", address)
            LabelWithValue("Bairro:", neighborhood)
            LabelWithValue("Número:", number)
            LabelWithValue("Cidade:", city)
            LabelWithValue("Estado:", state)
            LabelWithValue("Complemento:", complement)
            LabelWithValue("Observação do endereço:", observationAddress)
            LabelWithValue("Descrição", description)
            LabelWithValue("Password", password)
        }

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .weight(1f),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { },
                modifier = Modifier
                    .width(140.dp)
            ) {
                Text(text = "Home")
            }
            Button(
                onClick = {  navController.navigate(Routes.UserScreen.withId(idUser)) },
                modifier = Modifier
                    .width(140.dp)
            ) {
                Text(text = "Editar")
            }
        }
    }

}