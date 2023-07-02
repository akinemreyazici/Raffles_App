package com.works.kimkazandi.ui.TelefonTabletKazan

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.works.kimkazandi.R
import com.works.kimkazandi.adapters.customRaffleListAdapter
import com.works.kimkazandi.databinding.FragmentRafflesBinding
import com.works.kimkazandi.models.RaffleData


class TelefonTabletKazanFragment : Fragment() {

    private var _binding: FragmentRafflesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    lateinit var telefonTabletKazanViewModel: TelefonTabletKazanViewModel
    lateinit var controller: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        telefonTabletKazanViewModel =
            ViewModelProvider(
                this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
            ).get(
                TelefonTabletKazanViewModel::class.java
            )

        _binding = FragmentRafflesBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Thread {
            telefonTabletKazanViewModel.TelefonTabletKazanfetchData()
        }.start()

        controller = Navigation.findNavController(view)

        val listView = binding.rafflesListView
        telefonTabletKazanViewModel.list.observe(viewLifecycleOwner) {
            val adapter = customRaffleListAdapter(this, it)
            listView.adapter = adapter
        }

        listView.setOnItemClickListener { adapterView, view, i, l ->
            val selectedRaffle = listView.getItemAtPosition(i) as RaffleData
            val bundle = Bundle()
            bundle.putParcelable("raffle", selectedRaffle)
            controller.navigate(R.id.action_nav_telefontabletkazan_to_raffleDetailFragment, bundle)

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}