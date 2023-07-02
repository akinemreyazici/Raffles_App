package com.works.kimkazandi.room.repositories

import com.works.kimkazandi.models.RaffleData
import com.works.kimkazandi.room.dao.RaffleDao

class RafflesRoomRepositories(private val raffleDao: RaffleDao) : RaffleDao {

    override fun addRaffles(raffle: RaffleData): Long {
        return raffleDao.addRaffles(raffle)
    }

    override fun getAllRaffles(): List<RaffleData> {
        return raffleDao.getAllRaffles()
    }

    override fun getSelectedCategoryRaffles(category: String): List<RaffleData> {
        return raffleDao.getSelectedCategoryRaffles(category)
    }

    override fun deleteAllRaffles() {
        raffleDao.deleteAllRaffles()
    }

    fun isDatabaseEmpty(): Boolean {
        return raffleDao.getAllRaffles().isEmpty()
    }

    fun isSelectedCategoryEmpty(category: String): Boolean {
        return raffleDao.getSelectedCategoryRaffles(category).isEmpty()
    }
}

