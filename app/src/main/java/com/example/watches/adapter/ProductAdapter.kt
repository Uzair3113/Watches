package com.example.watches.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.watches.R
import com.example.watches.models.Product

class ProductAdapter(
    private var products: List<Product>,
    private val onItemClick: (Product) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val productImageView: ImageView = view.findViewById(R.id.imgWatch)
        val productNameTextView: TextView = view.findViewById(R.id.txtWatchName)
        val productSeriesTextView: TextView = view.findViewById(R.id.txtWatchSeries)
        val productPriceTextView: TextView = view.findViewById(R.id.txtPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recview_watches, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]

        Glide.with(holder.itemView.context).load(product.imageUrl).into(holder.productImageView)
        holder.productNameTextView.text = product.name
        holder.productSeriesTextView.text = product.category
        holder.productPriceTextView.text = "$${product.price}"

        holder.itemView.setOnClickListener {
            onItemClick(product)
        }
    }

    override fun getItemCount(): Int {
        return products.size
    }

    fun updateProducts(newProducts: List<Product>) {

        products = newProducts
        notifyDataSetChanged()
    }
}
