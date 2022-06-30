package com.ubaya.uasfoodrestaurant.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.uasfoodrestaurant.R
import com.ubaya.uasfoodrestaurant.databinding.MyrecipeListItemBinding
import com.ubaya.uasfoodrestaurant.databinding.RecipeListItemBinding
import com.ubaya.uasfoodrestaurant.model.MyRecipes
import com.ubaya.uasfoodrestaurant.model.Recipe
import com.ubaya.uasfoodrestaurant.model.Recipes
import com.ubaya.uasfoodrestaurant.util.loadImage
import kotlinx.android.synthetic.main.fragment_my_recipes.view.*
import kotlinx.android.synthetic.main.myrecipe_list_item.view.*
import kotlinx.android.synthetic.main.recipe_list_item.view.*

class MyRecipesAdapter(val recipeList:ArrayList<Recipes>): RecyclerView.Adapter<MyRecipesAdapter.MyRecipeViewHolder>(), ButtonEditClickListener, ButtonPIClickListener {
    class MyRecipeViewHolder(val view: MyrecipeListItemBinding): RecyclerView.ViewHolder(view.root)

    fun updateRecipeList(newRecipeList:List<Recipes>){
        recipeList.clear()
        recipeList.addAll(newRecipeList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyRecipeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
//        val v = inflater.inflate(R.layout.myrecipe_list_item, parent, false)
        val v = DataBindingUtil.inflate<MyrecipeListItemBinding>(inflater,
            R.layout.myrecipe_list_item, parent, false)
        return MyRecipeViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyRecipeViewHolder, position: Int) {
        with(holder.view){
            recipe =recipeList[position]
            editListener = this@MyRecipesAdapter
            addPIListener = this@MyRecipesAdapter
            imageUrl = recipeList[position].poster
            if(recipeList[position].public_stat == 0){
                btnAddIngPrep.visibility = View.VISIBLE
                btnEdit.visibility = View.GONE
            }
        }
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }

    override fun onButtonEditClick(v: View) {
        val action = MyRecipesFragmentDirections.actionEditFragment(v.tag.toString().toInt()
        )
        Navigation.findNavController(v).navigate(action)
    }

    override fun onButtonPIClick(v: View) {
        val action = MyRecipesFragmentDirections.actionCreatePIFragment(v.tag.toString().toInt()
        )
        Navigation.findNavController(v).navigate(action)
    }
}