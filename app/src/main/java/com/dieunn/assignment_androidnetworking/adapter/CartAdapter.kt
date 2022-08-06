package com.dieunn.assignment_androidnetworking.adapter

import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dieunn.assignment_androidnetworking.ApiInstance
import com.dieunn.assignment_androidnetworking.databinding.CartItemBinding
import com.dieunn.assignment_androidnetworking.model.ProductsRetrieved
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CartAdapter(val list : ArrayList<String>) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {
    class ViewHolder(val binding : CartItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(CartItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(list[position]) {
                GlobalScope.launch(Dispatchers.Main) {
                    val product = ApiInstance.api.getProductById(this@with)
                    Log.d(TAG, "onBindViewHolder: $product")
                    binding.productDesName.text = product[0].name
                    binding.productDesPrice.text = product[0].price
                    Picasso.get().load(product[0].imageLink).into(binding.productDesImg)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return list.count()
    }
}