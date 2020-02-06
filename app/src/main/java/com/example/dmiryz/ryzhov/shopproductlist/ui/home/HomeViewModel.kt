package com.example.dmiryz.ryzhov.shopproductlist.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.dmiryz.ryzhov.shopproductlist.dataBase.AppDatabase
import com.example.dmiryz.ryzhov.shopproductlist.dataBase.Product
import com.example.dmiryz.ryzhov.shopproductlist.dataBase.ProductGroup
import com.example.dmiryz.ryzhov.shopproductlist.dataBase.ProductGroupWithProducts
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class HomeViewModel(app: Application) : AndroidViewModel(app), CoroutineScope {

    var products: MutableLiveData<List<ProductGroupWithProducts>> = MutableLiveData()
    var database: AppDatabase = AppDatabase.getDatabase(app)

    var groupsDao = database.getGroupProductDao()
    var groupsDaoProducts = database.getProductDao()
    var groupsDaoCategory = database.getCategoryProduct()

    lateinit var cuurentproductGroup: List<ProductGroupWithProducts>

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private val job: Job = Job()


    fun getProductsGroup() {
        launch(Dispatchers.Main) {
            products.value = withContext(Dispatchers.Default) {
                cuurentproductGroup = groupsDao.getAll()
                groupsDao.getAll()
            }
        }
    }

    fun get(): List<ProductGroupWithProducts> {
        //TODO allowMainTrad
        cuurentproductGroup = groupsDao.getAll()
        return cuurentproductGroup
    }


    fun updateProductGroup(product: ProductGroup) {
        launch(Dispatchers.Default) {
            groupsDao.update(product)
        }

    }

    fun saveProductGroup(product: ProductGroup) {
        launch(Dispatchers.Default) {
            groupsDao.insert(product)
        }
        getProductsGroup()
    }

    fun delete(product: ProductGroup) {
        launch(Dispatchers.Default) {
            groupsDaoProducts.deleteAll(product.id!!)
//            groupsDaoCategory.deleteAll()
            groupsDao.delete(product)
        }
        getProductsGroup()
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

}