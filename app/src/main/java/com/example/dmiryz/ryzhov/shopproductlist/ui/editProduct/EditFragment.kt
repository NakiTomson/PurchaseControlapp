package com.example.dmiryz.ryzhov.shopproductlist.ui.editProduct


import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.dmiryz.ryzhov.shopproductlist.R
import com.example.dmiryz.ryzhov.shopproductlist.dataBase.Product
import com.example.dmiryz.ryzhov.shopproductlist.ui.MainActivity
import com.example.dmiryz.ryzhov.shopproductlist.ui.product.ProductViewModel
import kotlinx.android.synthetic.main.fragment_edit.*


class EditFragment : Fragment() {
    private lateinit var viewModel: ProductViewModel
    lateinit var productForEdit: Product
    lateinit var productEdited: Product
    var productsGroupName: Array<String>? = null
    var delete: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_edit, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        activity?.window?.decorView?.systemUiVisibility = (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
        productsGroupName = resources.getStringArray(R.array.group_product)
        val mainActivity = activity as MainActivity
        viewModel = ViewModelProviders.of(mainActivity).get(ProductViewModel::class.java)
        getData()
    }


    private fun getData() {
        productForEdit = viewModel.productForEdit ?: Product()
        productEdited = productForEdit.copy()
        editElement.setText(productForEdit.titleElement)
        title_product_group.setText(productsGroupName?.get(productForEdit.iconGroupProduct ?: 0))
        productForEdit.count?.let { count_of_product.setText(it.toString()) }
        productForEdit.countWeight?.let { weight_or_another_units.setText(it) }
        productForEdit.price?.let { price_for_one.setText(it.toString()) }
        productForEdit.comment?.let { comment_more_about_product.setText(it) }
        productForEdit.icon?.let { imageCategoryProduct.setImageResource(it) }
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_edit, menu)
        super.onCreateOptionsMenu(menu, inflater);
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = root_edit_product.findNavController()
        return when (item.itemId) {
            R.id.action_edit_product -> {
                setBase()
                viewModel.updateProduct(productForEdit)
                delete = true
                navController.popBackStack()
            }
            android.R.id.home -> {
                navController.popBackStack()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setBase() {
        productForEdit.selected = false
        productForEdit.count = 0
        productForEdit.comment = null
        productForEdit.countWeight = null
        productForEdit.price = null
        productForEdit.bought = false
        productForEdit.titleElement
    }

    private fun updateDate() {
        productEdited.titleElement = editElement.text.toString()
        productEdited.count = count_of_product.text.toString().toInt()
        productEdited.price = price_for_one.text.toString()
        productEdited.countWeight = weight_or_another_units.text.toString()
        productEdited.comment = comment_more_about_product.text.toString()
        if (productForEdit != productEdited) {
            if (productForEdit.titleElement != productEdited.titleElement) {
                setBase()
                viewModel.updateProduct(productForEdit)
                productEdited.id = null
                productEdited.popular = false
                productEdited.edited = true
                viewModel.saveProduct(productEdited)
            } else {
                viewModel.updateProduct(productEdited)
            }
        }
    }

    override fun onStop() {
        if (!delete) updateDate()
        super.onStop()
    }
}
