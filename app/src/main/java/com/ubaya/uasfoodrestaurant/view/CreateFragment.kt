package com.ubaya.uasfoodrestaurant.view

import android.app.AlertDialog
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
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaya.uasfoodrestaurant.R
import com.ubaya.uasfoodrestaurant.databinding.FragmentCreateBinding
import com.ubaya.uasfoodrestaurant.model.Ingredients
import com.ubaya.uasfoodrestaurant.model.MyRecipes
import com.ubaya.uasfoodrestaurant.model.Recipes
import com.ubaya.uasfoodrestaurant.util.Notification
import com.ubaya.uasfoodrestaurant.viewmodel.DetailRecipeViewModel
import kotlinx.android.synthetic.main.fragment_create.*

class CreateFragment : Fragment(), ButtonAddClickListener, RadioClickListener {
    private lateinit var viewModel: DetailRecipeViewModel
    private lateinit var dataBinding:FragmentCreateBinding

    var text = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_create, container, false)
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_create, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(DetailRecipeViewModel::class.java)
//        dataBinding.myrecipe = MyRecipes("",4,0,"")
        dataBinding.recipe = Recipes("","",0,"",0)
        dataBinding.listener = this
        dataBinding.radiolistener = this
        dataBinding.breakfast = "Breakfast"
        dataBinding.dessert = "Dessert"
        dataBinding.dinner = "Dinner"
        dataBinding.drink = "Drink"
//        dataBinding.ingredient = this.toString()
//        dataBinding.ingredients = Ingredients(1,"","")
//        text = dataBinding.ingredients!!.item.toString()
//        observeViewModel()
    }

    override fun onButtonAddClick(v: View) {

//        dataBinding.ingredient = this.
//        observeViewModel()
//        val test = "1.Pertama;2.Kedua;3.Ketiga;4.Keempat"
//        var result = dataBinding.ingredient!!.split(";").map { it.trim() }
//        Toast.makeText(v.context, result.toString(), Toast.LENGTH_SHORT).show()
        AlertDialog.Builder(context).apply {
            setMessage("You want to add this recipe?")
            setPositiveButton("Yes") { _, _ ->
                Notification(v.context).createNotificationDraft("Recipe Created","A new recipe have been saved into draft")
                viewModel.addRecipe(dataBinding.recipe!!)
//                viewModel.selectLastRecipe()

//                viewModel.deleteAllMyRecipe(4)
//                viewModel.deleteAllMyRecipe(10)
                Toast.makeText(
                    context,
                    dataBinding.recipe!!.toString(),
                    Toast.LENGTH_SHORT
                ).show()
                val action = CreateFragmentDirections.actionBackToListFragment()
                Navigation.findNavController(v).navigate(action)
            }
            setNegativeButton("Cancel", null)

            create().show()
        }
    }

    override fun onRadioClick(v: View, category: String, obj: Recipes) {
        obj.category = v.tag.toString()
    }

//    override fun onTextChanged(s: CharSequence) {
//        text = s.toString()
//    }
}