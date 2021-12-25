package com.biblia.foodapp.model.repository

import com.biblia.foodapp.model.APIService
import com.biblia.foodapp.model.RetrofitHelper
import com.biblia.foodapp.model.entities.CategoryModel

class CategoryRepo {

    suspend fun getAllMealCategory() : List<CategoryModel>? {
        val call = RetrofitHelper().getRetrofit().create(APIService::class.java).getAllMealCategory("categories.php")

        return if (call.isSuccessful) {
            call.body()?.categoryList
        } else {
            null
        }
    }
}