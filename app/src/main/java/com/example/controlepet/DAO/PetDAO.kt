package com.example.controlepet.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.controlepet.model.Pet
import com.example.controlepet.model.PetWithClientName
import kotlinx.coroutines.flow.Flow

@Dao
interface PetDAO {

    @Query("SELECT * FROM pet")
    fun getAllPets(): Flow<List<Pet>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(pet: Pet)

    @Update
    suspend fun update(pet: Pet)

    @Delete
    suspend fun delete(pet: Pet)

    @Query("DELETE FROM pet WHERE id = :petId")
    suspend fun deleteById(petId: Int)

    @Query("SELECT * from Pet WHERE id = :id")
    fun getPet(id: Int): Flow<Pet>

    @Query("SELECT * from Pet WHERE id = :id")
    suspend fun getPetNow(id: Int): Pet?

    @Query("SELECT * FROM Pet WHERE name LIKE '%' || :filter || '%' " )
    fun getPetFilter(filter: String): Flow<List<Pet>>

    @Query("""
        SELECT pet.*, client.name AS client_name
        FROM pet 
        INNER JOIN client ON pet.clientId = client.id """)
    fun getAllPetsWithClientName(): Flow<List<PetWithClientName>>

    @Query("""
        SELECT pet.*, client.name AS client_name
        FROM pet 
        INNER JOIN client ON pet.clientId = client.id 
        WHERE pet.id = :id""")
    suspend fun getPetWithClientName(id: Int): PetWithClientName?

}