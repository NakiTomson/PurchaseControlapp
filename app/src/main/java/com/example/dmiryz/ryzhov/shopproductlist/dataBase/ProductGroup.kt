package com.example.dmiryz.ryzhov.shopproductlist.dataBase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "groupProduct")
class ProductGroup(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var productType: String,
    var shoppingProgress: Int = 0,
    var countNeedBuy: Int = 0,
    var countBuy: Int = 0
) {

}