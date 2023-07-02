package com.works.kimkazandi.ui.RaffleDetail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.works.kimkazandi.models.FavouriteRaffleData
import com.works.kimkazandi.models.RaffleData
import com.works.kimkazandi.room.configs.AppDatabase
import com.works.kimkazandi.room.repositories.FavouriteRafflesRoomRepositories


class RaffleDetailViewModel(application: Application) : AndroidViewModel(application) {

    private val db: AppDatabase = Room.databaseBuilder(
        application,
        AppDatabase::class.java,
        "appDatabase"
    ).build()

    private val favouriteRaffleDao = db.favouriteRaffleDao()
    private val favouritesRepositories = FavouriteRafflesRoomRepositories(favouriteRaffleDao)
    val isFavourite: MutableLiveData<Boolean> = MutableLiveData()


    fun checkFavourite(href: String?) {
        val isFav = favouritesRepositories.isExist(href)
        isFavourite.postValue(isFav)
    }

    fun addFavourite(raffle: RaffleData) {
        Thread {
            favouritesRepositories.addFavourite(FavouriteRaffleData(null, raffle.href))
            checkFavourite(raffle.href)

        }.start()
    }

    fun removeFavourite(raffle: RaffleData) {
        Thread {
            val favouriteRaffleData = favouritesRepositories.getFavouriteByHref(raffle.href)
            if (favouriteRaffleData != null) {
                favouritesRepositories.deleteFavourite(favouriteRaffleData)
                checkFavourite(raffle.href)
            }
        }.start()
    }


}
