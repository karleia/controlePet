package com.example.controlepet.components


import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import java.text.Normalizer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AutoCompleteDropdown(
    allItems: List<String>,
    label: String = "Buscar",
    minCharsToFilter: Int = 3,
    onItemSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var query by remember { mutableStateOf("") }

    val filteredItems = remember(query) {
        if (query.isBlank()) emptyList()
        else if(query.length >= minCharsToFilter){
            val queryNoAccent = query.removeAccents().lowercase()
            allItems.filter { it.removeAccents().lowercase().contains(queryNoAccent, ignoreCase = true) }
        } else {
            emptyList()
        }
    }

    ExposedDropdownMenuBox(
        expanded = expanded && filteredItems.isNotEmpty(),
        onExpandedChange = { expanded = true }
    ) {
        OutlinedTextField(
            value = query,
            onValueChange = {
                query = it
                expanded = true
            },
            label = { Text(label) },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded) },
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
        )

        ExposedDropdownMenu(
            expanded = expanded && filteredItems.isNotEmpty(),
            onDismissRequest = { expanded = false }
        ) {
            filteredItems.forEach { item ->
                DropdownMenuItem(
                    text = { Text(item) },
                    onClick = {
                        query = item
                        expanded = false
                        onItemSelected(item)
                    }
                )
            }
        }
    }
}

fun String.removeAccents(): String {
    val normalized = Normalizer.normalize(this, Normalizer.Form.NFD)
    return normalized.replace("\\p{InCombiningDiacriticalMarks}+".toRegex(), "")
}