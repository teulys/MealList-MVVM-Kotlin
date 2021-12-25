package com.biblia.foodapp.model

import com.biblia.foodapp.model.entities.MealListModel
import com.biblia.foodapp.model.entities.MealsCategory
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {
    @GET
    suspend fun getAllMealCategory(@Url url: String) : Response<MealsCategory>

    @GET
    suspend fun getMealsByCategoryName(@Url url: String) : Response<MealListModel>
}