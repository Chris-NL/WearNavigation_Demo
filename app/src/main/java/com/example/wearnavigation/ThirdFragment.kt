package com.example.wearnavigation

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.wear.widget.ConfirmationOverlay
import com.example.wearnavigation.databinding.FragmentThirdBinding

class ThirdFragment : Fragment() {
    private val TAG = "ThirdFragment"

    private var _binding: FragmentThirdBinding? = null
    private val binding get() = _binding!!

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapterPeriods: ItemsRecyclerviewAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentThirdBinding.inflate(inflater, container, false)

        layoutManager = LinearLayoutManager(requireContext())

        val items = arrayOf("Just some item 1", "Just some item 2", "Just some item 3", "Just some item 4", "Just some item 5", "Just some item 6", "Just some item 7")

        binding.rvItems.layoutManager = layoutManager
        adapterPeriods = ItemsRecyclerviewAdapter(items)
        binding.rvItems.adapter = adapterPeriods

        return binding.root
    }
}