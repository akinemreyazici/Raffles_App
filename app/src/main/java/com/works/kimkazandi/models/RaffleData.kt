package com.works.kimkazandi.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "raffles")
data class RaffleData(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,

    // Raffle Kısmı

    val img: String?,
    val category: String?, // Bunu Room' a kayıt ederken kategori niyetiyle çekerken kullanmak için tutuyorum
    val href: String?, // Bu benim 2 tabloda da eşit olan değerim olacak bunun aracılığıyla bağlantıları kuruyorum
    val title: String?,
    val time: String?,
    val gift: String?,
    val price: String?,

    // Raffle Detail kısmı

    val paragraph: String?,
    val brandDescStartDate: String?,
    val brandDescLastJoinableDate: String?,
    val brandDescRaffleDate: String?,
    val brandDescListingDate: String?,
    //val brandDescMinimumPrice: String?, //  -> price bundan iki kere kaydetmektense yukardaki veriyle aynı olduğu için onu çekicem
    //val brandDescTotalGiftPrice: String?, // -> gift bundan iki kere kaydetmektense yukardaki veriyle aynı olduğu için onu çekicem
    val brandDescTotalCountGifts: String?
)

    : Parcelable


