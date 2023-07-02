package com.works.kimkazandi.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite_raffles")
data class FavouriteRaffleData(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,

    val href: String?, // Bu benim 2 tabloda da eşit olan değerim olacak bunun aracılığıyla bağlantıları kuruyorum
)

