package com.works.kimkazandi.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.works.kimkazandi.models.FavouriteRaffleData
import com.works.kimkazandi.models.RaffleData

@Dao
interface FavouriteRaffleDao {

    @Insert
    fun addFavourite(favouriteRaffleData: FavouriteRaffleData): Long

    @Query("Select * from favourite_raffles where href =:href")
    fun getFavouriteByHref(href: String?): FavouriteRaffleData?

    @Query("SELECT * FROM favourite_raffles")
    fun getAllRaffles(): List<FavouriteRaffleData>

    @Query("SELECT * FROM raffles WHERE href = :href")
    fun getRaffle(href: String?): RaffleData?

    @Delete
    fun deleteFavourite(favouriteRaffleData: FavouriteRaffleData)

    @Query("DELETE FROM favourite_raffles")
    fun deleteAllFavourites()
}