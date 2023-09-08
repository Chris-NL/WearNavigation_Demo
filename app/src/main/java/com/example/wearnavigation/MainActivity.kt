package com.example.wearnavigation

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.*
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.navigation.findNavController
import androidx.wear.widget.drawer.WearableNavigationDrawerView
import com.example.wearnavigation.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity(), WearableNavigationDrawerView.OnItemSelectedListener{
    private val TAG = "MainActivity"

    private var _binding: ActivityMainBinding? = null
    val binding get() = _binding!!

    /**
     * This custom handler is used for updates in "Active" mode. We use a separate static class to
     * help us avoid memory leaks.
     */
    private var drawerItem_ArrayList: ArrayList<DrawerItem>? = null
    private var mSelectedScreen = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate()")
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        drawerItem_ArrayList = initializeDrawerItems()

        // Top Navigation Drawer
        binding.topNavigationDrawer.setAdapter(NavigationAdapter(this))
        // Peeks navigation drawer on the top.
        binding.topNavigationDrawer.controller.peekDrawer()
        binding.topNavigationDrawer.addOnItemSelectedListener(this)

        // Bottom Action Drawer
        // Peeks action drawer on the bottom.
        binding.bottomActionDrawer.controller.peekDrawer()
    }

    private fun initializeDrawerItems(): ArrayList<DrawerItem> {
        Log.d(TAG, "Test " + packageName)
        val screens: ArrayList<DrawerItem> = ArrayList<DrawerItem>()
        val screenArrayNames = resources.getStringArray(R.array.wear_draweritems_array_names)
        for (ScreenInfo in screenArrayNames) {
            val screenResourceId = resources.getIdentifier(ScreenInfo, "array", packageName)
            val fragmentInformation = resources.getStringArray(screenResourceId)
            screens.add(
                DrawerItem(
                    getString(
                        resources.getIdentifier(
                            fragmentInformation[0],
                            "string",
                            packageName
                        )
                    ),  // Name
                    fragmentInformation[1]
                )
            ) // Icon
        }
        return screens
    }

    // Updates content when user changes between items in the navigation drawer.
    override fun onItemSelected(position: Int) {
        Log.d(
            TAG,
            "WearableNavigationDrawerView triggered onItemSelected(): $position"
        )
        try {
            if (position == 0) {
                when (mSelectedScreen) {
                    1 -> {
                        findNavController(R.id.nav_host_fragment).navigate(R.id.action_secondFragment_to_firstFragment)
                        binding.bottomActionDrawer.setIsLocked(false)
                    }
                    2 -> {
                        findNavController(R.id.nav_host_fragment).navigate(R.id.action_thirdFragment_to_firstFragment)
                        binding.bottomActionDrawer.setIsLocked(false)
                    }
                }
            } else {
                binding.bottomActionDrawer.controller.closeDrawer()
                binding.bottomActionDrawer.setIsLocked(true)
            }
            if (position == 1) {
                when (mSelectedScreen) {
                    0 -> {
                        findNavController(R.id.nav_host_fragment).navigate(R.id.action_firstFragment_to_secondFragment)
                    }
                    2 -> {
                        findNavController(R.id.nav_host_fragment).navigate(R.id.action_thirdFragment_to_secondFragment)
                    }
                }
            }
            if (position == 2) {
                when (mSelectedScreen) {
                    1 -> {
                        findNavController(R.id.nav_host_fragment).navigate(R.id.action_secondFragment_to_thirdFragment)
                    }
                    0 -> {
                        findNavController(R.id.nav_host_fragment).navigate(R.id.action_secondFragment_to_firstFragment)
                    }
                }
            }
            mSelectedScreen = position
        } catch (exception: Exception) {
            mSelectedScreen = position
            binding.topNavigationDrawer.controller.closeDrawer()
        }
    }

    private inner class NavigationAdapter  /* package */ (private val mContext: Context) :
        WearableNavigationDrawerView.WearableNavigationDrawerAdapter() {
        override fun getCount(): Int {
            return drawerItem_ArrayList!!.size
        }

        override fun getItemText(pos: Int): String {
            return drawerItem_ArrayList!![pos].name
        }

        override fun getItemDrawable(pos: Int): Drawable {
            val navigationIcon: String = drawerItem_ArrayList!![pos].navigationIcon
            val drawableNavigationIconId = resources.getIdentifier(
                navigationIcon, "drawable",
                packageName
            )
            return AppCompatResources.getDrawable(mContext, drawableNavigationIconId)!!
        }
    }

}