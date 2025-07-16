package com.example.controlepet.screens.agenda

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.controlepet.base.Routes
import com.example.controlepet.components.AutoCompleteDropdown
import com.example.controlepet.components.DropDownFields
import com.example.controlepet.components.SeletorDataHora
import com.example.controlepet.dataBase.AppDatabase
import com.example.controlepet.helpers.agenda.AgendaViewModelFactory
import com.example.controlepet.helpers.provideViewModel
import com.example.controlepet.repository.OfflineAgendaRepository
import com.example.controlepet.model.ServiceSelecionado
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class PetItemDisplay(
    val id: Int,
    val display: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgendaScreen(
    paddingValues: PaddingValues,
    navController: NavController,
    agendaIdToEdit: Int = 0
) {
    val context = LocalContext.current
    val db = remember { AppDatabase.getDatabase(context) }
    val repo = remember { OfflineAgendaRepository(db.AgendaDAO()) }
    val factory = remember {  AgendaViewModelFactory(repo, agendaIdToEdit) }
    val vm:  AgendaViewModel = provideViewModel(factory)

    //para o dropdow mostrar o nome do pet com o nome do dono no formato (pet - dono)
    val pets by vm.petList.collectAsState()
    val listaPets = pets.map {
        PetItemDisplay(id = it.pet.id, display = "${it.pet.name} - ${it.clientName}")
    }
    val mapaPetPorLabel = listaPets.associateBy { it.display }

    var petSelecionado by remember { mutableStateOf("") }
    var petIdSelecionado: Int? by remember { mutableStateOf(null) }


    //para o servicos
    val services by vm.serviceList.collectAsState()
    val _listaServicos = services.map {
        ServiceSelecionado(idService = it.id, name = it.name, price = it.price)
    }
    val mapaServicePorLabel = _listaServicos.associateBy{ it.labelComPreco}

    var listaServicos by remember { mutableStateOf(listOf<ServiceSelecionado>()) }

    var serviceSelected by remember { mutableStateOf("") }

    //mensagem de erro
    val isSuccess by remember { derivedStateOf { vm.isSuccess } }

    //formatar a data e a hora
    var timestampSelecionado by remember { mutableStateOf(vm.dataHoraSelecionada) }
    val formatter = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
    val textoDataHora = if (timestampSelecionado > 0L) { formatter.format(Date(timestampSelecionado)) } else {  "" }

    //iniciar se isSuccess for true
    LaunchedEffect(isSuccess) {
        if (isSuccess) {
            Toast.makeText(context, "Dados salvos com sucesso", Toast.LENGTH_LONG).show()
            navController.navigate(Routes.ListAgendaScreen.route)
        }
    }

    val agendaEditando by vm.agendaCompleta.collectAsState()
    //carrega os dados para edição se agendIdToEdit for maior que 0
     LaunchedEffect(agendaEditando) {
        if (agendaIdToEdit != 0) {
            vm.loadAgendaForEdit(agendaIdToEdit)
        }
     }

   LaunchedEffect(agendaEditando) {
        agendaEditando?.let {
            val pet = "${it.pet.name} - ${it.clientName }"
            petSelecionado = pet
            petIdSelecionado = it.agenda.idPet
            timestampSelecionado = it.agenda.date_time
            vm.dataHoraSelecionada = it.agenda.date_time

            listaServicos = it.servicosSelecionados.map { s ->
                ServiceSelecionado(
                    idService = s.servico.id,
                    name = s.servico.name,
                    price = s.agendaService.price
                )
            }
        }
    }

     Column(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxHeight()
            .verticalScroll(rememberScrollState())
            .padding(paddingValues)
            .padding(16.dp)
    ){

        AutoCompleteDropdown(
            allItems = mapaPetPorLabel.keys.toList(),
            label = "Digite o nome do Pet para buscar",
            onItemSelected = { labelSelecionado ->
                petSelecionado = labelSelecionado
                petIdSelecionado = mapaPetPorLabel[labelSelecionado]?.id
            }
        )
        Spacer(modifier = Modifier.height(12.dp))
        if(!petSelecionado.isEmpty()) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Cliente: $petSelecionado",
                style = MaterialTheme.typography.titleLarge.copy(
                    color = Color.Blue,
                    fontWeight = FontWeight.Bold
                ),
                textAlign = TextAlign.Left
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        // Data e hora do agendamento
         SeletorDataHora(
             initialTimestamp = timestampSelecionado,
             initialDisplayText = textoDataHora,
              onDateTimeSelected = { timestamp ->
                  timestampSelecionado = timestamp
                  vm.dataHoraSelecionada = timestamp
                  vm.dataHoraFormatada = formatter.format(Date(timestamp))
              }
         )
        Spacer(modifier = Modifier.height(12.dp))

        DropDownFields(
            label = "Serviços",
            options = mapaServicePorLabel.keys.toList(),
            selectedOption = serviceSelected,
            onOptionSelected = {
                serviceSelected = it
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        //buscar o servico com o valor e adicionar serviço na listagem
        Button(
            onClick = {
                val servicoObj = mapaServicePorLabel[serviceSelected]
                if (servicoObj != null && listaServicos.none { it.idService === servicoObj.idService} ) {
                    listaServicos = listaServicos + servicoObj
                    serviceSelected = ""
                }
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Adicionar")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Serviços para agendamento \n( ${listaServicos.size} adicionados )" ,
            style = MaterialTheme.typography.labelMedium.copy(
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(modifier = Modifier.height(16.dp))

        // lista dos servicos adicionados com valor
        listaServicos.forEach { servico ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(servico.labelComPreco)
                IconButton(onClick = {
                    listaServicos = listaServicos - servico
                }) {
                    Icon(Icons.Default.Delete, contentDescription = "Remover")
                }
            }
        }

         Row(
             modifier = Modifier
                 .fillMaxSize()
                 .padding(16.dp),
             horizontalArrangement = Arrangement.Center
         ) {
             Button(
                 onClick = {
                     val listaConvertida = listaServicos.map {
                         ServiceSelecionado(
                             idService = it.idService,
                             name = it.name,
                             price = it.price
                         )
                     }
                     if (petIdSelecionado != null && petIdSelecionado != 0) {
                         vm.saveAgenda(listaConvertida, petIdSelecionado!!)
                     } else {
                         Toast.makeText(context, "Selecione todos os dados", Toast.LENGTH_SHORT).show()
                     }
                 },
                 modifier = Modifier
                     .width(140.dp)
             ) {
                 Text(text = "Concluir")
             }
             Button(
                 onClick = {
                     navController.navigate("ListAgendaScreen")
                 },
                 modifier = Modifier
                     .width(140.dp)
             ) {
                 Text(text = "Cancelar")
             }
         }
    }
}

