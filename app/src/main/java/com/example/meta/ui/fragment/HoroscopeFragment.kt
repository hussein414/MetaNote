package com.example.meta.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.meta.R
import com.example.meta.data.event.ItemClickListener
import com.example.meta.data.model.ItemModel
import com.example.meta.databinding.FragmentHoroscopeBinding
import com.example.meta.ui.adapter.ItemCardAdapter
import com.example.meta.utils.Constance

class HoroscopeFragment : Fragment(), ItemClickListener {


    private lateinit var binding: FragmentHoroscopeBinding
    private lateinit var mainAdapter: ItemCardAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentHoroscopeBinding.inflate(inflater, container, false)
        bindViews()
        return binding.root
    }

    private fun bindViews() {
        mainAdapter = ItemCardAdapter(Constance.scopeWigetData, this)
        binding.scopeRecyclerView.apply {
            adapter = mainAdapter
            layoutManager =
                GridLayoutManager(requireContext(), 3, GridLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
        }
    }

    override fun itemClick(model: ItemModel) {
        if (model.name == "جفت بودن ستاره") {
            findNavController().navigate(R.id.action_horoscopeFragment_to_homeFragment)
        } else {
            Toast.makeText(requireContext(), "ایکون جفت بودن ستاره و بزن", Toast.LENGTH_SHORT).show()
        }
    }

}