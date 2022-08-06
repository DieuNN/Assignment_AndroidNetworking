package com.dieunn.assignment_androidnetworking.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.dieunn.assignment_androidnetworking.ApiInstance
import com.dieunn.assignment_androidnetworking.R
import com.dieunn.assignment_androidnetworking.adapter.NewProductItemAdapter
import com.dieunn.assignment_androidnetworking.databinding.FragmentPhoneBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class PhoneFragment : Fragment() {
    private lateinit var binding : FragmentPhoneBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPhoneBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        requireActivity().actionBar?.title = "Điện thoại"

        GlobalScope.launch(Dispatchers.Main) {
            binding.recyclerView.apply {
                layoutManager = GridLayoutManager(requireContext(), 2)
                adapter = NewProductItemAdapter(ApiInstance.api.getAllProducts(), this@PhoneFragment, type = "phone")
            }
        }
    }
}