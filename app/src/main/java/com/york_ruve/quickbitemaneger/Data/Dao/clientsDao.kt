package com.york_ruve.quickbitemaneger.Data.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.york_ruve.quickbitemaneger.Data.Entities.ClientsEntity
import com.york_ruve.quickbitemaneger.Data.Relations.ClientWithOrdersAndDishes

@Dao
interface clientsDao {
    @Query("SELECT * FROM clients")
    suspend fun getAllClients(): List<ClientsEntity>

    @Transaction
    @Query("SELECT * FROM clients")
    suspend fun getAllClientsWithOrdersAndDishes(): List<ClientWithOrdersAndDishes>

    @Query("SELECT * FROM clients WHERE id = :id")
    suspend fun getClientById(id: Int): ClientsEntity?

    @Insert
    suspend fun insertClient(client: ClientsEntity)

    @Update
    suspend fun updateClient(client:ClientsEntity)

    @Delete
    suspend fun deleteClient(client: ClientsEntity)
}