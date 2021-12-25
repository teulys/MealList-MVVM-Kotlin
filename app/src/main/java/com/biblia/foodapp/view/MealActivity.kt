package com.biblia.foodapp.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.biblia.foodapp.databinding.ActivityMainBinding
import com.biblia.foodapp.model.entities.MealModel
import com.biblia.foodapp.view.adapters.MealAdapter
import com.biblia.foodapp.viewmodel.MealViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MealActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MealAdapter
    private var mealList: MutableList<MealModel> = mutableListOf()
    private var category: String = ""
    private lateinit var mealVM : MealViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        category = intent.getStringExtra("category") ?: ""

        mealVM = MealViewModel(application)

        initRecycleView()
        initErrorMsg()
    }

    private fun initRecycleView() {
        adapter = MealAdapter(mealList)

        binding.rvCategory.layoutManager = LinearLayoutManager(this)
        binding.rvCategory.adapter = adapter

        loadMealList()
    }

    private fun loadMealList() {
        if (!category.isNullOrEmpty())
        {
            CoroutineScope(Dispatchers.IO).launch {

                val mealVModel = mealVM.getMealsByCategoryName(category)

                runOnUiThread {
                    mealVModel.observe(this@MealActivity, Observer {
                        mealList.clear()
                        mealList.addAll(it)
                        adapter.notifyDataSetChanged()
                    })
                }
            }
        }
    }

    private fun initErrorMsg() {
        mealVM.getErrorMessage().observe(this, Observer {
            if (!it.isNullOrEmpty())
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
    }

}