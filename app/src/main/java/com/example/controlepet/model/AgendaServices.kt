package com.example.controlepet.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "AgendaServices",
    foreignKeys = [
        ForeignKey(
            entity = Agenda::class,
            parentColumns = ["id"],
            childColumns = ["idAgenda"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Service::class,
            parentColumns = ["id"],
            childColumns = ["idService"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["idAgenda"]),
        Index(value = ["idService"])
    ]
)

data class AgendaServices(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val idAgenda: Int = 0,
    val idService: Int = 0,
    val price: Double = 0.00,
)
