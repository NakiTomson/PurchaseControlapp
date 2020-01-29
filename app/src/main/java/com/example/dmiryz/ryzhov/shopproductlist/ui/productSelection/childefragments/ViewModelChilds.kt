package com.example.dmiryz.ryzhov.shopproductlist.ui.productSelection.childefragments

import android.app.Application
import android.content.Context
import android.content.res.TypedArray
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.dmiryz.ryzhov.shopproductlist.R
import com.example.dmiryz.ryzhov.shopproductlist.app
import com.example.dmiryz.ryzhov.shopproductlist.app.Companion.groupId
import com.example.dmiryz.ryzhov.shopproductlist.dataBase.AppDatabase
import com.example.dmiryz.ryzhov.shopproductlist.dataBase.Product
import com.example.dmiryz.ryzhov.shopproductlist.dataBase.ProductCategory
import com.example.dmiryz.ryzhov.shopproductlist.dataBase.insertOrUpdate
import kotlinx.coroutines.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import kotlin.coroutines.CoroutineContext

class ViewModelChilds(app: Application) : AndroidViewModel(app), CoroutineScope {




    var listCategoryLiveDate: MutableLiveData<List<ProductCategory>> = MutableLiveData()
    var productsListLiveDate: MutableLiveData<List<Product>> = MutableLiveData()
    var productListMostPopularLiveDate: MutableLiveData<MutableList<Product>> = MutableLiveData()

    var database: AppDatabase = AppDatabase.getDatabase(app)
    var productDao = database.getProductDao()
    var categoryProductDao = database.getCategoryProduct()



    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private val job: Job = Job()

    fun getProducts() {
        launch(Dispatchers.Main) {
            productsListLiveDate.value = withContext(Dispatchers.Default) {
                productDao.getContactsOfGroup(groupId)
            }
        }
    }
    fun getProductCategory() {
        launch(Dispatchers.Main) {
            listCategoryLiveDate.value = withContext(Dispatchers.Default) {
                categoryProductDao.getAll()
            }
        }
    }

    fun updateProduct(product: Product) {
        launch(Dispatchers.Default) {
            productDao.update(product)
        }
        getProducts()
    }


}