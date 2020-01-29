package com.example.dmiryz.ryzhov.shopproductlist.ui.product

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.view.size
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dmiryz.ryzhov.shopproductlist.R
import com.example.dmiryz.ryzhov.shopproductlist.app
import com.example.dmiryz.ryzhov.shopproductlist.app.Companion.groupId
import com.example.dmiryz.ryzhov.shopproductlist.core.ProductAdapter
import com.example.dmiryz.ryzhov.shopproductlist.dataBase.Product
import com.example.dmiryz.ryzhov.shopproductlist.dataBase.ProductGroup
import com.example.dmiryz.ryzhov.shopproductlist.dataBase.ProductGroupWithProducts
import com.example.dmiryz.ryzhov.shopproductlist.ui.MainActivity
import com.example.dmiryz.ryzhov.shopproductlist.ui.home.HomeViewModel
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.product_fragment.*


class ProductFragment : Fragment() {

    companion object {
        fun newInstance() = ProductFragment()
    }

    private lateinit var viewModel: ProductViewModel
    private lateinit var viewModelHome: HomeViewModel
    private var productAdapter: ProductAdapter = ProductAdapter()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.product_fragment, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val mainActivity = activity as MainActivity
        viewModel = ViewModelProviders.of(mainActivity).get(ProductViewModel::class.java)
        viewModelHome = ViewModelProviders.of(mainActivity).get(HomeViewModel::class.java)
        viewModel.getProducts()
        recycler_list_product.layoutManager = LinearLayoutManager(context)
        viewModel.products.observe(this, Observer<List<Product>> {
            val productlistNeedBay:List<Product> = it.filter { it.selected }
            val productlistBay:List<Product> = it.filter { it.bought }
            productAdapter.setDate(productlistNeedBay.filter { it?.groupId == groupId }, context!!, viewModel)
            recycler_list_product.adapter = productAdapter
            if (it.isEmpty()) {
                imageBanan.visibility = View.VISIBLE
                textStart.visibility = View.VISIBLE
            }
            val productGroupWithProducts:List<ProductGroupWithProducts> = viewModelHome.get()
            if(productlistNeedBay.isEmpty()) return@Observer
            val productGroup:ProductGroup = productGroupWithProducts[viewModel.positionGroup!!].productGroup!!

            productGroup.countNeedBuy = productlistNeedBay.size
            productGroup.countBuy = productlistBay.size
            viewModelHome.updateProductGroup(productGroup)
        })
        activity!!.toolbar.visibility = View.VISIBLE
        activity?.fab?.setImageResource(R.drawable.ic_add)
        activity?.fab?.apply {
            setOnClickListener {
                val navController = recycler_list_product.findNavController()
                navController.navigate(R.id.addProductFragment)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


}
