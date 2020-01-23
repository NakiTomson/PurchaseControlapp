package com.example.dmiryz.ryzhov.shopproductlist.ui.home

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.dmiryz.ryzhov.shopproductlist.dataBase.AppDatabase
import com.example.dmiryz.ryzhov.shopproductlist.dataBase.ProductGroup
import com.example.dmiryz.ryzhov.shopproductlist.dataBase.ProductGroupWithProducts
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class HomeViewModel(app: android.app.Application) : AndroidViewModel(app), CoroutineScope {

    var products: MutableLiveData<List<ProductGroupWithProducts>> = MutableLiveData()
    var database: AppDatabase = AppDatabase.getDatabase(app)
    var groupsDao = database.getGroupProductDao()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private val job: Job = Job()


    fun getProducts(){
        launch(Dispatchers.Main) {
            products.value = withContext(Dispatchers.Default){
                groupsDao.getAll()
            }
        }
    }

    fun saveProductGroup(product: ProductGroup){
        launch(Dispatchers.Default){
            groupsDao.insert(product)
        }
        getProducts()
    }

    fun delete(product: ProductGroup){
        launch(Dispatchers.Default){
            groupsDao.delete(product)
        }
        getProducts()
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

}