package com.example.controlepet.viewModel.cadastroUsuario

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.controlepet.model.User
import com.example.controlepet.repository.OfflineUserRepository
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream

class RegisterUserViewModel(
    private val repo: OfflineUserRepository
): ViewModel() {

    var errorMessage by mutableStateOf<String?>(null)
    var isSuccess by mutableStateOf(false)
        private set

    var editingUserId: Int? = null
        private set

    var tipoCadastro by mutableStateOf("cadastrar")

    //step-1
    var name by mutableStateOf("")
    var email by mutableStateOf("")
    var document by mutableStateOf("")
    var description by mutableStateOf("")
    var cellPhone by mutableStateOf("")
    var cellPhone2 by mutableStateOf("")
    var typePeople by mutableStateOf("")

    //step-2
    var logoBase64 by mutableStateOf("")
    var companyName by mutableStateOf("")

    //step-3
    var address by mutableStateOf("")
    var cep by mutableStateOf("")
    var neighborhood by mutableStateOf("")
    var complement by mutableStateOf("")
    var number by mutableStateOf("")
    var city by mutableStateOf("")
    var state by mutableStateOf("")
    var observation_address by mutableStateOf("")



    //step-4
    var password by mutableStateOf("")

    var currentStep by mutableStateOf(0)
        private set


    companion object { const val TOTAL_STEPS = 4 }

    fun saveLogo(context: Context, bitmap: Bitmap) {
        val filename = "logo_${System.currentTimeMillis()}"
        val imageName = saveImageToInternalStorage(context, bitmap, filename)
        imageName?.let {
            logoBase64 = it
        }
    }

    fun nextStep() {
        if (currentStep < TOTAL_STEPS - 1) currentStep++
    }
    fun previousStep() {
        if (currentStep > 0) currentStep--
    }

    fun saveUser() {
        viewModelScope.launch {
            try {
                val user = User(
                    id = editingUserId ?: 0, // 0 se for novo usuário
                    name = name,
                    email = email,
                    document = document,
                    description = description,
                    cellPhone = cellPhone,
                    cellPhone2 = cellPhone2,
                    typePeople = typePeople,
                    address = address,
                    cep = cep,
                    neighborhood = neighborhood,
                    complement = complement,
                    number = number,
                    city = city,
                    state = state,
                    observationAddress = observation_address,
                    password = password,
                    logo = logoBase64
                )

                if (editingUserId == null) {
                    repo.insertUser(user)
                    tipoCadastro = "cadastrar"
                } else {
                    repo.updateUser(user)
                    tipoCadastro = "editar"
                }
                isSuccess = true
            }
            catch ( e: Exception ) {
                Log.e("Register", "Erro ao cadastrar", e)
            }
        }
    }
    fun saveImageToInternalStorage(context: Context, bitmap: Bitmap, filename: String): String? {
        return try {
            val directory = File(context.filesDir, "images")
            if (!directory.exists()) directory.mkdir()

            val file = File(directory, "$filename.png")
            val outputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
            outputStream.flush()
            outputStream.close()

            file.name
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun loadUserForEdit(userId: Int) {
        viewModelScope.launch {
            val user = repo.getUserNow(userId)
            user?.let {
                editingUserId = it.id
                name = it.name
                email = it.email
                document = it.document
                description = it.description
                cellPhone = it.cellPhone
                cellPhone2 = it.cellPhone2
                typePeople = it.typePeople
                logoBase64 = it.logo
                address = it.address
                cep = it.cep
                neighborhood = it.neighborhood
                complement = it.complement
                number = it.number
                city = it.city
                state = it.state
                observation_address = it.observationAddress
                password = it.password
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