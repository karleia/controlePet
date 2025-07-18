package com.example.controlepet.viewModel.pets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.controlepet.model.PetWithClientName
import com.example.controlepet.repository.OfflinePetRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ListPetViewModel(
    private val repo: OfflinePetRepository
): ViewModel() {

   /*val petList: StateFlow<List<Pet>> = repo.getAllPets()
      .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())*/

    val petList: StateFlow<List<PetWithClientName>> =
        repo.getAllPetsWithClientName()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    private val _toastMessage = MutableSharedFlow<String>()
    val toastMessage = _toastMessage.asSharedFlow()

    private var _showDialog = MutableStateFlow(false)
    val showDialog: StateFlow<Boolean> = _showDialog

    fun setShowDialog(state: Boolean) {
        _showDialog.value = state
    }

    fun onDelete(petId: Int) {

        viewModelScope.launch {
            try {
                repo.deletePetById(petId)
                _toastMessage.emit("Pet excluído com sucesso")
            } catch (e: Exception) {
                _toastMessage.emit("Erro ao excluir: ${e.localizedMessage}")
            }
        }

    }

}