package com.example.controlepet.repository


import com.example.controlepet.DAO.PetDAO
import com.example.controlepet.model.Pet
import com.example.controlepet.model.PetWithClientName
import kotlinx.coroutines.flow.Flow

class OfflinePetRepository (
    private val petDAO: PetDAO
): PetRepository {

    override fun getAllPets(): Flow<List<Pet>> = petDAO.getAllPets()

    override fun getPet(id: Int): Flow<Pet?> = petDAO.getPet(id)

    override suspend fun getPetNow(id: Int): Pet? = petDAO.getPetNow(id)

    override suspend fun insertPet(pet: Pet) = petDAO.insert(pet)

    override suspend fun deletePet(pet: Pet) = petDAO.delete(pet)

    override suspend fun deletePetById(id: Int)  = petDAO.deleteById(id)

    override suspend fun updatePet(pet: Pet) = petDAO.update(pet)

    override fun getPetFilter(filter: String): Flow<List<Pet>> = petDAO.getPetFilter(filter)

    override fun getAllPetsWithClientName(): Flow<List<PetWithClientName>> {
        return petDAO.getAllPetsWithClientName()
    }

    override suspend fun getPetWithClientName(id: Int): PetWithClientName? {
        return petDAO.getPetWithClientName(id)
    }

}