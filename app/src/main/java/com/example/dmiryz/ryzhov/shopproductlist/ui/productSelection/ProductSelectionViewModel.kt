package com.example.dmiryz.ryzhov.shopproductlist.ui.productSelection

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.dmiryz.ryzhov.shopproductlist.dataBase.ProductGroupWithProducts
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

class ProductSelectionViewModel(app:Application) : AndroidViewModel(app), CoroutineScope {



    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private val job: Job = Job()



    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}
