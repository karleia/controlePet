package com.example.controlepet.screens.services
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.controlepet.model.Service
import com.example.controlepet.repository.OfflineServiceRepository
import kotlinx.coroutines.launch

class ServiceViewModel(
    private val repo: OfflineServiceRepository,
): ViewModel() {

    var errorMessage by mutableStateOf<String?>(null)
    var isSuccess by mutableStateOf(false)
        private set

    var editingId: Int? = null
        private set


    var tipoCadastro by mutableStateOf("cadastrar")

    var serviceId by mutableStateOf(0)
    var name by mutableStateOf("")
    var price by mutableStateOf(0.00)

    fun saveService() {
        viewModelScope.launch {
            try {
                val service = Service(
                    id = editingId ?: 0, // 0 se for novo
                    name = name,
                    price = price
                )

                if (editingId == null) {
                    repo.insertService(service)
                    tipoCadastro = "cadastrar"
                } else {
                    repo.updateService(service)
                    tipoCadastro = "editar"
                }
                isSuccess = true
            }
            catch ( e: Exception ) {
                Log.e("Register", "Erro ao cadastrar", e)
            }
        }
    }

    fun loadServiceForEdit(serviceId: Int) {
        viewModelScope.launch {
            val pet = repo.getServiceNow(serviceId)
            pet?.let {
                editingId = it.id
                name = it.name
                price = it.price
            }
        }
    }

    fun resetState() {
        isSuccess = false
        errorMessage = null
        name = ""
    }

}