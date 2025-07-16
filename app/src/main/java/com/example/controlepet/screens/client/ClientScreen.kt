package com.example.controlepet.screens.client

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.controlepet.helpers.client.provideClientViewModel

@Composable
fun ClientScreen(
    paddingValues: PaddingValues,
    navController: NavController,
    clientIdToEdit: Int = 0
) {
    val vm = provideClientViewModel()

    LaunchedEffect(clientIdToEdit) {
        if (clientIdToEdit != 0) {
            vm.loadClientForEdit(clientIdToEdit)
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        when (vm.currentStep) {
            0 -> ClientScreen_step1(paddingValues, navController,vm)
            1 -> ClientScreen_step2(paddingValues, navController,vm)
        }
    }
}