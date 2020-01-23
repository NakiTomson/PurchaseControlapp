package com.example.dmiryz.ryzhov.shopproductlist.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.size
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dmiryz.ryzhov.shopproductlist.ui.MainActivity
import com.example.dmiryz.ryzhov.shopproductlist.core.MyListHomeAdapter
import com.example.dmiryz.ryzhov.shopproductlist.dataBase.ProductGroup
import com.example.dmiryz.ryzhov.shopproductlist.R
import com.example.dmiryz.ryzhov.shopproductlist.dataBase.ProductGroupWithProducts
import com.example.dmiryz.ryzhov.shopproductlist.ui.product.ProductViewModel
import kotlinx.android.synthetic.main.add_product_group_dialog.view.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.fragment_home_list.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        recycler_list_home.layoutManager = LinearLayoutManager(context)
        homeViewModel.getProducts()

        homeViewModel.products.observe(this, Observer<List<ProductGroupWithProducts>> {
            val mainActivity = activity as MainActivity
            val addConViewModel = ViewModelProviders.of(mainActivity).get(ProductViewModel::class.java)
            recycler_list_home.adapter = MyListHomeAdapter(it, mainActivity,addConViewModel,homeViewModel)

            if (it.isEmpty()) {
                imagePosterTodo.visibility = View.VISIBLE
                textHint.visibility = View.VISIBLE
            } else {
                imagePosterTodo.visibility = View.INVISIBLE
                textHint.visibility = View.INVISIBLE
            }
        })

        activity?.fab?.apply {
            setOnClickListener {
                createdAndShowAddProductDialog()
            }
        }
    }

    private fun createdAndShowAddProductDialog() {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Add Product Group")

        val dialogView = layoutInflater
            .inflate(R.layout.add_product_group_dialog, null)

        builder.setView(dialogView)
        builder.setPositiveButton("Создать") { dialogInterface, _ ->
            val title = dialogView.editTitleProduct.text.toString()
            val productGroup = ProductGroup(productType = title)
            homeViewModel.saveProductGroup(productGroup)
            dialogInterface.dismiss()
        }
        builder.create().show()
    }
}