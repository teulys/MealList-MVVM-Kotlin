package com.biblia.foodapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.biblia.foodapp.model.entities.MealModel
import com.biblia.foodapp.model.repository.MealsRepo

class MealViewModel(application: Application) : AndroidViewModel(application) {

    private var mealList : MutableLiveData<List<MealModel>> = MutableLiveData<List<MealModel>>()
    private var errorMsg: MutableLiveData<String> = MutableLiveData<String>()

    suspend fun getMealsByCategoryName(category: String) : MutableLiveData<List<MealModel>> {

        val meals = MealsRepo().getMealsByCategoryName(category)

        if (meals.isNullOrEmpty()) {
            setErrorMsg("The category $category not have meals")
        } else {
            mealList.postValue(meals)
        }

        return mealList
    }

    fun getErrorMessage() : MutableLiveData<String> {
        return errorMsg
    }

    private fun setErrorMsg(msg: String) {
        errorMsg.postValue(msg)
    }

}