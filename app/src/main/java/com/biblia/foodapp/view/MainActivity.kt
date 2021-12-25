package com.biblia.foodapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.biblia.foodapp.databinding.ActivityMainBinding
import com.biblia.foodapp.model.entities.CategoryModel
import com.biblia.foodapp.view.adapters.CategoryAdapter
import com.biblia.foodapp.viewmodel.CategoryViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private var mealCategories = mutableListOf<CategoryModel>()
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var categoryViewModel: CategoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        categoryViewModel = CategoryViewModel(application)

        initRecycleView()
        initErrorMsg()
    }

    private fun initErrorMsg() {
        categoryViewModel.getErrorMessage().observe(this, Observer {
            if (!it.isNullOrEmpty())
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
    }

    private fun initRecycleView() {
        categoryAdapter = CategoryAdapter(mealCategories, this)
        binding.rvCategory.layoutManager = LinearLayoutManager(this)
        binding.rvCategory.adapter = categoryAdapter

        loadCategoryList()
    }

    private fun loadCategoryList() {
        CoroutineScope(Dispatchers.IO).launch {

            var categorVM = categoryViewModel.getAllMealCategory()

            runOnUiThread {
                categorVM.observe(this@MainActivity, Observer {
                    mealCategories.clear()
                    mealCategories.addAll(it)

                    println(mealCategories.size)
                    if (categoryAdapter == null) {
                        categoryAdapter = CategoryAdapter(mealCategories, this@MainActivity)
                    }

                    categoryAdapter.notifyDataSetChanged()
                })
            }
        }
    }
}