package com.dieunn.assignment_androidnetworking.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.navigation.fragment.findNavController
import com.dieunn.assignment_androidnetworking.R
import com.dieunn.assignment_androidnetworking.UserInfoInstance
import com.dieunn.assignment_androidnetworking.databinding.FragmentHomeBinding
import com.google.android.material.navigation.NavigationView

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().apply {
            actionBar?.title = "Tech Shop"
        }
        val toggle = ActionBarDrawerToggle(
            requireActivity(),
            binding.drawerLayout,
            binding.toolbarHome,
            0,
            0
        )
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        if (savedInstanceState == null) {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(binding.fragmentContainerHome.id, AllProductFragment()).commit()
        }

        binding.btnCart.setOnClickListener {
            findNavController().navigate(R.id.cartFragment)
        }

        binding.navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_all_products -> requireActivity().supportFragmentManager.beginTransaction()
                    .replace(binding.fragmentContainerHome.id, AllProductFragment()).commit()
                R.id.menu_phone -> requireActivity().supportFragmentManager.beginTransaction()
                    .replace(binding.fragmentContainerHome.id, PhoneFragment()).commit()
                R.id.menu_laptop -> requireActivity().supportFragmentManager.beginTransaction()
                    .replace(binding.fragmentContainerHome.id, LaptopFragment()).commit()
                R.id.menu_tablet -> requireActivity().supportFragmentManager.beginTransaction()
                    .replace(binding.fragmentContainerHome.id, TabletFragment()).commit()
                R.id.menu_smartwatch -> requireActivity().supportFragmentManager.beginTransaction()
                    .replace(binding.fragmentContainerHome.id, SmartWatchFragment()).commit()
                R.id.menu_info -> requireActivity().supportFragmentManager.beginTransaction()
                    .replace(binding.fragmentContainerHome.id, InfoFragment()).commit()
                R.id.menu_logout -> {
                    UserInfoInstance.username = ""
                    findNavController().popBackStack(R.id.loginFragment, true)

                }
                R.id.menu_exit -> {
                    requireActivity().finishAffinity()
                }
            }
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            true

        }
    }


}