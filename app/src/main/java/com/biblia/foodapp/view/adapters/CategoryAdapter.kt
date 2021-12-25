package com.biblia.foodapp.view.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.biblia.foodapp.R
import com.biblia.foodapp.databinding.CategoryItemBinding
import com.biblia.foodapp.model.entities.CategoryModel
import com.biblia.foodapp.view.MealActivity
import com.squareup.picasso.Picasso

class CategoryAdapter(private val categoryList: MutableList<CategoryModel>, private val context: Context) : RecyclerView.Adapter<CategoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.category_item,parent,false)
        return CategoryViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category: CategoryModel = categoryList.get(position)
        holder.bind(category)
        holder.itemView.setOnClickListener {
            val intent = Intent(context, MealActivity::class.java)
            intent.putExtra("category", category.name)
            ContextCompat.startActivity(context, intent, null)
        }
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }
}

class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val binding = CategoryItemBinding.bind(itemView)

    fun bind(category: CategoryModel){
        binding.tvCategiryName.text = category.name
        binding.tvCategiryDescription.text = category.description

        Picasso.get().load(category.image).into(binding.ivCategoryImg)
    }
}
