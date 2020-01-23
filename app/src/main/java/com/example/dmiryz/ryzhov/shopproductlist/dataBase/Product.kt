package com.example.dmiryz.ryzhov.shopproductlist.dataBase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


@Entity(tableName = "product")
class Product(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,

    var titleElement: String? = null,
    @ColumnInfo(name = "group_id")
    var groupId: Int? = null,
    var count: Int? = null,
    var bought: Boolean = false,
    var countWeight: String? = null,
    var price: String? = null,
    var comment: String? = null,
    var iconGroupProduct: Int? = null,
    var selected: Boolean = false
) {
}