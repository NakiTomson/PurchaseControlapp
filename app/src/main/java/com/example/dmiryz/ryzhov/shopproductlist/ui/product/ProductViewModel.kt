package com.example.dmiryz.ryzhov.shopproductlist.ui.product

import android.app.Application
import android.content.Context
import android.content.res.TypedArray
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.dmiryz.ryzhov.shopproductlist.R
import com.example.dmiryz.ryzhov.shopproductlist.app
import com.example.dmiryz.ryzhov.shopproductlist.app.Companion.groupId
import com.example.dmiryz.ryzhov.shopproductlist.dataBase.*
import kotlinx.coroutines.*
import okhttp3.Dispatcher
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.util.concurrent.CopyOnWriteArrayList
import kotlin.coroutines.CoroutineContext

class ProductViewModel(app: Application) : AndroidViewModel(app), CoroutineScope {

    val icons: TypedArray = app.resources.obtainTypedArray(R.array.icons)
    var productGroupWithProducts: ProductGroupWithProducts? = null
    var products: MutableLiveData<List<Product>> = MutableLiveData()
    var database: AppDatabase = AppDatabase.getDatabase(app)
    var productDao = database.getProductDao()
    var categoryProductDao = database.getCategoryProduct()
    var positionGroup: Int? = null

    var listProductCategory: MutableList<ProductCategory> = mutableListOf()
    var listProduct: MutableList<Product> = mutableListOf()
    var listProductMostPopular: MutableList<Product> = mutableListOf()
    var listProductMostPopular2: CopyOnWriteArrayList<Product> = CopyOnWriteArrayList()//ConcurrentModificationException



    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private val job: Job = Job()


    fun getProducts() {
        listProductMostPopular.clear()
        listProductCategory.clear()
        listProduct.clear()
        groupId = productGroupWithProducts!!.productGroup!!.id!!
        if (productDao.getContactsOfGroup(productGroupWithProducts!!.productGroup!!.id!!).isEmpty()) {
            getDateListMostPopularProduct()
            getDateListCategory()
        } else {
            launch(Dispatchers.Main) {
                products.value = withContext(Dispatchers.Default) {
                    productDao.getContactsOfGroup(productGroupWithProducts!!.productGroup!!.id!!)
                }
            }
        }
    }


    fun saveProduct(product: Product) {
        launch(Dispatchers.Default) {
            product.groupId = productGroupWithProducts!!.productGroup!!.id
            productDao.insertOrUpdate(product)
        }
    }


    fun saveProductCategory(productCategory: ProductCategory) {
        launch(Dispatchers.Default) {
            categoryProductDao.insert(productCategory)
        }
        launch(Dispatchers.Main) {
            getProducts()
        }
    }


    fun updateProduct(product: Product) {
        launch(Dispatchers.Default) {
            productDao.update(product)
        }
        getProducts()
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }


    fun getDateListMostPopularProduct() {
        parseJsonPopular(loadJson())
    }

    fun getDateListCategory() {
        parseJson(loadJson())
    }


    fun parseJson(jsonData: String?) {
        val jsonObjMain: JSONObject
        try {
            jsonObjMain = JSONObject(jsonData)
            for (i in 0 until jsonObjMain.length() - 1) {
                val jsonObject = jsonObjMain.getJSONObject(app.KEY_NAME + i)
                val category = jsonObject.getInt(app.CATEGORY_QUESTION)
                val name = jsonObject.getString(app.CATEGORY_NAME)
                listProductCategory.add(
                    ProductCategory(
                        icon = icons.getResourceId(
                            category,
                            R.drawable.ic_close
                        ), title = name, category = category
                    )
                )
                val produstslist: JSONArray = jsonObject.getJSONArray(app.PRODUCTS)
                val category2 = jsonObject.getInt(app.CATEGORY_QUESTION)
                for (j in 0 until produstslist.length()) {
                    val produst: JSONObject = produstslist.getJSONObject(j)
                    val titleElement = produst.getString(app.TITLE_ELEMENT)
                    val groupId = produst.getInt(app.GROUP_ID)
                    val count = produst.getInt(app.COUNT)
                    val bought = produst.getBoolean(app.BOUGHT)
                    val IcongroupProduct = produst.getInt(app.GROUP_PRODUCT)
                    listProduct.add(
                        Product(
                            titleElement = titleElement,
                            groupId = groupId,
                            count = count,
                            bought = bought,
                            iconGroupProduct = IcongroupProduct,
                            icon = icons.getResourceId(category2, R.drawable.ic_close)
                        )
                    )
                }
            }
        } finally {
            if (categoryProductDao.getAll().isEmpty()) {
                listProductCategory.forEach { saveProductCategory(it) }
            }
            listProduct.forEach { saveProduct(it) }
        }
    }

    fun parseJsonPopular(jsonData: String?) {
        val jsonObjMain: JSONObject
        try {
            jsonObjMain = JSONObject(jsonData)
            val jsonObject = jsonObjMain.getJSONObject(app.KEY_NAME_POPULAR)
            val produstslist: JSONArray = jsonObject.getJSONArray(app.PRODUCTS)
            for (j in 0 until produstslist.length()) {
                val produst: JSONObject = produstslist.getJSONObject(j)
                val titleElement = produst.getString(app.TITLE_ELEMENT)
                val groupId = produst.getInt(app.GROUP_ID)
                val count = produst.getInt(app.COUNT)
                val bought = produst.getBoolean(app.BOUGHT)
                val IcongroupProduct = produst.getInt(app.GROUP_PRODUCT)
                listProductMostPopular.add(
                    Product(
                        titleElement = titleElement,
                        groupId = groupId,
                        count = count,
                        bought = bought,
                        iconGroupProduct = IcongroupProduct,
                        popular = true,
                        icon = icons.getResourceId("30".toInt(), R.drawable.ic_close)
                    )
                )
            }
        } finally {
            listProductMostPopular.forEach { saveProduct(it) }
        }
    }


    fun loadJson(): String? {
        val sb = StringBuffer()
        var br: BufferedReader? = null
        try {
            val contexts:Context = getApplication()
            br = BufferedReader(InputStreamReader(contexts.assets.open(app.CATEGORY_PROSUCT)))
            var temp: String?
            while (br.readLine().also { temp = it } != null) sb.append(temp)
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            try {
                br!!.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return sb.toString()
    }
}
