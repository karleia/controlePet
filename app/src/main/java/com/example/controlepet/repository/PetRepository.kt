package com.example.controlepet.repository

import com.example.controlepet.model.Pet
import com.example.controlepet.model.PetWithClientName
import kotlinx.coroutines.flow.Flow

interface PetRepository {
    fun getAllPets(): Flow<List<Pet>>

    fun getPet(id: Int): Flow<Pet?>

    suspend fun getPetNow(id: Int): Pet?

    fun getPetFilter(filter: String): Flow<List<Pet>>

    suspend fun insertPet(Pet: Pet)

    suspend fun deletePet(Pet: Pet)

    suspend fun deletePetById(id: Int)

    suspend fun updatePet(Pet: Pet)

    fun getAllPetsWithClientName(): Flow<List<PetWithClientName>>

    suspend fun getPetWithClientName(id: Int): PetWithClientName?
}