package com.biblia.foodapp.model.entities

import com.google.gson.annotations.SerializedName

data class MealModel(@SerializedName("strMeal") val name: String,
                     @SerializedName("strMealThumb") val image: String,
                     @SerializedName("idMeal") val id: Int)

data class MealListModel(@SerializedName("meals") val meals: List<MealModel>)

