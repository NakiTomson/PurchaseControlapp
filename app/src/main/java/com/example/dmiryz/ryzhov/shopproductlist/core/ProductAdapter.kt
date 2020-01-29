package com.example.dmiryz.ryzhov.shopproductlist.core

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
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


class ProductAdapter() : RecyclerView.Adapter<ProductAdapter.ProductHolder>() {

    lateinit var products: List<Product>
    lateinit var context: Context
    lateinit var contactViewModel: ProductViewModel

    fun setDate(products: List<Product>, context: Context, contactViewModel: ProductViewModel) {
        this.products = products
        this.context = context
        this.contactViewModel = contactViewModel
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        return ProductHolder(
            LayoutInflater.from(context).inflate(
                R.layout._item_product,
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        val item = products[position]
        holder.productTitle.text = item.titleElement
        holder.checkBoxBought.isChecked = item.bought
        holder.iconProduct.setImageResource(item.icon!!)
        holder.costProduct.text = item.price
        holder.countProduct.text = item.count.toString()
        holder.unitsProduct.text = item.countWeight
        holder.commentToProducts.text = item.comment
        holder.checkBoxBought.setOnClickListener {
            item.bought = holder.checkBoxBought.isChecked
            contactViewModel.updateProduct(item)
        }
        if (holder.checkBoxBought.isChecked){
            holder.root.alpha = 0.7F
//            holder.root.backgroundTintMode =
        }else{
            holder.root.alpha = 1F
        }
    }

    override fun getItemCount(): Int = products.size

    inner class ProductHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val root = itemView.root_product
        val productTitle = itemView.title_product
        val checkBoxBought = itemView.checkBox
        val iconProduct = itemView.icon_product
        val costProduct = itemView.count_money
        val countProduct = itemView.count
        val unitsProduct = itemView.units_weight
        val commentToProducts = itemView.comment
    }
}