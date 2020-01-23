package com.example.dmiryz.ryzhov.shopproductlist.ui.product

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dmiryz.ryzhov.shopproductlist.dataBase.*
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class ProductViewModel(app:Application) : AndroidViewModel(app), CoroutineScope {

    var productGroupWithProducts: ProductGroupWithProducts? = null

    var products: MutableLiveData<List<Product>> = MutableLiveData()
    var productsInfo: MutableLiveData<List<Product>> = MutableLiveData()

    var database: AppDatabase = AppDatabase.getDatabase(app)
    var productDao = database.getProductDao()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private val job: Job = Job()


    fun getProducts() {
        launch(Dispatchers.Main) {
            products.value = withContext(Dispatchers.Default) {
                productDao.getContactsOfGroup(productGroupWithProducts!!.productGroup!!.id!!)
            }
        }
    }

    fun saveProduct(product: Product){
        launch(Dispatchers.Default){
            product.groupId = productGroupWithProducts!!.productGroup!!.id
            productDao.insertOrUpdate(product)
        }
        getProducts()
    }


    fun deleteProduct(product: Product){
        launch(Dispatchers.Default){
            productDao.delete(product)
        }
        getProducts()
    }

    fun updateProduct(product: Product){
        launch(Dispatchers.Default){
            productDao.update(product)
        }
        getProducts()
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}
