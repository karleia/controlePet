package com.example.controlepet.model

import androidx.room.ColumnInfo
import androidx.room.Embedded

data class PetWithClientName (
    @Embedded
    val pet: Pet,
    @ColumnInfo(name = "client_name")
    val clientName: String
)