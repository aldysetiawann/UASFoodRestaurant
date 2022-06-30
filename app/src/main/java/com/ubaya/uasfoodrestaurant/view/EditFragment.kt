package com.ubaya.uasfoodrestaurant.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.ubaya.uasfoodrestaurant.R
import com.ubaya.uasfoodrestaurant.databinding.FragmentCreateBinding
import com.ubaya.uasfoodrestaurant.databinding.FragmentEditBinding
import com.ubaya.uasfoodrestaurant.model.Recipes
import com.ubaya.uasfoodrestaurant.viewmodel.DetailRecipeViewModel
import kotlinx.android.synthetic.main.fragment_create.*

class EditFragment : Fragment(),ButtonSaveChangeClickListener, RadioClickListener {
    private lateinit var viewModel:DetailRecipeViewModel
    private lateinit var dataBinding:FragmentEditBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_create, container, false)
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_edit, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailRecipeViewModel::class.java)


        val id = EditFragmentArgs.fromBundle(requireArguments()).id
        viewModel.fetch(id)

        dataBinding.radiolistener = this
        dataBinding.listener = this
        dataBinding.breakfast = "Breakfast"
        dataBinding.dessert = "Dessert"
        dataBinding.dinner = "Dinner"
        dataBinding.drink = "Drink"
        observerViewModel()
    }

    fun observerViewModel() {
        viewModel.recipeLD.observe(viewLifecycleOwner, Observer {
            dataBinding.recipe = it

            if(dataBinding.recipe!!.category == "Breakfast"){
                dataBinding.category = 4
            } else if(dataBinding.recipe!!.category == "Dessert"){
                dataBinding.category = 3
            } else if(dataBinding.recipe!!.category == "Dinner"){
                dataBinding.category = 2
            }else if(dataBinding.recipe!!.category == "Drink"){
                dataBinding.category = 1
            }
        })
    }

    override fun onRadioClick(v: View, category: String, obj: Recipes) {
        obj.category = v.tag.toString()
    }

    override fun onButtonSaveChangeClick(v: View, obj: Recipes) {
        viewModel.updateRecipe(obj.name.toString(), obj.category.toString(), obj.poster.toString(), obj.recipe_id)
        Toast.makeText(v.context, "Recipe updated", Toast.LENGTH_SHORT).show()
        val action = EditFragmentDirections.actionGoBackToMyRecipesFragment()
        Navigation.findNavController(v).navigate(action)
    }


}