package com.dieunn.assignment_androidnetworking.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.dieunn.assignment_androidnetworking.R
import com.dieunn.assignment_androidnetworking.databinding.NewProductItemBinding
import com.dieunn.assignment_androidnetworking.model.ProductsRetrieved
import com.dieunn.assignment_androidnetworking.model.ProductsRetrievedItem
import com.squareup.picasso.Picasso

class NewProductItemAdapter(
    private var items: ProductsRetrieved,
    private val fragment: Fragment,
    private val type: String
) : RecyclerView.Adapter<NewProductItemAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: NewProductItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: NewProductItemBinding =
            NewProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        with(holder) {
            if (type.isNotBlank()) {
                with(items.filter { it.type == type }[position]) {
                    Picasso.get().load(this.imageLink).into(binding.newProductItemImg)
                    binding.newProductItemName.text = this.name
                    binding.newProductItemPrice.text = this.price

                    binding.newProductItem.setOnClickListener {
                        val bundle: Bundle = Bundle()
                        bundle.putString("id", this._id)
                        fragment.findNavController()
                            .navigate(R.id.productDescriptionFragment, bundle)
                    }
                }
            } else {
                with(items[position]) {
                    Picasso.get().load(this.imageLink).into(binding.newProductItemImg)
                    binding.newProductItemName.text = this.name
                    binding.newProductItemPrice.text = this.price

                    binding.newProductItem.setOnClickListener {
                        val bundle: Bundle = Bundle()
                        bundle.putString("id", this._id)
                        fragment.findNavController()
                            .navigate(R.id.productDescriptionFragment, bundle)
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return if (type.isNotBlank()) items.filter { it.type == type }.count() else items.count()
    }
}