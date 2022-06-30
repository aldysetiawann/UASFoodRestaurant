package com.ubaya.uasfoodrestaurant.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.uasfoodrestaurant.R
import com.ubaya.uasfoodrestaurant.model.Grocery
import com.ubaya.uasfoodrestaurant.model.Recipes
import com.ubaya.uasfoodrestaurant.util.loadImage
import kotlinx.android.synthetic.main.fragment_create.view.*
import kotlinx.android.synthetic.main.grocery_bag_item.view.*

class CreateAdapter(val recipe:ArrayList<Recipes>): RecyclerView.Adapter<CreateAdapter.GroceryViewHolder>() {
    class GroceryViewHolder(val view: View): RecyclerView.ViewHolder(view)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroceryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.grocery_bag_item, parent, false)

        return GroceryViewHolder(v)
    }

    override fun onBindViewHolder(holder: GroceryViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return recipe.size
    }
}