package com.works.kimkazandi.ui.Cekilisler

import android.app.Application
import android.preference.PreferenceManager
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.works.kimkazandi.common.Url
import com.works.kimkazandi.jsoup.repositories.RaffleJsoupRepositories
import com.works.kimkazandi.models.RaffleData
import com.works.kimkazandi.room.configs.AppDatabase
import com.works.kimkazandi.room.repositories.FavouriteRafflesRoomRepositories
import com.works.kimkazandi.room.repositories.RafflesRoomRepositories

class CekilislerViewModel(application: Application) : AndroidViewModel(application) {

    private val db: AppDatabase = Room.databaseBuilder(
        application,
        AppDatabase::class.java,
        "appDatabase"
    ).build()

    private val raffleDao = db.raffleDao()
    private val favouriteRaffleDao = db.favouriteRaffleDao() // Dataları sıfırlamak için çağırıyorum
    private val rafflesRoomRepositories = RafflesRoomRepositories(raffleDao)
    private val favouriteRafflesRoomRepositories =
        FavouriteRafflesRoomRepositories(favouriteRaffleDao)
    private val rafflesJsoupRepositories = RaffleJsoupRepositories()

    private val _list = MutableLiveData<List<RaffleData>>()
    val list: LiveData<List<RaffleData>> get() = _list


    val currentTime = System.currentTimeMillis()
    val lastUpdateTime = PreferenceManager.getDefaultSharedPreferences(getApplication())
        .getLong("last_update_time", 0)
    val elapsedTime = currentTime - lastUpdateTime

    init {
        Log.e("Geçen süre", elapsedTime.toString())

    }

    fun CekilislerfetchData() {

        // -------------FINAL ODEVİ HAKKINDA BİLGİLENDİRME----------------------

        // Jsoup'tan çekerken hem detayı hem çekilişi aynı modelde dolduruyorum derken ondan dolayı yüklerken biraz geç yükleniyor.

        Thread {
            val isDbEmpty =
                rafflesRoomRepositories.isDatabaseEmpty() // Uygulama ilk yüklenildiğinde ve datalar silindiyse Jsoupdan çekiyoruz
            if (isDbEmpty) {
                val data = rafflesJsoupRepositories.ParseRaffles(Url.cekilisler)
                for (raffle in data) {
                    rafflesRoomRepositories.addRaffles(raffle)
                }
                _list.postValue(data)
                Log.d("İşlemler", "Veriler Jsoup'dan çekildi ve database'e kaydedildi.")
            } else {
                val data = rafflesRoomRepositories.getSelectedCategoryRaffles(Url.cekilislerSplit)
                _list.postValue(data)
                Log.d("İşlemler", "Veriler database'den çekildi.")

            }
        }.start()
    }


    fun CheckClearAndFetchData() {
        val currentTime = System.currentTimeMillis()
        val lastUpdateTime = PreferenceManager.getDefaultSharedPreferences(getApplication())
            .getLong("last_update_time", 0)
        val elapsedTime = currentTime - lastUpdateTime

        //Log.d("currentTime", currentTime.toString())
        //Log.d("lastUpdate", lastUpdateTime.toString())

        val threeHoursInMillis = 3 * 60 * 60 * 1000
        // val onSecondsInMillis = 10 * 1000

        if (elapsedTime >= threeHoursInMillis) {
            // 3 saat geçmiş, verileri sıfırla
            Thread {
                favouriteRafflesRoomRepositories.deleteAllFavourites()
                rafflesRoomRepositories.deleteAllRaffles()
                PreferenceManager.getDefaultSharedPreferences(getApplication()).edit()
                    .putLong("last_update_time", currentTime).apply()
                Log.d("İşlemler", "Veriler sıfırlandı.")
                CekilislerfetchData() // Sildikten sonra hemen tekrar verileri yüklüyoruz
            }.start()

        }
        else{
            CekilislerfetchData()
        // Bunu yapma sebebim uygulama ilk çalıştığında direk dataları silmeye çalıştığımızdan database sorun çıkarıyor
        // Bu ilk defa girerken ve 3 saat geçmemişken
        }
    }

}

