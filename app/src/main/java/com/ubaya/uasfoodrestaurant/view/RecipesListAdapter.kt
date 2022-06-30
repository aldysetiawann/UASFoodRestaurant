package com.ubaya.uasfoodrestaurant.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.uasfoodrestaurant.R
import com.ubaya.uasfoodrestaurant.databinding.FragmentCreateBinding
import com.ubaya.uasfoodrestaurant.model.Recipe
import com.ubaya.uasfoodrestaurant.model.Recipes
import com.ubaya.uasfoodrestaurant.util.loadImage
import kotlinx.android.synthetic.main.recipe_list_item.view.*

class RecipesListAdapter {
    class RecipeListAdapter(val recipeList:ArrayList<Recipes>):RecyclerView.Adapter<RecipeListAdapter.RecipeViewHolder>(), RecipeDetailClickListener, RecipeShareClickListener
    {
        class RecipeViewHolder(val view: RecipeListItemBinding):RecyclerView.ViewHolder(view.root)

        fun updateRecipeList(newRecipeList:List<Recipes>){
            recipeList.clear()
            recipeList.addAll(newRecipeList)
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
            val inflater = LayoutInflater.from(parent.context)
//        val v = inflater.inflate(R.layout.recipe_list_item, parent, false)
            val v = DataBindingUtil.inflate<RecipeListItemBinding>(inflater,
                R.layout.recipe_list_item, parent, false)
            return RecipeViewHolder(v)
        }

        override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
            with(holder.view){
                recipe =recipeList[position]
                detailsListener = this@RecipeListAdapter
                shareListener = this@RecipeListAdapter
                imageUrl = recipeList[position].poster
                val id = recipeList[position].recipe_id
            }
        }

        override fun getItemCount(): Int {
            return recipeList.size
        }

        override fun onRecipeDetailClick(v: View) {
            val action = RecipeListFragmentDirections.actionRecipeDetail(v.tag.toString().toInt()
            )
            Navigation.findNavController(v).navigate(action)
        }

        override fun onRecipeShareClick(v: View) {
            val action = RecipeListFragmentDirections.actionOptionFragment(v.tag.toString().toInt())
            Navigation.findNavController(v).navigate(action)
        }
    }
}