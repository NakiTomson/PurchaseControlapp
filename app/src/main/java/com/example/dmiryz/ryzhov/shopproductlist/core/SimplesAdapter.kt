package com.example.dmiryz.ryzhov.shopproductlist.core

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.dmiryz.ryzhov.shopproductlist.R
import com.example.dmiryz.ryzhov.shopproductlist.dataBase.Product
import com.example.dmiryz.ryzhov.shopproductlist.dataBase.ProductCategory
import com.example.dmiryz.ryzhov.shopproductlist.ui.addProduct.AddListProductViewModel
import kotlinx.android.synthetic.main._item_selection_product.view.*
import java.io.IOException
import java.io.InputStream
import java.lang.Exception

class SimplesAdapter(
    val productsCategory: MutableList<ProductCategory>? = null,
    val context: Context,
    val products: List<Product>,
    var contactViewModel: AddListProductViewModel? = null,
    var flag: Int
) :
    RecyclerView.Adapter<SimplesAdapter.SimpleHolder>() {


    var countCurrent: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            SimpleHolder = SimpleHolder(
        LayoutInflater.from(context).inflate(
            R.layout._item_selection_product,
            parent,
            false
        )
    )


    override fun onBindViewHolder(holder: SimpleHolder, position: Int) {
        if (flag == 0) {
            val item = productsCategory!!.get(position)
            holder.imageAddProduct.setImageResource(item.icon)
            holder.titleProductAdd.text = item.title

            holder.rootLayoutItem.setOnClickListener {
                val product = products.filter { it.iconGroupProduct!! == item.category }
                contactViewModel!!.productsList = product.toMutableList()
                val navController = it.findNavController()
                navController.navigate(R.id.addListProductFragment)
            }
        } else if (flag == 1) {
            val item = products.get(position)
            holder.titleProductAdd.text = item.titleElement
            holder.rootLayoutItem.setOnClickListener {
                countCurrent = holder.countProduct.text.toString().toInt()
                countCurrent++
                holder.countProduct.text = countCurrent.toString()
                if (countCurrent >= 2) holder.iconProduct.setImageResource(R.drawable.ic_remove)
                val animation: Animation = AnimationUtils.loadAnimation(context, R.anim.rotate_anim)
                holder.imageAddProduct.animation = animation
                holder.imageAddProduct.setImageResource(R.drawable.ic_add_product_selected)
                holder.iconProduct.visibility = View.VISIBLE
            }
            holder.iconProduct.setOnClickListener {
                val animationRevers: Animation = AnimationUtils.loadAnimation(context, R.anim.rotate_anim_revers)
                holder.imageAddProduct.animation = animationRevers
                countCurrent = holder.countProduct.text.toString().toInt()
                countCurrent--
                if (countCurrent == 0) {
                    holder.iconProduct.visibility = View.GONE
                    holder.imageAddProduct.setImageResource(R.drawable.ic_add_product)
                    holder.countProduct.text = countCurrent.toString()
                } else if (countCurrent == 1) {
                    holder.iconProduct.setImageResource(R.drawable.ic_close)
                    holder.countProduct.text = countCurrent.toString()
                } else holder.countProduct.text = countCurrent.toString()
            }
        } else {
            throw Exception("flag is non")
        }
    }

    override fun getItemCount(): Int = productsCategory?.size ?: products.size


    inner class SimpleHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageAddProduct = itemView.imageAddProduct
        val titleProductAdd = itemView.titleProductAdd
        val iconProduct = itemView.imageViewCancelAdd
        val rootLayoutItem = itemView.add_product_root
        val countProduct = itemView.count_product
    }


}