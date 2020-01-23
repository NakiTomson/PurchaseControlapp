package com.example.dmiryz.ryzhov.shopproductlist.ui.productSelection

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.dmiryz.ryzhov.shopproductlist.dataBase.ProductGroupWithProducts
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

class ProductSelectionViewModel(app:Application) : AndroidViewModel(app), CoroutineScope {

    var productGroupWithProducts: ProductGroupWithProducts? = null

//    var database: AppDatabase = AppDatabase.getDatabase(app)
//    var contactDao = database.getProductDao()
    //TODO Получение данных из JSON

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private val job: Job = Job()

//    suspend fun requestProducts(): LiveData<List<Product>> {
//        return withContext(Dispatchers.Default){
//            contactDao.getContactsOfGroup(productGroupWithProducts!!.productGroup!!.id!!)
//        }
//    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}
