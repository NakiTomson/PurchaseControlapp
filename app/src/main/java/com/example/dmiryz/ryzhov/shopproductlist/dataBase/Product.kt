package com.example.dmiryz.ryzhov.shopproductlist.dataBase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "product")
class Product(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo(name = "group_id")
    var groupId: Int,
    var titleElement: String,
    var count: Int = 1,
    var bought:Boolean = false,
    var countWeight: String? = null,
    var price: String? = null,
    var comment: String? = null,
    var iconGroupProduct: Int
) {
}