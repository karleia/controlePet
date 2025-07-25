package com.example.controlepet.viewModel.agenda

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.controlepet.model.Agenda
import com.example.controlepet.model.AgendaCompleta
import com.example.controlepet.model.AgendaServices
import com.example.controlepet.model.PetWithClientName
import com.example.controlepet.model.Service
import com.example.controlepet.model.ServiceSelecionado
import com.example.controlepet.repository.AgendaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AgendaViewModel(
    private val repo: AgendaRepository,
): ViewModel() {

    companion object {
        private const val NOVO_ID = 0
    }

    val listaPets: StateFlow<List<PetWithClientName>> =
        repo.getAllPetsWithClientName()
            .stateIn(viewModelScope, SharingStarted.Companion.WhileSubscribed(5_000), emptyList())

    val serviceList: StateFlow<List<Service>> =
        repo.getAllService()
            .stateIn(viewModelScope, SharingStarted.Companion.WhileSubscribed(5_000), emptyList())

    var editingId: Int? = null
        private set

    var errorMessage by mutableStateOf<String?>(null)
    var isSuccess by mutableStateOf(false)

    var dataHoraSelecionada by mutableStateOf(0L)
    var dataHoraFormatada by mutableStateOf("")
    var data by mutableStateOf("")
    var hora by mutableStateOf("")

    //dados para editar
    private val _agendaCompleta = MutableStateFlow<AgendaCompleta?>(null)
    val agendaCompleta: StateFlow<AgendaCompleta?> = _agendaCompleta

    //save agenda salva novos dados quando NOVO_ID for 0
    //editar os dados quando for passado o editingId
    fun saveAgenda(servicos: List<ServiceSelecionado>, petId: Int) {
        viewModelScope.launch {
            try {
                val novaAgenda = Agenda(
                    id = editingId ?: NOVO_ID, // 0 se for novo
                    idPet = petId,
                    date_time = dataHoraSelecionada,
                    createdAt = obterTimestampAtual()
                )

                val listaAgendaServices = servicos.map {
                    AgendaServices(
                        idAgenda = 0,
                        idService = it.idService,
                        price = it.price
                    )
                }

                if (editingId != null && editingId != 0) {
                        Log.e("Editando", "${editingId}")
                     repo.updateAgendaWithServices(novaAgenda, listaAgendaServices)
                } else {
                    repo.insertAgendaWithServices(novaAgenda, listaAgendaServices)
                }
                isSuccess = true
            }
            catch ( e: Exception ) {
                errorMessage = "Erro ao salvar a agenda: ${e.localizedMessage}"
                Log.e("Register", "Erro ao cadastrar", e)
            }
        }
    }

    fun loadAgendaForEdit(agendaId: Int) {
        viewModelScope.launch {
            _agendaCompleta.value = repo.getAgendaCompleta(agendaId)
            editingId = agendaId
        }
    }

    fun obterTimestampAtual(): Long {
        val agora = Date()
        val formatter = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
        return formatter.parse(formatter.format(agora))?.time ?: System.currentTimeMillis()
    }
}