package com.works.kimkazandi.room.configs

import androidx.room.Database
import androidx.room.RoomDatabase
import com.works.kimkazandi.models.FavouriteRaffleData
import com.works.kimkazandi.models.RaffleData
import com.works.kimkazandi.room.dao.FavouriteRaffleDao
import com.works.kimkazandi.room.dao.RaffleDao

@Database(entities = [RaffleData::class, FavouriteRaffleData::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun raffleDao(): RaffleDao
    abstract fun favouriteRaffleDao(): FavouriteRaffleDao
}