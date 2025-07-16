package com.example.controlepet.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.controleagendaServices.DAO.AgendaServicesDAO
import com.example.controlepet.DAO.AgendaDAO
import com.example.controlepet.DAO.ClientDAO
import com.example.controlepet.DAO.PetDAO
import com.example.controlepet.DAO.UserDAO
import com.example.controlepet.model.Agenda
import com.example.controlepet.model.AgendaServices
import com.example.controlepet.model.Client
import com.example.controlepet.model.Pet
import com.example.controlepet.model.Service
import com.example.controlepet.model.User
import com.example.controleservice.DAO.ServiceDAO

@Database(
    entities = [
        User::class,
        Client::class,
        Pet::class,
        Service::class,
        Agenda::class,
        AgendaServices::class
    ],
    version = 5,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun UserDAO(): UserDAO
    abstract fun ClientDAO(): ClientDAO
    abstract fun PetDAO(): PetDAO
    abstract fun ServiceDAO(): ServiceDAO
    abstract fun AgendaDAO(): AgendaDAO
    abstract fun AgendaServicesDAO(): AgendaServicesDAO

    companion object {
        @Volatile
        private var Instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, AppDatabase::class.java, "control_pet_database")
                     .build()
                    .also { Instance = it }
            }
        }
    }
}

//.fallbackToDestructiveMigration()