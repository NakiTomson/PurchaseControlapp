package com.example.dmiryz.ryzhov.shopproductlist.ui.productSelection.childefragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dmiryz.ryzhov.shopproductlist.R
import com.example.dmiryz.ryzhov.shopproductlist.core.SimplesAdapter
import com.example.dmiryz.ryzhov.shopproductlist.dataBase.Product
import com.example.dmiryz.ryzhov.shopproductlist.ui.MainActivity
import com.example.dmiryz.ryzhov.shopproductlist.ui.product.ProductViewModel
import kotlinx.android.synthetic.main.one_fragment.*

class FragmentPopular (): Fragment() {

    private lateinit var viewModel: ViewModelChilds
    private lateinit var viewModelProduct: ProductViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.one_fragment,container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recycler_popular.layoutManager = LinearLayoutManager(context)
        val mainActivity = activity as MainActivity
        viewModel = ViewModelProviders.of(mainActivity).get(ViewModelChilds::class.java)
        viewModel.getProducts()
        viewModelProduct = ViewModelProviders.of(mainActivity).get(ProductViewModel::class.java)
        viewModel.productsListLiveDate.observe(this, Observer<List<Product>> {
            recycler_popular.addItemDecoration(DividerItemDecoration(recycler_popular.context, DividerItemDecoration.VERTICAL))
            recycler_popular.adapter = SimplesAdapter(products = it.filter { it.popular }, context = context!!,flag = 1,productViewModel = viewModelProduct)
        })
    }

}