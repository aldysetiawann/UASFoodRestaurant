package com.ubaya.uasfoodrestaurant.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ubaya.uasfoodrestaurant.R
import com.ubaya.uasfoodrestaurant.databinding.FragmentOptionBinding
import com.ubaya.uasfoodrestaurant.model.Recipes
import com.ubaya.uasfoodrestaurant.viewmodel.DetailRecipeViewModel
import kotlinx.android.synthetic.main.fragment_option.*

class OptionFragment : BottomSheetDialogFragment(), ButtonShareClickListener {
    private lateinit var viewModel: DetailRecipeViewModel
    private lateinit var dataBinding: FragmentOptionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_option, container, false)
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_option, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(DetailRecipeViewModel::class.java)

        val id = OptionFragmentArgs.fromBundle(requireArguments()).id
        dataBinding.shareListener = this

        viewModel.fetch(id)

        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.recipeLD.observe(viewLifecycleOwner, Observer {
            dataBinding.recipe = it
            dataBinding.imageUrl = it.poster

        })
    }

    override fun onButtonShareClick(v: View) {
        val sendIntent = Intent.createChooser(Intent().apply {
            action = Intent.ACTION_SEND
            var text = "Recipe ${dataBinding.recipe!!.name} is a ${dataBinding.recipe!!.category}" +
                    ", and there are ${dataBinding.recipe!!.likes.toString()} people that like this recipe"
            var poster = dataBinding.recipe!!.poster
            putExtra(Intent.EXTRA_TEXT, text)
            type = "text/plain"

        },null)
        // Buat share intent
        val shareIntent = Intent.createChooser(sendIntent, "Kirim pesan menggunakan")

        startActivity(shareIntent)
    }
}