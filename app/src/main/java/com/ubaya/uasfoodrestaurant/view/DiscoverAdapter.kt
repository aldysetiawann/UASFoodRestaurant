package com.ubaya.uasfoodrestaurant.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.uasfoodrestaurant.R
import com.ubaya.uasfoodrestaurant.databinding.MyrecipeListItemBinding
import com.ubaya.uasfoodrestaurant.databinding.RecipeListsearchItemBinding
import com.ubaya.uasfoodrestaurant.model.Recipe
import com.ubaya.uasfoodrestaurant.model.Recipes
import com.ubaya.uasfoodrestaurant.util.loadImage
import kotlinx.android.synthetic.main.recipe_list_item.view.*
import kotlinx.android.synthetic.main.recipe_listsearch_item.view.*

class DiscoverAdapter(val recipeList:ArrayList<Recipes>): RecyclerView.Adapter<DiscoverAdapter.DiscoverViewHolder>(), ImageViewClickListener {
    class DiscoverViewHolder(val view: RecipeListsearchItemBinding): RecyclerView.ViewHolder(view.root)

    fun updateRecipeList(newRecipeList:List<Recipes>){
        recipeList.clear()
        recipeList.addAll(newRecipeList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiscoverViewHolder {
        val inflater = LayoutInflater.from(parent.context)
//        val v = inflater.inflate(R.layout.recipe_listsearch_item, parent, false)
        val v = DataBindingUtil.inflate<RecipeListsearchItemBinding>(inflater,
            R.layout.recipe_listsearch_item, parent, false)
        return DiscoverViewHolder(v)
    }

    override fun onBindViewHolder(holder: DiscoverViewHolder, position: Int) {
        with(holder.view){
            recipe =recipeList[position]
            imageViewListener = this@DiscoverAdapter
            imageUrl = recipeList[position].poster

        }
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }

    override fun onImageViewClick(v: View) {
        val action = DiscoverFragmentDirections.actionToRecipeDetail(v.tag.toString().toInt()
        )
        Navigation.findNavController(v).navigate(action)
    }
}