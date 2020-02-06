package com.example.dmiryz.ryzhov.shopproductlist.core

import androidx.recyclerview.widget.DiffUtil
import com.example.dmiryz.ryzhov.shopproductlist.dataBase.Product


class ProductDiffUtilCallback(
    private val oldList: List<Product>,
    private val newList: List<Product>
) : DiffUtil.Callback() {


    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldProduct = oldList[oldItemPosition]
        val newProduct = newList[newItemPosition]
        return oldProduct.id === newProduct.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldProduct = oldList[oldItemPosition]
        val newProduct = newList[newItemPosition]
        return (oldProduct.titleElement.equals(newProduct.titleElement) && oldProduct.count == newProduct.count && oldProduct.bought.equals(newProduct.bought) &&
                oldProduct.comment.equals(newProduct.comment) && oldProduct.price.equals(newProduct.price))
    }


}