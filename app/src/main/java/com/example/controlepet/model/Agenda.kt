package com.example.controlepet.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Pet::class,
            parentColumns = ["id"],
            childColumns = ["idPet"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["idPet"])]
)

data class Agenda (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val idPet: Int = 0,
    val date_time: Long = System.currentTimeMillis(),
    val createdAt: Long = System.currentTimeMillis(),
    val observation: String = ""
)