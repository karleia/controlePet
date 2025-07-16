package com.example.controlepet.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "Client")
data class Client(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String = "",
    val email: String = "",
    val document: String = "",
    val cep: String = "",
    val address: String = "",
    val neighborhood: String = "",
    val complement: String = "",
    val city: String = "",
    val state: String = "",
    val number: String = "",
    val observationAddress: String = "",
    val observation: String = "",
    val cellphone: String = "",
    val cellphone2: String = "",
    val numberWhatsapp: String = ""
)
