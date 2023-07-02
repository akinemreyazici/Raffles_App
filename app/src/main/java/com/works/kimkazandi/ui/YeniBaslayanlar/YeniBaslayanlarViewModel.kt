package com.works.kimkazandi.ui.YeniBaslayanlar

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.works.kimkazandi.common.Url
import com.works.kimkazandi.jsoup.repositories.RaffleJsoupRepositories
import com.works.kimkazandi.models.RaffleData
import com.works.kimkazandi.room.configs.AppDatabase
import com.works.kimkazandi.room.repositories.RafflesRoomRepositories

class YeniBaslayanlarViewModel(application: Application) : AndroidViewModel(application) {

    private val db: AppDatabase = Room.databaseBuilder(
        application,
        AppDatabase::class.java,
        "appDatabase"
    ).build()

    private val raffleDao = db.raffleDao()
    private val rafflesRoomRepositories = RafflesRoomRepositories(raffleDao)
    private val rafflesJsoupRepositories = RaffleJsoupRepositories()

    private val _list = MutableLiveData<List<RaffleData>>()
    val list: LiveData<List<RaffleData>> get() = _list


    fun YeniBaslayanlarfetchData() {
        Thread {
            val isDbEmpty =
                rafflesRoomRepositories.isSelectedCategoryEmpty(Url.yeniBaslayanlarSplit) // Uygulama ilk yüklenildiğinde Jsoupdan çekiyoruz
            if (isDbEmpty) {
                val data = rafflesJsoupRepositories.ParseRaffles(Url.yeniBaslayanlar)
                for (raffle in data) {
                    rafflesRoomRepositories.addRaffles(raffle)
                }
                _list.postValue(data)
                Log.d("İşlemler", "Veriler Jsoup'dan çekildi ve database'e kaydedildi.")
            } else {
                val data =
                    rafflesRoomRepositories.getSelectedCategoryRaffles(Url.yeniBaslayanlarSplit)
                _list.postValue(data)
                Log.d("İşlemler", "Veriler database'den çekildi.")

            }
        }.start()
    }
}