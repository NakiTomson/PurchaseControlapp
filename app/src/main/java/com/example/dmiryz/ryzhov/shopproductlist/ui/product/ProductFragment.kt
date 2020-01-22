package com.example.dmiryz.ryzhov.shopproductlist.ui.product

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.dmiryz.ryzhov.shopproductlist.R
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.product_fragment.*


class ProductFragment : Fragment() {

    companion object { fun newInstance() = ProductFragment() }

    private lateinit var viewModel: ProductViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.product_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ProductViewModel::class.java)

        activity?.fab?.apply {
            setOnClickListener {
                val navController = recycler_list_product.findNavController()
                navController.navigate(R.id.addProductFragment)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }

}
