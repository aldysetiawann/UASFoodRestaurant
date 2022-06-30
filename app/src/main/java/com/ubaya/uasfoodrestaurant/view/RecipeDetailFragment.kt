package com.ubaya.uasfoodrestaurant.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.ubaya.uasfoodrestaurant.R
import com.ubaya.uasfoodrestaurant.databinding.FragmentRecipeDetailBinding
import com.ubaya.uasfoodrestaurant.viewmodel.DetailRecipeViewModel
import kotlinx.android.synthetic.main.fragment_recipe_detail.*

class RecipeDetailFragment : Fragment() {
    private lateinit var viewModel:DetailRecipeViewModel
    private lateinit var dataBinding:FragmentRecipeDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate<FragmentRecipeDetailBinding>(inflater, R.layout.fragment_recipe_detail, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(DetailRecipeViewModel::class.java)

        val id = RecipeDetailFragmentArgs.fromBundle((requireArguments())).id
        viewModel.fetch(id)

        observeViewModel()

        btnIngredient.setOnClickListener {
            val action = RecipeDetailFragmentDirections.actionIngredientList(RecipeDetailFragmentArgs.fromBundle(requireArguments()).id)
            Navigation.findNavController(it).navigate(action)
        }

        btnPreparation.setOnClickListener {
            val action = RecipeDetailFragmentDirections.actionPreparationList(RecipeDetailFragmentArgs.fromBundle(requireArguments()).id)
            Navigation.findNavController(it).navigate(action)
        }
    }

    fun observeViewModel() {
        viewModel.recipeLD.observe(viewLifecycleOwner, Observer {
            dataBinding.recipe = it
            dataBinding.imageUrl = it.poster.toString()
//            imageView2.loadImage(it[0].poster.toString(), progressBar2)
        })
    }
}