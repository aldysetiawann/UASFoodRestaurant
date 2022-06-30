package com.ubaya.uasfoodrestaurant.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaya.uasfoodrestaurant.R
import com.ubaya.uasfoodrestaurant.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_discover.*

class DiscoverFragment : Fragment() {
    private  lateinit var viewModel: ListViewModel
    private val recipeListAdapter = DiscoverAdapter(arrayListOf())
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_discover, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.refresh(txtCari.text.toString())

        recyclerViewDiscover.layoutManager = LinearLayoutManager(context)
        recyclerViewDiscover.adapter = recipeListAdapter

        refreshLayoutDiscover.setOnRefreshListener {
            recyclerViewDiscover.visibility = View.GONE
            textErrorDiscover.visibility = View.GONE
            progressBarDiscover.visibility = View.VISIBLE
            viewModel.refresh(txtCari.text.toString())
            refreshLayoutDiscover.isRefreshing = false
        }

        btnCari.setOnClickListener {
            recyclerViewDiscover.visibility = View.GONE
            textErrorDiscover.visibility = View.GONE
            progressBarDiscover.visibility = View.VISIBLE
            viewModel.refresh(txtCari.text.toString())
            refreshLayoutDiscover.isRefreshing = false
        }

        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.recipessLD.observe(viewLifecycleOwner, Observer {
            recipeListAdapter.updateRecipeList(it)
        })

        viewModel.loadingErrorLD.observe(viewLifecycleOwner, Observer {
            if(it){
                textErrorDiscover.visibility = View.VISIBLE
            }
            else{
                textErrorDiscover.visibility = View.GONE
            }
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if(it){
                progressBarDiscover.visibility = View.VISIBLE
                recyclerViewDiscover.visibility = View.GONE
            }
            else{
                progressBarDiscover.visibility = View.GONE
                recyclerViewDiscover.visibility = View.VISIBLE
            }
        })
    }
}