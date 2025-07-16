package com.example.controlepet.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "Pet",
    foreignKeys = [
        ForeignKey(
            entity = Client::class,
            parentColumns = ["id"],
            childColumns = ["clientId"],
            onDelete = ForeignKey.NO_ACTION
        ),
    ],
    indices = [
        Index(value = ["clientId"])
    ]
)
data class Pet(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val clientId: Int = 0,
    val name: String = "",
    val typePet: String = "",
    val breed : String = "",
    val color: String = "",
    val pelagem: String = "",
    val sex: String = "",
    val observation: String = ""
)
