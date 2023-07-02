package com.works.kimkazandi.room.repositories

import com.works.kimkazandi.models.FavouriteRaffleData
import com.works.kimkazandi.models.RaffleData
import com.works.kimkazandi.room.dao.FavouriteRaffleDao


class FavouriteRafflesRoomRepositories(private val favouriteRaffleDao: FavouriteRaffleDao) : FavouriteRaffleDao {
    override fun addFavourite(favouriteRaffleData: FavouriteRaffleData): Long {
        return favouriteRaffleDao.addFavourite(favouriteRaffleData)
    }

    override fun getFavouriteByHref(href: String?): FavouriteRaffleData? {
        return favouriteRaffleDao.getFavouriteByHref(href)
    }

    override fun getAllRaffles(): List<FavouriteRaffleData> {
        return favouriteRaffleDao.getAllRaffles()
    }

    override fun getRaffle(href: String?): RaffleData? {
        return favouriteRaffleDao.getRaffle(href)
    }

    override fun deleteFavourite(favouriteRaffleData: FavouriteRaffleData) {
        favouriteRaffleDao.deleteFavourite(favouriteRaffleData)
    }

    override fun deleteAllFavourites() {
        favouriteRaffleDao.deleteAllFavourites()
    }

    fun isExist(href: String?): Boolean {
        val raffleData = favouriteRaffleDao.getFavouriteByHref(href)
        return raffleData != null
    }

}