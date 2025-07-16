package com.example.controlepet.model

import androidx.room.Embedded
import androidx.room.Relation

data class ServiceWithInfo (
    @Embedded
    val agendaService: AgendaServices,
    @Relation(
        parentColumn = "idService",
        entityColumn = "id"
    )
    val servico: Service
)