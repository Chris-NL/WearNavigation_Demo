package com.example.wearnavigation

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.wear.widget.ConfirmationOverlay
import com.example.wearnavigation.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {
    private val TAG = "FirstFragment"

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!
    private lateinit var mainAct: MainActivity

    private val bottomActionDrawerMenuListener =
        MenuItem.OnMenuItemClickListener { menuItem: MenuItem ->
            Log.d(TAG, "onMenuItemClick(): $menuItem")

            val itemId = menuItem.itemId
            if (itemId == R.id.action_menu_action_one) {
                ConfirmationOverlay()
                    .setType(ConfirmationOverlay.SUCCESS_ANIMATION)
                    .setDuration(1500)
                    .setMessage("Action One")
                    .showOn(requireActivity())
            } else {
                ConfirmationOverlay()
                    .setType(ConfirmationOverlay.FAILURE_ANIMATION)
                    .setDuration(1500)
                    .setMessage("Action Two")
                    .showOn(requireActivity())
            }

            mainAct.binding.bottomActionDrawer.controller.closeDrawer()
            return@OnMenuItemClickListener false
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        mainAct = activity as MainActivity

        return binding.root
    }

    override fun onResume() {
        super.onResume()

        mainAct.binding.bottomActionDrawer.setOnMenuItemClickListener(bottomActionDrawerMenuListener)

    }
}