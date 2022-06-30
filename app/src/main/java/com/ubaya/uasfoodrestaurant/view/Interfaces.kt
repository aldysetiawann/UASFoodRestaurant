package com.ubaya.uasfoodrestaurant.view

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
//import androidx.databinding.BindingAdapter
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.ubaya.uasfoodrestaurant.R
import com.ubaya.uasfoodrestaurant.model.MyRecipes
import com.ubaya.uasfoodrestaurant.model.Recipes
import com.ubaya.uasfoodrestaurant.util.loadImage
import java.lang.Exception

interface RecipeDetailClickListener {
    fun onRecipeDetailClick(v: View)
}

interface ImageViewClickListener {
    fun onImageViewClick(v: View)
}

interface ButtonEditClickListener {
    fun onButtonEditClick(v: View)
}

interface ButtonPIClickListener {
    fun onButtonPIClick(v: View)
}

interface ButtonAddPIClickListener {
    fun onButtonAddPIClick(v: View)
}

interface RecipeShareClickListener {
    fun onRecipeShareClick(v: View)
}

interface ButtonShareClickListener {
    fun onButtonShareClick(v: View)
}

interface ButtonAddClickListener {
    fun onButtonAddClick(v: View)
}

interface ButtonSaveChangeClickListener {
    fun onButtonSaveChangeClick(v: View, obj: Recipes)
}

interface RadioClickListener {
    fun onRadioClick(v: View, category:String, obj:Recipes)
}

interface RadioCheckListener {
    fun onRadioCheck(v: View, category:String, obj:MyRecipes)
}

interface TextChangedListener {
    fun onTextChanged(s: CharSequence)
}