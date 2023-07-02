package com.works.kimkazandi.jsoup.repositories

import com.works.kimkazandi.jsoup.services.RaffleService
import com.works.kimkazandi.models.RaffleData
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.HttpsURLConnection
import javax.net.ssl.SSLContext
import javax.net.ssl.X509TrustManager

class RaffleJsoupRepositories : RaffleService {

    override fun trustAllCertificates() {
        val trustAllCerts = arrayOf<X509TrustManager>(object : X509TrustManager {
            override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
            override fun checkClientTrusted(certs: Array<X509Certificate>, authType: String) {}
            override fun checkServerTrusted(certs: Array<X509Certificate>, authType: String) {}
        })

        val sc = SSLContext.getInstance("SSL")
        sc.init(null, trustAllCerts, SecureRandom())
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.socketFactory)

        val allHostsValid = HostnameVerifier { _, _ -> true }
        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid)
    }

    override fun ParseRaffles(url: String): List<RaffleData> {
        trustAllCertificates()  // Call this only in development environments!

        val arr = mutableListOf<RaffleData>()
        val document: Document = Jsoup.connect(url).timeout(15000).get()
        val desiredDiv = document.selectFirst("div.col-lg-12.col-sm-12.campDesc")
        val elements = desiredDiv?.select("div.col-sm-3.col-lg-3.item")

        if (elements != null) {

            for (item in elements) {


                val img = item.selectFirst(".img img")?.attr("abs:src")
                val href = item.selectFirst(".img a")?.attr("abs:href")
                val title = item.selectFirst("h4")?.text()
                val time = item.selectFirst(".title .date > i.icon-time")?.parent()?.text()
                val gift = item.selectFirst(".title .date > i.icon-gift")?.parent()?.text()
                val price = item.selectFirst(".title .date > i.icon-price")?.parent()?.text()

                val lastSegment = url.split("/").last() // URL'nin son kısmını al

                val raffle = RaffleData(
                    null,
                    img,
                    lastSegment,
                    href,
                    title,
                    time,
                    gift,
                    price,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
                )
                val completeRaffle = ParseRaffleDetail(raffle)
                arr.add(completeRaffle!!)

                // Önce anasayfadaki verileri modelimde tutup diğerleri null ile tutuyorum
                // bitirmeden önce detay sayfasındaki verileri href ile giderek  kaydedip tamamlanmış halini kaydediyorum
            }
        }
        return arr
    }

    override fun ParseRaffleDetail(RaffleData: RaffleData): RaffleData? {
        trustAllCertificates()
        return try {
            val doc: Document = Jsoup.connect(RaffleData.href).get()
            val containerDivs = doc.select("div.container-fluid")

            if (containerDivs.size > 1) {
                val secondContainer = containerDivs[1]
                val img = secondContainer.select("img").attr("abs:src")

                var paragraph = "" // Changed from ArrayList<String>

                val campDescElements = doc.select("div.campDesc div.scrollbar-dynamic p")
                for (element in campDescElements) {
                    paragraph += element.text() + "\n" + "\n" // Add paragraphs to the string with a new line after each
                }

                var brandDescStartDate: String? = null
                var brandDescLastJoinableDate: String? = null
                var brandDescRaffleDate: String? = null
                var brandDescListingDate: String? = null
                //var brandDescMinimumPrice: String? = null
                //var brandDescTotalGiftPrice: String? = null
                var brandDescTotalCountGifts: String? = null

                val itemElements = doc.select("div.kalanSure")
                for (itemElement in itemElements) {
                    val label: String = itemElement.select("label").text()
                    val value: String = itemElement.text().replace(label, "").trim()

                    when (label) {
                        "Başlangıç Tarihi :" -> brandDescStartDate = value
                        "Son Katılım Tarihi :" -> brandDescLastJoinableDate = value
                        "Çekiliş Tarihi :" -> brandDescRaffleDate = value
                        "İlan Tarihi :" -> brandDescListingDate = value
                        //"Min. Harcama Tutarı :" -> brandDescMinimumPrice = value Çekebilirdim fakat zaten anasafyada aynı veri var olduğundan çekmedim
                        //"Toplam Hediye Değeri :" -> brandDescTotalGiftPrice = value Çekebilirdim fakat zaten anasayfada aynı veri var olduğundan çekmedim
                        "Toplam Hediye Sayısı :" -> brandDescTotalCountGifts = value
                    }
                }

                return RaffleData(
                    RaffleData.id,
                    RaffleData.img,
                    RaffleData.category,
                    RaffleData.href,
                    RaffleData.title,
                    RaffleData.time,
                    RaffleData.gift,
                    RaffleData.price,
                    paragraph.trim(),
                    brandDescStartDate,
                    brandDescLastJoinableDate,
                    brandDescRaffleDate,
                    brandDescListingDate,
                    brandDescTotalCountGifts,
                )
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }
}