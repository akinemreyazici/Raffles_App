package com.works.kimkazandi.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.works.kimkazandi.models.RaffleData

@Dao
interface RaffleDao {
    @Insert
    fun addRaffles(raffle: RaffleData): Long // Yeni çekilişleri ekleme

    @Query("select * from raffles") // Tüm listeyi getirmesi için
    fun getAllRaffles(): List<RaffleData>

    @Query("select * from raffles where category like :category ")
    fun getSelectedCategoryRaffles(category: String): List<RaffleData>

    @Query("DELETE FROM raffles")
    fun deleteAllRaffles()

}