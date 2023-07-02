package com.works.kimkazandi.ui.TakipEttiklerim

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.works.kimkazandi.R
import com.works.kimkazandi.adapters.customRaffleListAdapter
import com.works.kimkazandi.databinding.FragmentRafflesBinding
import com.works.kimkazandi.models.RaffleData


class TakipEttiklerimFragment : Fragment() {

    private var _binding: FragmentRafflesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var controller: NavController
    lateinit var takipEttiklerimViewModel: TakipEttiklerimViewModel
    lateinit var adapter: customRaffleListAdapter
    lateinit var listView: ListView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        takipEttiklerimViewModel =
            ViewModelProvider(
                this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
            ).get(TakipEttiklerimViewModel::class.java)

        _binding = FragmentRafflesBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        controller = Navigation.findNavController(view)

        Thread {
            takipEttiklerimViewModel.TakipEttiklerimfetchData()
        }.start()

        listView = binding.rafflesListView
        takipEttiklerimViewModel.list.observe(viewLifecycleOwner) {
            adapter = customRaffleListAdapter(this, it)
            adapter.makeListView(listView)
            Log.d("favoriler", it.toString())
            listView.adapter = adapter
        }


        listView.setOnItemClickListener { adapterView, view, i, l ->
            val selectedRaffle = listView.getItemAtPosition(i) as RaffleData
            val bundle = Bundle()
            bundle.putParcelable("raffle", selectedRaffle)
            controller.navigate(R.id.action_nav_takipettiklerim_to_raffleDetailFragment, bundle)

        }
    }

    override fun onResume() {
        super.onResume()
        takipEttiklerimViewModel.TakipEttiklerimfetchData()
        adapter.makeListView(listView)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}