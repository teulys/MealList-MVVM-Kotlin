package com.biblia.foodapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.biblia.foodapp.model.entities.CategoryModel
import com.biblia.foodapp.model.repository.CategoryRepo

class CategoryViewModel(application: Application) : AndroidViewModel(application) {

    private var categoryList: MutableLiveData<List<CategoryModel>> = MutableLiveData<List<CategoryModel>>()
    private var errorMsg: MutableLiveData<String> = MutableLiveData<String>()

    suspend fun getAllMealCategory() : MutableLiveData<List<CategoryModel>> {

        val categories = CategoryRepo().getAllMealCategory()

        if (categories.isNullOrEmpty()) {
            setErrorMsg("An error occurred while searching for food categories")
        }
        else {
            categoryList.postValue(categories)
        }

        return categoryList
    }

    fun getErrorMessage() : MutableLiveData<String> {
        return errorMsg
    }

    private fun setErrorMsg(msg: String) {
        errorMsg.postValue(msg)
    }
}