package com.ubaya.uasfoodrestaurant.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaya.uasfoodrestaurant.R
import com.ubaya.uasfoodrestaurant.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_ingredients_list.*

class IngredientListFragment : Fragment() {
    private  lateinit var viewModel:ListViewModel
    private val ingredientListAdapter = IngredientListAdapter(arrayListOf())
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ingredients_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        var id = 1

        if(arguments!=null){
//            var id = IngredientListFragmentArgs.fromBundle(requireArguments()).id
        }
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.refreshIng(IngredientsListFragmentArgs.fromBundle(requireArguments()).id)

        Log.d("SHOW ID", IngredientsListFragmentArgs.fromBundle(requireArguments()).id.toString())

        recyclerView2.layoutManager = LinearLayoutManager(context)
        recyclerView2.adapter = ingredientListAdapter

        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.ingredentsLD.observe(viewLifecycleOwner, Observer {
            ingredientListAdapter.updateIngredientList(it)
        })

        viewModel.loadingErrorLD.observe(viewLifecycleOwner, Observer {
            if(it){
                txtError3.visibility = View.VISIBLE
            }
            else{
                txtError3.visibility = View.GONE
            }
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if(it){
                progressBar3.visibility = View.VISIBLE
                recyclerView2.visibility = View.GONE
            }
            else{
                progressBar3.visibility = View.GONE
                recyclerView2.visibility = View.VISIBLE
            }
        })
    }
}