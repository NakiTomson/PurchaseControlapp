package com.example.dmiryz.ryzhov.shopproductlist.ui.addProduct.childefragments

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.dmiryz.ryzhov.shopproductlist.R
import com.example.dmiryz.ryzhov.shopproductlist.app
import com.example.dmiryz.ryzhov.shopproductlist.dataBase.Product
import com.example.dmiryz.ryzhov.shopproductlist.dataBase.ProductCategory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class ViewModelChilds(app: Application) : AndroidViewModel(app) {

    var listCategory: MutableList<ProductCategory> = ArrayList()
    var productList: MutableList<Product> = ArrayList()
    var productListMostPopular: MutableList<Product> = ArrayList()


    var listCategoryLiveDate: MutableLiveData<MutableList<ProductCategory>> = MutableLiveData()
    var productsListLiveDate: MutableLiveData<MutableList<Product>> = MutableLiveData()
    var productListMostPopularLiveDate: MutableLiveData<MutableList<Product>>   = MutableLiveData()




    fun getDateListMostPopularProduct(context: Context) {
        productListMostPopular.clear()
        parseJsonPopular(loadJson(context))
    }

    fun getDateListCategory(context: Context) {
        listCategory.clear()
        productList.clear()
        parseJson(loadJson(context))
    }



    fun parseJson(jsonData: String?) {
        var jsonObjMain: JSONObject? = null
        try {
            jsonObjMain = JSONObject(jsonData)
            for (i in 0 until jsonObjMain.length()) {
                val jsonObject = jsonObjMain.getJSONObject(app.KEY_NAME + i)
                val category = jsonObject.getInt(app.CATEGORY_QUESTION)
                val name = jsonObject.getString(app.CATEGORY_NAME)
                listCategory.add(ProductCategory(icon = images[category], title = name, category = category))
                val produstslist: JSONArray = jsonObject.getJSONArray(app.PRODUCTS)
                for (j in 0 until produstslist.length()) {
                    val produst: JSONObject = produstslist.getJSONObject(j)
                    val titleElement = produst.getString(app.TITLE_ELEMENT)
                    val groupId = produst.getInt(app.GROUP_ID)
                    val count = produst.getInt(app.COUNT)
                    val bought = produst.getBoolean(app.BOUGHT)
                    val weight_count = produst.getString(app.WEIGHT)
                    val price = produst.getString(app.PRICE)
                    val comment = produst.getString(app.COMMENT)
                    val IcongroupProduct = produst.getInt(app.GROUP_PRODUCT)
                    productList.add(Product(titleElement = titleElement, groupId = groupId, count = count, bought = bought, countWeight = weight_count, price = price, comment = comment, iconGroupProduct = IcongroupProduct))
                }
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }finally {
            productsListLiveDate.value = productList
            listCategoryLiveDate.value = listCategory
        }
    }

    fun parseJsonPopular(jsonData: String?){
        var jsonObjMain: JSONObject? = null
        jsonObjMain = JSONObject(jsonData)
        val jsonObject = jsonObjMain.getJSONObject(app.KEY_NAME_POPULAR)
        val produstslist: JSONArray = jsonObject.getJSONArray(app.PRODUCTS)
        for (j in 0 until produstslist.length()) {
            val produst: JSONObject = produstslist.getJSONObject(j)
            val titleElement = produst.getString(app.TITLE_ELEMENT)
            val groupId = produst.getInt(app.GROUP_ID)
            val count = produst.getInt(app.COUNT)
            val bought = produst.getBoolean(app.BOUGHT)
            val weight_count = produst.getString(app.WEIGHT)
            val price = produst.getString(app.PRICE)
            val comment = produst.getString(app.COMMENT)
            val IcongroupProduct = produst.getInt(app.GROUP_PRODUCT)
            productListMostPopular.add(Product(titleElement = titleElement, groupId = groupId, count = count, bought = bought, countWeight = weight_count, price = price, comment = comment, iconGroupProduct = IcongroupProduct))
        }
        productListMostPopularLiveDate.value = productListMostPopular
    }


    fun loadJson(context: Context): String? {
        val sb = StringBuffer()
        var br: BufferedReader? = null
        try {
            br = BufferedReader(InputStreamReader(context?.getAssets()?.open(app.CATEGORY_PROSUCT)))
            var temp: String?
            while (br.readLine().also { temp = it } != null) sb.append(temp)
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            try {
                br!!.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return sb.toString()
    }

    val images = intArrayOf(
        R.drawable.icon_alcohol,
        R.drawable.icon_pharmacy,
        R.drawable.icon_newspaper_books_music,
        R.drawable.icon_ready_dish,
        R.drawable.icon_diet_food,
        R.drawable.icone_home_and_kitchen,
        R.drawable.icon_home_cake,
        R.drawable.icon_food_preservation,
        R.drawable.icon_frozen_foods,
        R.drawable.icon_health_and_beauty,
        R.drawable.icon_stationery,
        R.drawable.icon_canned_food,
        R.drawable.icon_coffee_tea_cocoa,
        R.drawable.icon_cereals_pasta,
        R.drawable.icon_personal_hygiene,
        R.drawable.icon_oils,
        R.drawable.icon_milk_produce,
        R.drawable.icon_meat_poultry,
        R.drawable.icon_drinks,
        R.drawable.icon_clothes,
        R.drawable.icon_etc,
        R.drawable.icon_fish_and_seafood,
        R.drawable.icon_sweets_snack_food,
        R.drawable.icon_sauces_spices_seasonings,
        R.drawable.house_cleaning,
        R.drawable.icon_car_care,
        R.drawable.icon_baby_care,
        R.drawable.icon_pet_care,
        R.drawable.icon_fruits_and_vegetables,
        R.drawable.icon_bread_and_pastries,
        R.drawable.icon_electronics_and_engineering
    )

}