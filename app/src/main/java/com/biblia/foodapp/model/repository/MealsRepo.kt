package com.biblia.foodapp.model.repository

import com.biblia.foodapp.model.APIService
import com.biblia.foodapp.model.RetrofitHelper
import com.biblia.foodapp.model.entities.MealModel

class MealsRepo {

    suspend fun getMealsByCategoryName(name: String) : List<MealModel>? {
        val call = RetrofitHelper().getRetrofit().create(APIService::class.java).getMealsByCategoryName("filter.php?c=$name")

        return if (call.isSuccessful) {
            call.body()?.meals
        } else {
            null
        }
    }

}