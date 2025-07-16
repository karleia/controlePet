package com.example.controlepet.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User")
data class User (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val document: String = "",
    val typePeople: String = "",
    val cep: String = "",
    val address: String = "",
    val neighborhood: String = "",
    val complement: String = "",
    val city: String = "",
    val state: String = "",
    val number: String = "",
    val observationAddress: String = "",
    val description: String = "",
    val logo: String = "",
    val cellPhone: String = "",
    val cellPhone2: String = "",
)
