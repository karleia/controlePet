package com.example.controlepet.components

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

import androidx.compose.runtime.remember

@SuppressLint("RememberReturnType")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SeletorDataHora(
    initialTimestamp: Long = 0L,
    initialDisplayText: String = "",
    onDateTimeSelected: (Long) -> Unit
) {
    val context = LocalContext.current

    var textoDataHora by remember { mutableStateOf(initialDisplayText) }
    var dataSelecionada by remember { mutableStateOf<Calendar?>(null) }

    val formatter = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())

    LaunchedEffect(initialTimestamp) {
        if (initialTimestamp > 0L) {
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = initialTimestamp
            dataSelecionada = calendar
            textoDataHora = initialDisplayText
        }
    }


    // Mostra o TimePicker
    val timePickerDialog = remember(dataSelecionada) {
        TimePickerDialog(context, { _, hour, minute ->
            dataSelecionada?.let { calendar ->
                calendar.set(Calendar.HOUR_OF_DAY, hour)
                calendar.set(Calendar.MINUTE, minute)
                val date = calendar.time
                textoDataHora = formatter.format(date)
                onDateTimeSelected(calendar.timeInMillis)
            }
        }, 12, 0, true)
    }

    // Mostra o DatePicker
    val datePickerDialog = remember {
        DatePickerDialog(
            context,
            { _, year, month, day ->
                val calendar = dataSelecionada ?: Calendar.getInstance()
                calendar.set(year, month, day)
                dataSelecionada = calendar

                // Após escolher a data, abre o TimePickerDialog!
                timePickerDialog.show()
            },
            Calendar.getInstance().get(Calendar.YEAR),
            Calendar.getInstance().get(Calendar.MONTH),
            Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        )
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = textoDataHora,
            onValueChange = {},
            readOnly = true,
            label = { Text("Data e hora") },
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    datePickerDialog.show()
                }
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
             datePickerDialog.show()
        }) {
            Text("Selecionar Data e Hora")
        }
    }
}