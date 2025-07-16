package com.example.controlepet.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.controlepet.model.Client
import com.example.controlepet.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface ClientDAO {

    @Query("SELECT * FROM client")
    fun getAll(): Flow<List<Client>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(client: Client)

    @Update
    suspend fun update(client: Client)

    @Delete
    suspend fun delete(client: Client)

    @Query("DELETE FROM client WHERE id = :clientId")
    suspend fun deleteById(clientId: Int)

    @Query("SELECT * from Client WHERE id = :id")
    fun getClient(id: Int): Flow<Client>

    @Query("SELECT * from Client WHERE id = :id")
    suspend fun getClientNow(id: Int): Client?

    @Query("SELECT * FROM Client WHERE name LIKE '%' || :filter || '%' OR document LIKE '%' || :filter || '%' " )
    fun getClientFilter(filter: String): Flow<List<Client>>

}