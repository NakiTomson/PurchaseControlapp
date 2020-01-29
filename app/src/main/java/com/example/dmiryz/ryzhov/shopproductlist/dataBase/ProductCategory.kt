package com.example.dmiryz.ryzhov.shopproductlist.dataBase

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "productCategory")
class ProductCategory(

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var title: String,
    var icon: Int,
    var category: Int

) {
}



