package com.ubaya.uasfoodrestaurant.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ubaya.uasfoodrestaurant.model.*
import com.ubaya.uasfoodrestaurant.util.buildDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DetailRecipeViewModel(application: Application):AndroidViewModel(application), CoroutineScope {
    private val job = Job()
    //    val recipeLD = MutableLiveData<MyRecipes>()
    val recipeLD = MutableLiveData<Recipes>()
    val recipeLastLD = MutableLiveData<Recipes>()
    val ingredentsLD = MutableLiveData<Ingredients>()
    val preparationLD = MutableLiveData<Preparations>()

    private val TAG = "volleyTag"
    private var queue: RequestQueue?= null

    fun fetch(uuid:Int){
        launch {
            val db = buildDB(getApplication())
            recipeLD.value = db.recipeDao().selectRecipe(uuid)
        }
    }

    fun update(name:String, category:String, poster:String, uuid:Int){
        launch {
            val db = buildDB(getApplication())
            db.recipeDao().updateMyRecipe(name, category, poster, uuid)
        }
    }

    fun updateRecipe(name:String, category:String, poster:String, id: Int){
        queue = Volley.newRequestQueue(getApplication())
        var url = "https://ubaya.fun/hybrid/160419072/updaterecipe.php"

        val stringRequest = object : StringRequest(
            Request.Method.POST, url,
            { response ->
                val sType = object : TypeToken<Recipes>() { }.type
                val result = Gson().fromJson<Recipes>(response, sType )
//                preparationLD.value = result

                launch {
                    val db = buildDB(getApplication())
                    db.recipeDao().updateRecipe(name, category, poster, id)
                }
//                loadingLD.value = false
                Log.d("showvolley", response.toString())

            },
            {
//                loadingErrorLD.value = true
//                loadingLD.value = false
                Log.d("showvolley", it.toString())
            }
        ){
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
//                Log.d("AmbilParam", "Dapat")
                params["recipe_id"] = id.toString()
                params["name"] = name.toString()
                params["category"] = category.toString()
                params["poster"] = poster.toString()
                return params
            }
        }

        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }

    fun addRecipe(recipe:Recipes) {
        queue = Volley.newRequestQueue(getApplication())
        var url = "https://ubaya.fun/hybrid/160419072/addrecipe.php"

        val stringRequest = object : StringRequest(
            Request.Method.POST, url,
            { response ->
                val sType = object : TypeToken<Recipes>() { }.type
                val result = Gson().fromJson<Recipes>(response, sType )
                recipeLD.value = result

                launch {
                    val db = buildDB(getApplication())
                    db.recipeDao().insertAll(recipe)
                }
                Log.d("showvolley", response.toString())

            },
            {
                Log.d("showvolley", it.toString())
            }
        ){
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
//                Log.d("AmbilParam", "Dapat")
                params["name"] = recipe.name.toString()
                params["category"] = recipe.category.toString()
                params["likes"] = recipe.likes.toString()
                params["poster"] = recipe.poster.toString()
                params["public"] = recipe.public_stat.toString()
                return params
            }
        }

        stringRequest.tag = TAG
        queue?.add(stringRequest)

    }

    fun addIngredients(ingredients: List<Ingredients>) {
        launch {
            val db = buildDB(getApplication())
            db.recipeDao().insertAllIngredients(ingredients)
        }
    }

    fun addIngredient(ingredients: Ingredients) {
        queue = Volley.newRequestQueue(getApplication())
        var url = "https://ubaya.fun/hybrid/160419072/addingredient.php"

        val stringRequest = object : StringRequest(
            Request.Method.POST, url,
            { response ->
                val sType = object : TypeToken<Ingredients>() { }.type
                val result = Gson().fromJson<Ingredients>(response, sType )
                ingredentsLD.value = result

                Log.d("showvolley", response.toString())

            },
            {
                Log.d("showvolley", it.toString())
            }
        ){
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
//                Log.d("AmbilParam", "Dapat")
                params["recipe_id"] = ingredients.recipe_id_ing.toString()
                params["item"] = ingredients.item.toString()
                params["amount"] = ingredients.amount.toString()
                return params
            }
        }

        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }

    fun addPreparation(preparations: Preparations) {
        queue = Volley.newRequestQueue(getApplication())
        var url = "https://ubaya.fun/hybrid/160419072/addpreparation.php"

        val stringRequest = object : StringRequest(
            Request.Method.POST, url,
            { response ->
                val sType = object : TypeToken<Preparations>() { }.type
                val result = Gson().fromJson<Preparations>(response, sType )
                preparationLD.value = result

                Log.d("showvolley", response.toString())

            },
            {
                Log.d("showvolley", it.toString())
            }
        ){
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
//                Log.d("AmbilParam", "Dapat")
                params["recipe_id"] = preparations.recipe_id_prep.toString()
                params["step"] = preparations.step.toString()
                params["description"] = preparations.description.toString()
                return params
            }
        }

        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }

    fun setPublicRecipe(id: Int) {
        queue = Volley.newRequestQueue(getApplication())
        var url = "https://ubaya.fun/hybrid/160419072/publicrecipe.php"

        val stringRequest = object : StringRequest(
            Request.Method.POST, url,
            { response ->
                val sType = object : TypeToken<Preparations>() { }.type
                val result = Gson().fromJson<Preparations>(response, sType )
//                preparationLD.value = result

                launch {
                    val db = buildDB(getApplication())
                    db.recipeDao().setPublicRecipe(id)
                }
//                loadingLD.value = false
                Log.d("showvolley", response.toString())

            },
            {
                Log.d("showvolley", it.toString())
            }
        ){
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
//                Log.d("AmbilParam", "Dapat")
                params["recipe_id"] = id.toString()
                return params
            }
        }

        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }

    fun deleteAllIngredients() {
        launch {
            val db = buildDB(getApplication())
            db.recipeDao().deleteAllIngredients()
        }
    }

    fun deleteIngredient(id: Int) {
        launch {
            val db = buildDB(getApplication())
            db.recipeDao().deleteIngredient(id)
        }
    }

    fun deletePreparation(id: Int) {
        launch {
            val db = buildDB(getApplication())
            db.recipeDao().deletePreparation(id)
        }
    }

    fun deleteAllMyRecipe(id: Int) {
        launch {
            val db = buildDB(getApplication())
            db.recipeDao().deleteRecipe(id)
        }
    }

    fun selectLastRecipe() {
        launch {
            val db = buildDB(getApplication())
            recipeLastLD.value = db.recipeDao().selectLastRecipe()
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

}