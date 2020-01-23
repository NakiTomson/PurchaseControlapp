package com.example.dmiryz.ryzhov.shopproductlist.ui.productSelection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.dmiryz.ryzhov.shopproductlist.R
import com.example.dmiryz.ryzhov.shopproductlist.ui.productSelection.childefragments.FragmentCatalogElement
import com.example.dmiryz.ryzhov.shopproductlist.ui.productSelection.childefragments.FragmentPopular
import com.example.dmiryz.ryzhov.shopproductlist.core.ViewPagerAdapter
import kotlinx.android.synthetic.main.add_product_fragment.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.product_fragment.*

class ProductSelectionFragment : Fragment() {

    lateinit var adapter: ViewPagerAdapter

    companion object {
        fun newInstance() = ProductSelectionFragment()
    }

    private lateinit var viewModel: ProductSelectionViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.add_product_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ProductSelectionViewModel::class.java)
        adapter = ViewPagerAdapter(childFragmentManager)
        adapter.addFragment(FragmentPopular(), "Популярные")
        adapter.addFragment(FragmentCatalogElement(), "Каталог эелементов")
        viewPager_id.adapter = adapter
        tabLayout.setupWithViewPager(viewPager_id)

        activity!!.toolbar.visibility = View.GONE
        activity!!.fab.visibility = View.VISIBLE
        activity!!.fab.setImageResource(R.drawable.ic_search)
        activity?.fab?.apply {
            setOnClickListener {
                Toast.makeText(context,"seach",Toast.LENGTH_SHORT).show()
            }
        }
    }




}
