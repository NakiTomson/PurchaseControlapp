package com.example.dmiryz.ryzhov.shopproductlist.dataBase

import androidx.room.Embedded
import androidx.room.Relation

class ProductGroupWithProducts {

    @Embedded
    var productGroup: ProductGroup? = null
    @Relation(parentColumn = "id", entityColumn = "group_id", entity = Product::class)
    var contacts: List<Product> = listOf()

}