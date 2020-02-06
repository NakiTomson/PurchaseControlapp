package com.example.dmiryz.ryzhov.shopproductlist.ui.productSelection.addProduct

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.dmiryz.ryzhov.shopproductlist.dataBase.AppDatabase
import com.example.dmiryz.ryzhov.shopproductlist.dataBase.Product
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class AddProductViewModel(app: Application) : AndroidViewModel(app), CoroutineScope {

    var productsList: MutableList<Product> = ArrayList()

    var productsLists: MutableLiveData<List<Product>> = MutableLiveData()
    var database: AppDatabase = AppDatabase.getDatabase(app)
    var groupsDao = database.getProductDao()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private val job: Job = Job()


    fun getProducts(){
        Log.i("TAG", productsList.size.toString()+"in view model")
        launch(Dispatchers.Main) {
            productsLists.value = withContext(Dispatchers.Default){
                productsList
            }
        }
    }

}
