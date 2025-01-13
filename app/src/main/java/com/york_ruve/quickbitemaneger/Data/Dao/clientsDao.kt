package com.york_ruve.quickbitemaneger.Data.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.york_ruve.quickbitemaneger.Data.Entities.ClientsEntity

@Dao
interface clientsDao {
    @Query("SELECT * FROM clients")
    fun getAllClients(): List<ClientsEntity>

    @Query("SELECT * FROM clients WHERE id = :id")
    fun getClientById(id: Int): ClientsEntity?

    @Insert
    fun insertClient(client: ClientsEntity)

    @Update
    fun updateClient(client:ClientsEntity)

    @Delete
    fun deleteClient(client: ClientsEntity)
}