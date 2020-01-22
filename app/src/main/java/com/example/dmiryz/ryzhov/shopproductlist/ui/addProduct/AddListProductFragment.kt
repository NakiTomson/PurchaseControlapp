package com.example.dmiryz.ryzhov.shopproductlist.ui.addProduct

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.dmiryz.ryzhov.shopproductlist.R
import com.example.dmiryz.ryzhov.shopproductlist.core.SimplesAdapter
import com.example.dmiryz.ryzhov.shopproductlist.dataBase.Product
import com.example.dmiryz.ryzhov.shopproductlist.ui.MainActivity
import kotlinx.android.synthetic.main.add_list_product_fragment.*
import kotlinx.android.synthetic.main.two_fragment.*

class AddListProductFragment : Fragment() {

    companion object {
        fun newInstance() = AddListProductFragment()
    }

    private lateinit var viewModel: AddListProductViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_list_product_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val mainActivity = activity as MainActivity
        viewModel = ViewModelProviders.of(mainActivity).get(AddListProductViewModel::class.java)
        viewModel.getProducts()
        recyclerViewListProductAdd.layoutManager = LinearLayoutManager(context)
        recyclerViewListProductAdd.addItemDecoration(
            DividerItemDecoration(recyclerViewListProductAdd.context,
                DividerItemDecoration.VERTICAL
            )
        )
        viewModel.productsLists.observe(this, Observer<List<Product>> {
            recyclerViewListProductAdd.adapter = SimplesAdapter(context = context!!,products = it,flag = 1)
        })
    }

}
