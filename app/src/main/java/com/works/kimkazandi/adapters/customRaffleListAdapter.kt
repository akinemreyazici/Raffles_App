package com.works.kimkazandi.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.works.kimkazandi.R
import com.works.kimkazandi.models.RaffleData

class customRaffleListAdapter(
    private val fragment: Fragment,
    private val list: List<RaffleData>
) : ArrayAdapter<RaffleData>(fragment.requireContext(), R.layout.custom_raffles_list, list) {
    private var ListView: ListView? = null

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val root = fragment.layoutInflater.inflate(R.layout.custom_raffles_list, null, true)

        val raffleTitle = root.findViewById<TextView>(R.id.r_title)
        val raffleTime = root.findViewById<TextView>(R.id.r_time)
        val raffleGift = root.findViewById<TextView>(R.id.r_gift)
        val rafflePrice = root.findViewById<TextView>(R.id.r_price)
        val raffleImage = root.findViewById<ImageView>(R.id.r_img)

        val raffle = list.get(position)
        raffleTitle.text = raffle.title
        raffleTime.text = raffle.time
        raffleGift.text = raffle.gift
        rafflePrice.text = raffle.price
        Glide.with(fragment.requireContext())
            .load(raffle.img)
            .into(raffleImage)

        return root


    }

    fun makeListView(listView: ListView) {
        ListView = listView
        notifyDataSetChanged()
    }
}