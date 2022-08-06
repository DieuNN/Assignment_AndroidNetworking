package com.dieunn.assignment_androidnetworking.fragment

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.dieunn.assignment_androidnetworking.ApiInstance
import com.dieunn.assignment_androidnetworking.R
import com.dieunn.assignment_androidnetworking.UserInfoInstance
import com.dieunn.assignment_androidnetworking.adapter.CartAdapter
import com.dieunn.assignment_androidnetworking.databinding.FragmentCartBinding
import com.dieunn.assignment_androidnetworking.model.UserCartRetrieved
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.log


class CartFragment : Fragment() {
    private lateinit var binding: FragmentCartBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCartBinding.inflate(inflater)
        return binding.root
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.cartListItem.apply {
            layoutManager = LinearLayoutManager(requireContext())
            ApiInstance.api.getUserCart(UserInfoInstance.username)
                .enqueue(object : Callback<UserCartRetrieved> {
                    override fun onResponse(
                        call: Call<UserCartRetrieved>,
                        response: Response<UserCartRetrieved>
                    ) {
//                        Log.d(TAG, "onResponse: ${response.body()}")
                        val list: ArrayList<String> = ArrayList()

                        for (item in response.body()?.get(0)?.products!!) {
                            list.add(item.productId)
                            GlobalScope.launch(Dispatchers.Main) {
                                val product = ApiInstance.api.getProductById(item.productId)
                                val regex = Regex("[^A-Za-z0-9]")
                                val result = regex.replace( product[0].price, "")
                                val priceInInt = result.toInt()
                                UserInfoInstance.totalPrice += priceInInt;
                                binding.txtTotal.text ="Tổng tiền: "+ UserInfoInstance.totalPrice.toString()
                                Log.d(TAG, "onResponse: ${UserInfoInstance.totalPrice}")
                            }

                        }

                        Log.d(TAG, "onResponse: $list")
                        adapter = CartAdapter(list)
                    }

                    override fun onFailure(call: Call<UserCartRetrieved>, t: Throwable) {
                        throw t
                    }

                })


//                for (product in productIds[0].products) {
//                    list.add(product.productId)
//                }
//                adapter = CartAdapter(list)

        }
    }


}