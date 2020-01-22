package com.example.dmiryz.ryzhov.shopproductlist.ui.addProduct

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.dmiryz.ryzhov.shopproductlist.R
import com.example.dmiryz.ryzhov.shopproductlist.ui.addProduct.childefragments.FragmentCatalogElement
import com.example.dmiryz.ryzhov.shopproductlist.ui.addProduct.childefragments.FragmentPopular
import com.example.dmiryz.ryzhov.shopproductlist.core.ViewPagerAdapter
import kotlinx.android.synthetic.main.add_product_fragment.*

class AddProductFragment : Fragment() {

    lateinit var adapter: ViewPagerAdapter

    companion object {
        fun newInstance() = AddProductFragment()
    }

    private lateinit var viewModel: AddProductViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_product_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AddProductViewModel::class.java)

        adapter =
            ViewPagerAdapter(
                childFragmentManager
            )

        adapter.addFragment(FragmentPopular(), "Популярные")
        adapter.addFragment(FragmentCatalogElement(), "Каталог эелементов")

        viewPager_id.adapter = adapter
        tabLayout.setupWithViewPager(viewPager_id)


    }



}
