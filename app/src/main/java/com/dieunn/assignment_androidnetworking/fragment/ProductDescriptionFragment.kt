package com.dieunn.assignment_androidnetworking.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.dieunn.assignment_androidnetworking.ApiInstance
import com.dieunn.assignment_androidnetworking.R
import com.dieunn.assignment_androidnetworking.UserInfoInstance
import com.dieunn.assignment_androidnetworking.databinding.FragmentProductDescriptionBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


@DelicateCoroutinesApi
class ProductDescriptionFragment : Fragment() {
    private lateinit var binding: FragmentProductDescriptionBinding
    private lateinit var bundle: Bundle
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductDescriptionBinding.inflate(inflater)
        bundle = requireArguments()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBack.setOnClickListener {
            requireActivity().onBackPressed()
        }

        binding.btnCart.setOnClickListener {
            findNavController().navigate(R.id.cartFragment)
        }

        binding.btnAddToCart.setOnClickListener {
            if (binding.txtAmount.text.toString().toInt() <= 0) {
                return@setOnClickListener
            }
            GlobalScope.launch {
                ApiInstance.api.addToCart(UserInfoInstance.username, bundle.getString("id")!!)
            }
        }




        GlobalScope.launch(Dispatchers.Main) {
            val product = ApiInstance.api.getProductById(bundle.getString("id")!!)
            binding.productDesName.text = product[0].name
            binding.productDesPrice.text = product[0].price
            binding.productDesDes.text = product[0].description
            Picasso.get().load(product[0].imageLink).into(binding.productDesImg)
        }
    }
}