package com.example.controlepet

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.NavController
import com.example.controlepet.screens.agenda.PerfilAgendaScreen
import com.example.controlepet.test.FakePerfilAgendaViewModel
import io.mockk.mockk
import org.junit.Rule
import org.junit.Test

class PerfilAgendaScreenTest {

    @get:Rule val composeTestRule = createComposeRule()

    @Test
    fun shouldDisplayLoadingWhenAgendaIsNull() {

        val fakeNavController = mockk<NavController>(relaxed = true)
        val fakeVm = FakePerfilAgendaViewModel(iniciarComValor = false)
        composeTestRule.setContent {
            PerfilAgendaScreen(
                paddingValues = PaddingValues(),
                navController = fakeNavController,
                agendaId = 1,
                vm = fakeVm
            )
        }
        composeTestRule.onNodeWithText("Carregando...").assertExists()
    }
}
