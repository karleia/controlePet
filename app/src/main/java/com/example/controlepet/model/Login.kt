package com.example.controlepet.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "login")
data class Login(
    @PrimaryKey(autoGenerate = true)
    val idLogin: Int = 0,
    val name: String = "",
    val email: String = "",
    val logo: String = ""
)
