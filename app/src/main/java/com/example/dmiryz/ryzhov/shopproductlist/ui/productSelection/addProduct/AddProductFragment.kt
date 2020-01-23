package com.example.dmiryz.ryzhov.shopproductlist.ui.productSelection.addProduct

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.dmiryz.ryzhov.shopproductlist.R
import com.example.dmiryz.ryzhov.shopproductlist.core.SimplesAdapter
import com.example.dmiryz.ryzhov.shopproductlist.dataBase.Product
import com.example.dmiryz.ryzhov.shopproductlist.ui.MainActivity
import com.example.dmiryz.ryzhov.shopproductlist.ui.product.ProductViewModel
import kotlinx.android.synthetic.main.add_list_product_fragment.*
import kotlinx.android.synthetic.main.app_bar_main.*

class AddProductFragment : Fragment() {

    companion object {
        fun newInstance() =
            AddProductFragment()
    }

    private lateinit var viewModel: AddProductViewModel
    private lateinit var viewModelProduct: ProductViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_list_product_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val mainActivity = activity as MainActivity
        viewModel = ViewModelProviders.of(mainActivity).get(AddProductViewModel::class.java)
        viewModel.getProducts()
        recyclerViewListProductAdd.layoutManager = LinearLayoutManager(context)
        recyclerViewListProductAdd.addItemDecoration(
            DividerItemDecoration(recyclerViewListProductAdd.context,
                DividerItemDecoration.VERTICAL
            )
        )

        viewModelProduct = ViewModelProviders.of(mainActivity).get(ProductViewModel::class.java)
        viewModel.productsLists.observe(this, Observer<List<Product>> {
            recyclerViewListProductAdd.adapter = SimplesAdapter(context = context!!,products = it,flag = 1,productViewModel = viewModelProduct )
        })
        activity?.fab?.visibility = View.INVISIBLE

    }

}
