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
import com.dieunn.assignment_androidnetworking.databinding.FragmentAllProductBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem


class AllProductFragment : Fragment() {
    private lateinit var binding : FragmentAllProductBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAllProductBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.carousel.registerLifecycle(lifecycle)
        val dataList = mutableListOf<CarouselItem>().apply {
            add(
                CarouselItem(
                    imageUrl = "https://cdn2.cellphones.com.vn/690x300/https://dashboard.cellphones.com.vn/storage/690-300-air%20m2.png",
                )
            )
            add(
                CarouselItem(
                    imageUrl = "https://cdn2.cellphones.com.vn/690x300/https://dashboard.cellphones.com.vn/storage/poco%20f4.png",
                )
            )
            add(
                CarouselItem(
                    imageUrl = "https://cdn2.cellphones.com.vn/690x300/https://dashboard.cellphones.com.vn/storage/banner-huawei-tbvp.png",
                )
            )
            add(
                CarouselItem(
                    imageUrl = "https://cdn2.cellphones.com.vn/690x300/https://dashboard.cellphones.com.vn/storage/rex%202.png",
                )
            )
        }

        binding.carousel.setData(dataList)

        GlobalScope.launch(Dispatchers.Main) {
            binding.listViewAllProduct.apply {
                layoutManager = GridLayoutManager(requireContext(), 2)
                adapter = NewProductItemAdapter(ApiInstance.api.getAllProducts(), this@AllProductFragment, type = "")
            }
        }

    }
}