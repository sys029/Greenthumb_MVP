package com.example.greenthumb_tester

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_product.view.*

class ProductAdapter(private val dataList: MutableList<ProductData>) : RecyclerView.Adapter<ProductHolder>(){

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        context = parent.context
        return ProductHolder(LayoutInflater.from(context).inflate(R.layout.item_product,parent,false))
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        val data = dataList[position]

        val productName = holder.itemView.product_title_id
        val productImage = holder.itemView.product_img_id
        val productPrice = holder.itemView.product_price_id

        val ItemName= data.product_name
        productName.text = ItemName
        val ItemPrice = data.price
        productPrice.text = "₹"+ItemPrice

        Picasso.get()
            .load(data.image_url)
            .into(productImage)

        holder.itemView.setOnClickListener {
            Toast.makeText(context,ItemName, Toast.LENGTH_SHORT).show()
        }
    }
}