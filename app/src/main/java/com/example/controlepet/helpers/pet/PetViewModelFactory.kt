package com.example.controlepet.helpers.pet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.controlepet.repository.OfflineClientRepository
import com.example.controlepet.repository.OfflinePetRepository
import com.example.controlepet.screens.pets.ListPetViewModel
import com.example.controlepet.screens.pets.PerfilPetViewModel
import com.example.controlepet.screens.pets.PetViewModel

class PetViewModelFactory(
    private val repository: OfflinePetRepository,
    private val repositoryClient: OfflineClientRepository,
    private val petId: Int
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListPetViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ListPetViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(PetViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PetViewModel(repository, repositoryClient) as T
        }
        if (modelClass.isAssignableFrom(PerfilPetViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PerfilPetViewModel(repository, repositoryClient, petId) as T
        }
        throw IllegalArgumentException("Classe desconhecida: $modelClass")
    }
}