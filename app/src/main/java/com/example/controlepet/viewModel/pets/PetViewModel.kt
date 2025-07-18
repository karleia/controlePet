package com.example.controlepet.viewModel.pets

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.controlepet.model.Client
import com.example.controlepet.model.Pet
import com.example.controlepet.repository.OfflineClientRepository
import com.example.controlepet.repository.OfflinePetRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class PetViewModel(
    private val repo: OfflinePetRepository,
    private val clientRepo: OfflineClientRepository
): ViewModel() {

    val clientList: StateFlow<List<Client>> = clientRepo.getAllClients()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            emptyList()
        )

    var errorMessage by mutableStateOf<String?>(null)
    var isSuccess by mutableStateOf(false)
        private set

    var editingId: Int? = null
        private set

    private val _clientIdSelected = MutableStateFlow(0)
    val clientIdSelected: StateFlow<Int> = _clientIdSelected

    var tipoCadastro by mutableStateOf("cadastrar")

    var clientId by mutableStateOf(0)
    var name by mutableStateOf("")
    var typePet by mutableStateOf("")
    var breed by mutableStateOf("")
    var observation by mutableStateOf("")
    var color by mutableStateOf("")
    var pelagem by mutableStateOf("")
    var sex by mutableStateOf("")
    var isEditing = false

    fun savePet() {
        viewModelScope.launch {
            try {
                val clientId = _clientIdSelected.value
                val pet = Pet(
                    id = editingId ?: 0, // 0 se for novo
                    clientId = clientId,
                    name = name,
                    breed = breed,
                    pelagem = pelagem,
                    color = color,
                    typePet = typePet,
                    sex = sex,
                    observation = observation
                )

                if (editingId == null) {
                    repo.insertPet(pet)
                    tipoCadastro = "cadastrar"
                } else {
                    repo.updatePet(pet)
                    tipoCadastro = "editar"
                }
                isSuccess = true
            }
            catch ( e: Exception ) {
                Log.e("Register", "Erro ao cadastrar", e)
            }
        }
    }

    fun loadPetForEdit(petId: Int) {
        viewModelScope.launch {
            val pet = repo.getPetNow(petId)
            pet?.let {
                editingId = it.id
                name = it.name
                clientId = it.clientId
                typePet = it.typePet
                breed = it.breed
                pelagem = it.pelagem
                color = it.color
                sex = it.sex
                observation = it.observation
                _clientIdSelected.value = it.clientId
            }
        }
    }

    fun resetState() {
        isSuccess = false
        errorMessage = null
        name = ""
    }

    fun setClientIdSelected(clientId: Int) {
        _clientIdSelected.value = clientId
    }

}