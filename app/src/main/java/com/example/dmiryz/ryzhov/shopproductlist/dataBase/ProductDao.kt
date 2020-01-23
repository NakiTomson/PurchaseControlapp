package com.example.dmiryz.ryzhov.shopproductlist.dataBase

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ProductDao {

    @Query("SELECT * FROM product WHERE group_id = :groupId")
    fun getContactsOfGroup(groupId: Int): List<Product>

    @Query("SELECT COUNT(id) FROM product WHERE group_id = :groupId")
    fun getCount(groupId: Int): Int

    @Insert
    fun insert(group: Product)

    @Update
    fun update(group: Product)

    @Delete
    fun delete(group: Product)
}


fun ProductDao.insertOrUpdate(product: Product) {
    if (product.id != null) {
        update(product)
    } else {
        insert(product)
    }
}