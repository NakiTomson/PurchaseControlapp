package com.example.dmiryz.ryzhov.shopproductlist.core

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.dmiryz.ryzhov.shopproductlist.R
import com.example.dmiryz.ryzhov.shopproductlist.dataBase.ProductGroup
import com.example.dmiryz.ryzhov.shopproductlist.dataBase.ProductGroupWithProducts
import com.example.dmiryz.ryzhov.shopproductlist.ui.home.HomeViewModel
import com.example.dmiryz.ryzhov.shopproductlist.ui.product.ProductViewModel
import kotlinx.android.synthetic.main._item_products_group.view.*

class MyListHomeAdapter(
    val items: List<ProductGroupWithProducts>,
    val context: Context,
    var contactViewModel: ProductViewModel,
    var homeViewModel: HomeViewModel
) : RecyclerView.Adapter<MyListHomeAdapter.MyListHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyListHolder {
        return MyListHolder(
            LayoutInflater.from(context).inflate(
                R.layout._item_products_group,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyListHolder, position: Int) {
        val groupWithProduct = items[position]
        val product = groupWithProduct.productGroup

        holder.productType.text = product?.productType
        holder.shoppingProgress.progress = product?.shoppingProgress!!
        holder.countNeedToBuy.text = product.countNeedBuy.toString()
        holder.countBuyed.text = product.countBuy.toString()


        holder.rootLayout.setOnClickListener {
            contactViewModel.productGroupWithProducts = groupWithProduct
            contactViewModel.positionGroup = position
            val navController = it.findNavController()
            navController.navigate(R.id.action_nav_home_to_productFragment)
        }

        holder.menuIcon.setOnClickListener { v ->
            showPopupmenu(v, product)
        }


    }

    private fun showPopupmenu(v: View, item: ProductGroup) {
        val popupMenu = PopupMenu(context, v)
        popupMenu.inflate(R.menu.product_popup_menu)
        popupMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_edit -> {
                    true
                }
                R.id.action_delete -> {
                    homeViewModel.delete(product = item)
                    true
                }
                R.id.action_Share -> {

                    true
                }
                R.id.action_Copy -> {
                    true
                }
                else -> false
            }
        }
        popupMenu.show()
    }


    override fun getItemCount(): Int = items.size

    inner class MyListHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val productType = itemView.product_type
        val shoppingProgress = itemView.shopping_progress
        val countNeedToBuy = itemView.count_need_to_by
        val countBuyed = itemView.count_buyed
        val rootLayout = itemView.card_group
        val menuIcon = itemView.menu_icon
    }
}