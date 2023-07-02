package com.works.kimkazandi.ui.RaffleDetail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableRow
import android.widget.TextView
import com.bumptech.glide.Glide
import com.works.kimkazandi.R
import com.works.kimkazandi.databinding.FragmentRaffleDetailBinding
import com.works.kimkazandi.models.RaffleData


class RaffleDetailFragment : Fragment() {

    private var _binding: FragmentRaffleDetailBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var viewModel: RaffleDetailViewModel
    private var raffle: RaffleData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            raffle = it.getParcelable("raffle")
            //Log.d("raffleDetail", raffle.toString())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel =
            ViewModelProvider(
                this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
            ).get(
                RaffleDetailViewModel::class.java
            )

        _binding = FragmentRaffleDetailBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Thread {
            viewModel.checkFavourite(raffle?.href)
        }.start()

        val detailImage = binding.detailImage

        val detailParagraph = binding.detailParagraph

        val tableLayout = binding.detailTable

        val selectFavouriteBtn = binding.selectFavouriteBtn



        Log.d("detailImg", raffle?.img.toString())

        Glide.with(this)
            .load(raffle?.img)
            .into(detailImage)


        detailParagraph.text = raffle?.paragraph // No need for checks


        val data = listOf(
            Pair("Başlangıç Tarihi :", raffle?.brandDescStartDate),
            Pair("Son Katılım Tarihi :", raffle?.brandDescLastJoinableDate),
            Pair("Çekiliş Tarihi :", raffle?.brandDescRaffleDate),
            Pair("İlan Tarihi :", raffle?.brandDescListingDate),
            Pair(
                "Min. Harcama Tutarı :",
                raffle?.price
            ), // Bu ana sayfadaki ile aynı veri o yüzden böyle isimlendirildi
            Pair(
                "Toplam Hediye Değeri :",
                raffle?.gift
            ), // Bu ana sayfadaki ile aynı veri o yüzden böyle isimlendirildi
            Pair("Toplam Hediye Sayısı :", raffle?.brandDescTotalCountGifts)
        )

        for (item in data) {
            val row = TableRow(requireContext())
            val labelTextView = TextView(requireContext())
            val valueTextView = TextView(requireContext())

            labelTextView.text = item.first
            valueTextView.text = item.second

            labelTextView.setTextAppearance(requireContext(), R.style.MyTableLabelStyle)
            valueTextView.setTextAppearance(requireContext(), R.style.MyTableValueStyle)

            row.addView(labelTextView)
            row.addView(valueTextView)

            tableLayout.addView(row)
        }


        viewModel.isFavourite.observe(viewLifecycleOwner, { isFavourite ->
            val favouriteDrawable = if (isFavourite) {
                R.drawable.ic_favourites
            } else {
                R.drawable.ic_unfavouritesbutton
            }
            selectFavouriteBtn.setBackgroundResource(favouriteDrawable)
        })

        selectFavouriteBtn.setOnClickListener {
            if (viewModel.isFavourite.value == true) {
                // Eğer favoride ise favoriden çıkar
                Thread {
                    viewModel.removeFavourite(raffle!!)
                }.start()
            } else {
                // Eğer favoride değilse favoriye ekle
                Thread {
                    viewModel.addFavourite(raffle!!)
                }.start()
            }
        }


    }
}

