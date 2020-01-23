package com.example.dmiryz.ryzhov.shopproductlist.core

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dmiryz.ryzhov.shopproductlist.R
import com.example.dmiryz.ryzhov.shopproductlist.dataBase.Product
import com.example.dmiryz.ryzhov.shopproductlist.ui.product.ProductViewModel
import kotlinx.android.synthetic.main._item_product.view.*
import java.io.IOException
import java.io.InputStream


class ProductAdapter(val products: List<Product>, val context: Context, var contactViewModel: ProductViewModel):RecyclerView.Adapter<ProductAdapter.ProductHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        return ProductHolder(LayoutInflater.from(context).inflate(R.layout._item_product,parent,false))
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        val item = products[position]

        holder.productTitle.text = item.titleElement
        holder.checkBoxBought.isChecked = item.bought
        holder.iconProduct.setImageResource(R.drawable.baban)
        holder.costProduct.text = item.price
        holder.countProduct.text = item.count.toString()
        holder.unitsProduct.text = item.countWeight
        holder.commentToProducts.text = item.comment
    }

    override fun getItemCount(): Int  = products.size

    inner class ProductHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productTitle = itemView.title_product
        val checkBoxBought = itemView.checkBox
        val iconProduct = itemView.icon_product
        val costProduct = itemView.count_money
        val countProduct = itemView.count
        val unitsProduct = itemView.units_weight
        val commentToProducts = itemView.comment
    }

}