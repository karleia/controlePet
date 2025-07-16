package com.example.controlepet.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Service")
data class Service(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String = "",
    val price: Double = 00.0
)
