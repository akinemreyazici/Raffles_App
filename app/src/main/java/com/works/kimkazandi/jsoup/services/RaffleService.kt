package com.works.kimkazandi.jsoup.services

import com.works.kimkazandi.models.RaffleData

interface RaffleService {

    fun ParseRaffles(url: String): List<RaffleData>

    fun trustAllCertificates()

    fun ParseRaffleDetail(RaffleData: RaffleData): RaffleData?
}