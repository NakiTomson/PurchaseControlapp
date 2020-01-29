package com.example.dmiryz.ryzhov.shopproductlist.dataBase

import androidx.room.*

@Dao
interface ProductGroupDao {

    @Query("SELECT * FROM groupProduct")
    fun getAll() : List<ProductGroupWithProducts>

    @Insert
    fun insert(product: ProductGroup)

    @Update
    fun update(product: ProductGroup)

    @Delete
    fun delete(roduct: ProductGroup)


}