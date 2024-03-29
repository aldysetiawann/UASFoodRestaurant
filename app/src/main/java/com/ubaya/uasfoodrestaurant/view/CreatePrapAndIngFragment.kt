package com.ubaya.uasfoodrestaurant.view

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.Person.fromBundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.ubaya.uasfoodrestaurant.R
import com.ubaya.uasfoodrestaurant.databinding.FragmentCreatePrapAndIngBinding
import com.ubaya.uasfoodrestaurant.model.Ingredients
import com.ubaya.uasfoodrestaurant.model.Preparations
import com.ubaya.uasfoodrestaurant.util.Notification
import com.ubaya.uasfoodrestaurant.viewmodel.DetailRecipeViewModel

class CreatePrepAndIngFragment : Fragment(), ButtonAddPIClickListener {
    private lateinit var viewModel: DetailRecipeViewModel
    private lateinit var dataBinding: FragmentCreatePrapAndIngBinding

    var recipeId = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_create_prap_and_ing, container, false)
        return dataBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailRecipeViewModel::class.java)
        dataBinding.ingredients = Ingredients(0,"","")
        dataBinding.preparations = Preparations(0,0,"")

        dataBinding.listener = this

        if(arguments!=null){
//            recipeId = CreatePrepAndIngFragment.fromBundle/
        }
    }

    override fun onButtonAddPIClick(v: View) {
        var result = dataBinding.ingredient!!.split(";").map { it.trim() }
        var result2 = dataBinding.amount!!.split(";").map { it.trim() }
        var result3 = dataBinding.preparation!!.split(";").map { it.trim() }
//        var arrayIng = arrayListOf<Ingredients>()
        AlertDialog.Builder(context).apply {
            setMessage("You want to set public this recipe?")
            setPositiveButton("Yes") { _, _ ->
                if(result.size != result2.size){
                    Toast.makeText(
                        context,
                        "The ingredient and ingredient amount need to be the same amount",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else {
                    Notification(v.context).createNotificationPublic("Recipe Uloaded","A new recipe have been public")
                    for ((index, value) in result.withIndex()) {
                        viewModel.addIngredient(Ingredients(recipeId, value, result2[index]))
                    }

                    for ((index, value) in result3.withIndex()) {
//                        viewModel.addIngredient(Ingredients(recipeId, value, result2[index]))
                        viewModel.addPreparation(Preparations(recipeId, index+1, value))
                    }
                    viewModel.setPublicRecipe(recipeId)
                }
            }
            setNegativeButton("Cancel", null)

            create().show()
        }
    }
}