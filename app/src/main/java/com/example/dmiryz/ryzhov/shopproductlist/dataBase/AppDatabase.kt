package com.example.dmiryz.ryzhov.shopproductlist.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ProductGroup::class,Product::class,ProductCategory::class], version = 3)
abstract class AppDatabase() : RoomDatabase(){

    abstract fun getGroupProductDao(): ProductGroupDao
    abstract fun getProductDao(): ProductDao
    abstract fun getCategoryProduct():ProductCategoryDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                //Create database
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "database"
                )
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

}