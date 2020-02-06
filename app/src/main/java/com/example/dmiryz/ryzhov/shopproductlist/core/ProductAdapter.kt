package com.example.dmiryz.ryzhov.shopproductlist.core

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.dmiryz.ryzhov.shopproductlist.R
import com.example.dmiryz.ryzhov.shopproductlist.app.Companion.showPrice
import com.example.dmiryz.ryzhov.shopproductlist.dataBase.Product
import com.example.dmiryz.ryzhov.shopproductlist.ui.product.ProductViewModel
import kotlinx.android.synthetic.main._item_product.view.*


class ProductAdapter() : RecyclerView.Adapter<ProductAdapter.ProductHolder>() {



    var products: List<Product> = mutableListOf()
    lateinit var context: Context
    lateinit var productViewModel: ProductViewModel

    fun setDate(products: List<Product>, context: Context, contactViewModel: ProductViewModel) {
        this.products = products
        this.context = context
        this.productViewModel = contactViewModel
    }

    fun getData(): List<Product> {
        return products
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

        if (showPrice){
            holder.iconProduct.setImageResource(R.drawable.rub_icon)
            holder.costProduct.text = item.price ?: 0.toString()
            holder.costProduct.visibility = View.VISIBLE
        }else{
            holder.iconProduct.setImageResource(item.icon!!)
            holder.costProduct.text = item.price
            holder.costProduct.visibility = View.GONE
        }


        holder.countProduct.text = item.count.toString()
        holder.unitsProduct.text = item.countWeight
        holder.commentToProducts.text = item.comment

        holder.checkBoxBought.setOnClickListener {
            item.bought = holder.checkBoxBought.isChecked
            productViewModel.updateProduct(item)
        }
        if (holder.checkBoxBought.isChecked) {
            holder.root.alpha = 0.7F
//            holder.root.backgroundTintMode =
        } else {
            holder.root.alpha = 1F
        }

        holder.root.setOnLongClickListener {
            productViewModel.productForEdit = item
            val navController = it.findNavController()
            navController.navigate(R.id.action_productFragment_to_editFragment)
            true
        }
    }

    override fun getItemCount(): Int = products.size ?: 0

    fun setConfig() {
        showPrice = !showPrice
        notifyDataSetChanged()
    }

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