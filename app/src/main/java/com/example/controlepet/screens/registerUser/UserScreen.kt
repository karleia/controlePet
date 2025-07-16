package com.example.controlepet.screens.registerUser

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.controlepet.helpers.user.provideRegisterUserViewModel

@Composable
fun UserScreen(
    paddingValues: PaddingValues,
    navController: NavController,
    userIdToEdit: Int = 0
) {
    val vm = provideRegisterUserViewModel()

    LaunchedEffect(userIdToEdit) {
        if (userIdToEdit != 0) {
            vm.loadUserForEdit(userIdToEdit)
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        when (vm.currentStep) {
            0 -> RegisterUserScreen(paddingValues, navController,vm)
            1 -> RegisterUserScreen_step2(paddingValues, navController,vm)
            2 -> RegisterUserScreen_step3(paddingValues, navController,vm)
            3 -> RegisterUserScreen_step4(paddingValues, navController,vm)
        }
    }
}