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
import com.example.dmiryz.ryzhov.shopproductlist.dataBase.ProductCategory
import com.example.dmiryz.ryzhov.shopproductlist.ui.MainActivity
import com.example.dmiryz.ryzhov.shopproductlist.ui.product.ProductViewModel
import com.example.dmiryz.ryzhov.shopproductlist.ui.productSelection.addProduct.AddProductViewModel
import kotlinx.android.synthetic.main.two_fragment.*


class FragmentCatalogElement() : Fragment() {
    var listCategory: MutableList<ProductCategory> = ArrayList()
    var productList: MutableList<Product> = ArrayList()
    private lateinit var viewModel: ViewModelChilds
    private lateinit var viewModelProduct: ProductViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.two_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        listCategory.clear()
        productList.clear()
        val mainActivity = activity as MainActivity
        viewModel = ViewModelProviders.of(mainActivity).get(ViewModelChilds::class.java)
        viewModel.getDateListMostPopularProduct(context!!)
        val addConViewModel =  ViewModelProviders.of(mainActivity).get(AddProductViewModel::class.java)
        recycler_list_catalog_element.layoutManager = LinearLayoutManager(context)

        viewModel.getDateListCategory(context!!)

        viewModel.productsListLiveDate.observe(this, Observer<MutableList<Product>> {
            productList = it
        })
        viewModelProduct = ViewModelProviders.of(mainActivity).get(ProductViewModel::class.java)
        viewModel.listCategoryLiveDate.observe(this, Observer<MutableList<ProductCategory>> {
            listCategory = it
            recycler_list_catalog_element.adapter = SimplesAdapter(it, context!!,productList,addConViewModel!!,flag = 0,productViewModel = viewModelProduct)
        })
        recycler_list_catalog_element.addItemDecoration(DividerItemDecoration(recycler_list_catalog_element.context, DividerItemDecoration.VERTICAL))
    }
}

