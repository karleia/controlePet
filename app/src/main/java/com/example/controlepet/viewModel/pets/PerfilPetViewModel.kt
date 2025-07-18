package com.example.controlepet.viewModel.pets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.controlepet.model.PetWithClientName
import com.example.controlepet.repository.OfflineClientRepository
import com.example.controlepet.repository.OfflinePetRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PerfilPetViewModel(
    private val repo: OfflinePetRepository,
    private val repoClient: OfflineClientRepository,
    petId: Int
): ViewModel() {

    private val _petWithClientName = MutableStateFlow<PetWithClientName?>(null)
    val petWithClientName: StateFlow<PetWithClientName?> = _petWithClientName

    fun loadPetWithClientName(id: Int) {
        viewModelScope.launch {
            val pet = repo.getPetWithClientName(id)
            _petWithClientName.value = pet
        }
    }

   /*val pet: StateFlow<Pet?> = repo.getPet(petId)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)*/
}