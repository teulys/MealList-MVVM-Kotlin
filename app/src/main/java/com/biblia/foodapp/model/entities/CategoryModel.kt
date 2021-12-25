package com.biblia.foodapp.model.entities

import com.google.gson.annotations.SerializedName

data class CategoryModel(@SerializedName("idCategory") val id: Int,
                         @SerializedName("strCategory") val name: String,
                         @SerializedName("strCategoryThumb") val image: String,
                         @SerializedName("strCategoryDescription" ) val description: String)

data class MealsCategory(@SerializedName("categories") val categoryList: List<CategoryModel>)