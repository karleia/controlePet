package com.example.controlepet.screens.agenda

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.controlepet.base.Routes
import com.example.controlepet.components.DividerLabel
import com.example.controlepet.components.LabelWithValue
import com.example.controlepet.viewModel.agenda.PerfilAgendaViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun PerfilAgendaScreen(
    paddingValues: PaddingValues,
    navController: NavController,
    agendaId: Int,
    vm: PerfilAgendaViewModel
) {
    val agendaCompleta by vm.agendaCompleta.collectAsState()

    LaunchedEffect(agendaId) {
        vm.loadAgendaCompleta(agendaId)
    }

    val agenda = agendaCompleta?.agenda
    val pet = agendaCompleta?.pet
    val nome_cliente = agendaCompleta?.clientName
    val servicos = agendaCompleta?.servicosSelecionados

    //formata os itens de datas
    val formatter = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
    val dataAgendamento = agenda?.date_time?.let {
        formatter.format(Date(it))
    } ?:"Data de agendamento não disponível"

    val dataCreate = agenda?.createdAt?.let {
        formatter.format(Date(it))
    } ?:"Data de criação não disponível"

    // Soma total dos preços dos serviços
    val total = servicos?.sumOf { it.agendaService.price }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        if (agenda != null) {
            Column(
                modifier = Modifier
                    .weight(5f)
                    .fillMaxSize()
                    .fillMaxHeight()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
            ) {

                LabelWithValue("Pet", pet?.name.toString())
                LabelWithValue("Data / Hora", dataAgendamento)
                LabelWithValue("Cliente", nome_cliente.toString() )

                DividerLabel("🧼 Serviços Agendados:")

                servicos?.forEach { servicoInfo ->
                    val servico = servicoInfo.servico
                    val preco = servicoInfo.agendaService.price
                    println(servicoInfo)
                    LabelWithValue(servico.name, "(R$ ${"%.2f".format(preco)})")
                }

                Spacer(modifier = Modifier.height(12.dp))
                LabelWithValue("💵 Total", "R$ ${"%.2f".format(total)}")

                LabelWithValue("Criado em", dataCreate)
                DividerLabel("Observações sobre o pet")
                Text(pet?.observation.toString())
            }

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .weight(1f),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = { navController.navigate(Routes.ListAgendaScreen.route) },
                    modifier = Modifier
                        .width(140.dp)
                ) {
                    Text("Voltar")
                }
                Button(
                    onClick = { navController.navigate(Routes.AgendaScreen.withId(agenda.id)) },
                    modifier = Modifier
                        .width(140.dp)
                ) {
                    Text(text = "Editar")
                }
            }
        } else {
            Text(text = "Carregando...")
        }
    }

}