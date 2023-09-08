package com.example.wearnavigation

import com.example.wearnavigation.databinding.FragmentSecondBinding
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment

class SecondFragment : Fragment() {
    private val TAG = "SecondFragment"

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)

        return binding.root
    }
}