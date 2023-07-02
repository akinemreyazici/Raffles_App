package com.works.kimkazandi.ui.TakipEttiklerim


import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.works.kimkazandi.models.RaffleData
import com.works.kimkazandi.room.configs.AppDatabase
import com.works.kimkazandi.room.repositories.FavouriteRafflesRoomRepositories

class TakipEttiklerimViewModel(application: Application) : AndroidViewModel(application) {

    private val db: AppDatabase = Room.databaseBuilder(
        application,
        AppDatabase::class.java,
        "appDatabase"
    ).build()

    private val favouriteRaffleDao = db.favouriteRaffleDao()
    private val favouriteRafflesRoomRepositories =
        FavouriteRafflesRoomRepositories(favouriteRaffleDao)

    private val _list = MutableLiveData<List<RaffleData>>()
    val list: LiveData<List<RaffleData>> get() = _list

    fun TakipEttiklerimfetchData() {
        Thread {
            val favouriteRaffles = favouriteRafflesRoomRepositories.getAllRaffles()
            val raffleList = favouriteRaffles.mapNotNull { favouriteRaffle ->
                favouriteRafflesRoomRepositories.getRaffle(favouriteRaffle.href)
            }
            _list.postValue(raffleList)
            Log.d("list", _list.value.toString())
        }.start()
    }
}