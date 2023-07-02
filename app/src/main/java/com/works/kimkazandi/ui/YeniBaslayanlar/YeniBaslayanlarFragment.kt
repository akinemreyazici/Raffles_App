package com.works.kimkazandi.ui.YeniBaslayanlar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.works.kimkazandi.R
import com.works.kimkazandi.adapters.customRaffleListAdapter
import com.works.kimkazandi.databinding.FragmentRafflesBinding
import com.works.kimkazandi.models.RaffleData


class YeniBaslayanlarFragment : Fragment() {

    private var _binding: FragmentRafflesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    lateinit var yeniBaslayanlarViewModel: YeniBaslayanlarViewModel
    lateinit var controller: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        yeniBaslayanlarViewModel =
            ViewModelProvider(
                this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
            ).get(
                YeniBaslayanlarViewModel::class.java
            )

        _binding = FragmentRafflesBinding.inflate(inflater, container, false)
        val root: View = binding.root



        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Thread {
            yeniBaslayanlarViewModel.YeniBaslayanlarfetchData()
        }.start()

        controller = Navigation.findNavController(view)

        val listView = binding.rafflesListView
        yeniBaslayanlarViewModel.list.observe(viewLifecycleOwner) {
            val adapter = customRaffleListAdapter(this, it)
            listView.adapter = adapter
        }

        listView.setOnItemClickListener { adapterView, view, i, l ->
            val selectedRaffle = listView.getItemAtPosition(i) as RaffleData
            val bundle = Bundle()
            bundle.putParcelable("raffle", selectedRaffle)
            controller.navigate(R.id.action_nav_yenibaslayanlar_to_raffleDetailFragment, bundle)

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}