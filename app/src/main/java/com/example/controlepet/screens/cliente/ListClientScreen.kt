package com.example.controlepet.screens.cliente

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.NavController
import com.example.controlepet.base.Routes
import com.example.controlepet.viewModel.cliente.ListClientViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListClientScreen(
    paddingValues: PaddingValues,
    navController: NavController,
    viewModel: ListClientViewModel
)
{
    val clients by viewModel.clientList.collectAsState()
    val showDialog by viewModel.showDialog.collectAsState()
    var clientSelected by remember { mutableStateOf(0) }

    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(Unit) {
        viewModel.toastMessage.collect { message ->
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        }
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize()
                .padding(paddingValues)
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        items(items = clients, key = { it.id }) { client ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Nome: ${client.name}", style = MaterialTheme.typography.titleMedium)
                    Text("E-mail: ${client.email}", style = MaterialTheme.typography.bodyMedium)
                    Text("Endereço: ${client.address}", style = MaterialTheme.typography.bodySmall)

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp))
                    {
                        Icon(
                            Icons.Default.Visibility,
                            contentDescription = "",
                            modifier = Modifier.clickable {
                                navController.navigate("PerfilClientScreen/${client.id}")
                            }
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Icon(
                            Icons.Default.Edit,
                            contentDescription = "",
                            modifier = Modifier.clickable {
                                navController.navigate("ClientScreen/${client.id}")
                            }
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Icon(
                            Icons.Default.Delete,
                            contentDescription = "",
                            modifier = Modifier.clickable {
                                 clientSelected = client.id
                                 viewModel.setShowDialog(true)
                            }
                        )
                        Spacer(modifier = Modifier.width(10.dp))

                    }
                }
            }
        }
    }

    Box (
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        contentAlignment = Alignment.BottomEnd
    ) {
        FloatingActionButton(onClick = {  navController.navigate(Routes.ClientScreen.withId(0))
        }) {
            Icon(Icons.Default.Add, contentDescription = "")
        }
    }


    if (showDialog) {
        AlertDialog(
            onDismissRequest = { viewModel.setShowDialog(false) },
            title = { Text("Confirmar Exclusão do cliente") },
            text = { Text("Deseja realmente excluir este cliente?") },
            confirmButton = {
                TextButton(onClick = {
                    viewModel.onDelete(clientSelected)
                    viewModel.setShowDialog(false)
                }) {
                    Text("Excluir", color = Color.Red)
                }
            },
            dismissButton = {
                TextButton(onClick = { viewModel.setShowDialog(false) }) {
                    Text("Cancelar")
                }
            }
        )
    }

}
