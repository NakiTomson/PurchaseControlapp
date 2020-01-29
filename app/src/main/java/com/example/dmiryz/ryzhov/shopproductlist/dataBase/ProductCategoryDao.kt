package com.example.dmiryz.ryzhov.shopproductlist.dataBase

import androidx.room.*

@Dao
interface ProductCategoryDao {

    @Query("SELECT * FROM productCategory")
    fun getAll() : List<ProductCategory>

    @Insert
    fun insert(productCategory: ProductCategory)

    @Update
    fun update(productCategory: ProductCategory)

    @Delete
    fun delete(productCategory: ProductCategory)

    @Query("DELETE from productCategory")
    fun deleteAll()


}

fun ProductCategoryDao.insertOrUpdate(productCategory: ProductCategory) {
    if (productCategory.id != null) {
        update(productCategory)
    } else {
        insert(productCategory)
    }
}