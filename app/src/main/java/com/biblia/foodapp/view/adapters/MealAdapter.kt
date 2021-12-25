package com.biblia.foodapp.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.biblia.foodapp.R
import com.biblia.foodapp.databinding.MealItemBinding
import com.biblia.foodapp.model.entities.MealModel
import com.squareup.picasso.Picasso

class MealAdapter(val meals: MutableList<MealModel>) : RecyclerView.Adapter<MealViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val infalte = LayoutInflater.from(parent.context).inflate(R.layout.meal_item, parent, false)
        return MealViewHolder(infalte)
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        val meal = meals.get(position)
        holder.bind(meal)
    }

    override fun getItemCount(): Int {
        return meals.size
    }
}

class MealViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding: MealItemBinding = MealItemBinding.bind(itemView)

    fun bind(meal: MealModel){
        binding.tvMealName.text = meal.name
        Picasso.get().load(meal.image).into(binding.ivMealImg)
    }
}
