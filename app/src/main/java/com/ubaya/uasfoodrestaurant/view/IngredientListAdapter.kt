package com.ubaya.uasfoodrestaurant.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.uasfoodrestaurant.R
import com.ubaya.uasfoodrestaurant.databinding.IngredientsListItemBinding
import com.ubaya.uasfoodrestaurant.databinding.PreparationListItemBinding
import com.ubaya.uasfoodrestaurant.model.Ingredients

class IngredientListAdapter(val ingredientList:ArrayList<Ingredients>): RecyclerView.Adapter<IngredientListAdapter.IngredientViewHolder>() {
    class IngredientViewHolder(var view: IngredientsListItemBinding) : RecyclerView.ViewHolder(view.root)

    fun updateIngredientList(newStudentList:List<Ingredients>){
        ingredientList.clear()
        ingredientList.addAll(newStudentList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = DataBindingUtil.inflate<IngredientsListItemBinding>(inflater,
            R.layout.ingredients_list_item, parent, false)

        return IngredientViewHolder(v)
    }

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
        with(holder.view){
            ingredients = ingredientList[position]
        }
    }

    override fun getItemCount(): Int {
        return ingredientList.size
    }
}