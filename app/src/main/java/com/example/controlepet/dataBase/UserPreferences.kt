package com.example.controlepet.dataBase

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "controle_pet_prefs")

class UserPreferences(private val context: Context) {

    companion object {
        private val CONTROLE_PET_LOGGED_IN_USER_ID = intPreferencesKey("controle_pet_logged_in_user_id")
        private val CONTROLE_PET_EDIT_ID = intPreferencesKey("controle_pet_edit_id")
   }

    val loggedInUserId: Flow<Int?> = context.dataStore.data
        .map { prefs -> prefs[CONTROLE_PET_LOGGED_IN_USER_ID ] }

    suspend fun saveUserId(userId: Int) {
        context.dataStore.edit { prefs ->
            prefs[CONTROLE_PET_LOGGED_IN_USER_ID] = userId
        }
    }

    fun getUserId(context: Context): Flow<Int?> {
        return context.dataStore.data.map { prefs ->
            prefs[CONTROLE_PET_LOGGED_IN_USER_ID]
        }
    }

    suspend fun clearUser() {
        context.dataStore.edit { prefs ->
            prefs.remove(CONTROLE_PET_LOGGED_IN_USER_ID)
        }
    }
}