package com.example.controlepet.viewModel.cliente

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.controlepet.model.Client
import com.example.controlepet.repository.OfflineClientRepository
import kotlinx.coroutines.launch

class ClientViewModel(
    private val repo: OfflineClientRepository
): ViewModel() {

    var errorMessage by mutableStateOf<String?>(null) 
    var isSuccess by mutableStateOf(false)
        private set

    var editingId: Int? = null
        private set

    //step-1
    var name by mutableStateOf("")
    var email by mutableStateOf("")
    var document by mutableStateOf("")
    var observation by mutableStateOf("")
    var cellPhone by mutableStateOf("")
    var cellPhone2 by mutableStateOf("")
    var numberWhatsapp by mutableStateOf("")

    //step-2
    var address by mutableStateOf("")
    var cep by mutableStateOf("")
    var neighborhood by mutableStateOf("")
    var complement by mutableStateOf("")
    var number by mutableStateOf("")
    var city by mutableStateOf("")
    var state by mutableStateOf("")
    var observationAddress by mutableStateOf("")

    var tipoCadastro by mutableStateOf("cadastrar")

    var currentStep by mutableStateOf(0)
        private set

    companion object { const val TOTAL_STEPS = 2 }


    fun nextStep() {
        if (currentStep < TOTAL_STEPS - 1) currentStep++
    }
    fun previousStep() {
        if (currentStep > 0) currentStep--
    }

    fun saveClient() {
        viewModelScope.launch {
            try {
                val client = Client(
                    id = editingId ?: 0, // 0 se for novo
                    name = name,
                    email = email,
                    document = document,
                    cellphone = cellPhone,
                    cellphone2 = cellPhone2,
                    numberWhatsapp = numberWhatsapp,
                    address = address,
                    cep = cep,
                    neighborhood = neighborhood,
                    complement = complement,
                    number = number,
                    city = city,
                    state = state,
                    observationAddress = observationAddress,
                    observation = observation
                )

                if (editingId == null) {
                    repo.insertClient(client)
                    tipoCadastro = "cadastrar"
                } else {
                    repo.updateClient(client)
                    tipoCadastro = "editar"
                }
                isSuccess = true
            }
            catch ( e: Exception ) {
                Log.e("Register", "Erro ao cadastrar", e)
            }
        }
    }

    fun loadClientForEdit(clientId: Int) {
        viewModelScope.launch {
            val client = repo.getClientNow(clientId)
            client?.let {
                editingId = it.id
                name = it.name
                email = it.email
                document = it.document
                cellPhone = it.cellphone
                cellPhone2 = it.cellphone2
                numberWhatsapp = it.numberWhatsapp
                address = it.address
                cep = it.cep
                neighborhood = it.neighborhood
                complement = it.complement
                number = it.number
                city = it.city
                state = it.state
                observationAddress = it.observationAddress
                observation = it.observation
            }
        }
    }

    fun resetState() {
        isSuccess = false
        errorMessage = null
        name = ""
        email = ""
    }
}