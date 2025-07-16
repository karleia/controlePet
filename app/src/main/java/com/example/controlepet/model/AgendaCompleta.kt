package com.example.controlepet.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Relation

data class AgendaCompleta (
    @Embedded(prefix = "agenda_")
    val agenda: Agenda,

    @Embedded(prefix = "pet_")
    val pet: Pet,

    @ColumnInfo(name = "client_name")
    val clientName: String,

    @Relation(
        entity = AgendaServices::class,
        parentColumn = "agenda_id", // agenda.id virou agenda_id com prefixo
        entityColumn = "idAgenda"
    )
    val servicosSelecionados: List<ServiceWithInfo>
)