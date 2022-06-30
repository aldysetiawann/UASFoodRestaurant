package com.ubaya.uasfoodrestaurant.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Room
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
class ListViewModel(application: Application):AndroidViewModel(application), CoroutineScope {
    val recipessLD = MutableLiveData<List<Recipes>>()
    val recipesDraftLD = MutableLiveData<List<Recipes>>()
    val ingredentsLD = MutableLiveData<List<Ingredients>>()
    val preparationsLD = MutableLiveData<List<Preparations>>()

    val loadingErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    val loadingErrorDraftLD = MutableLiveData<Boolean>()
    val loadingDraftLD = MutableLiveData<Boolean>()
    private var job = Job()

    private val TAG = "volleyTag"
    private var queue: RequestQueue?= null

    fun refresh() {
        loadingErrorLD.value = false
        loadingLD.value = true

        queue = Volley.newRequestQueue(getApplication())
        var url = "https://ubaya.fun/hybrid/160419075/recipelist3.php"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                val sType = object : TypeToken<List<Recipes>>() { }.type
                val result = Gson().fromJson<List<Recipes>>(response, sType )
//                recipessLD.value = result

                launch {
                    val db = Room.databaseBuilder(getApplication(),
                        FoodRestaurantDataBase::class.java, "foodrecipedb").build()
                    db.recipeDao().deleteRecipe(result)
                    db.recipeDao().deleteAllRecipe()
                    db.recipeDao().insertAllRecipes(result)
                    recipessLD.value = db.recipeDao().selectAllRecipePublic()
                }
                loadingLD.value = false
                Log.d("showvolley", response.toString())

            },
            {
                loadingErrorLD.value = true
                loadingLD.value = false
                Log.d("showvolley", it.toString())
            })

        stringRequest.tag = TAG
        queue?.add(stringRequest)

//        launch {
//            val db = Room.databaseBuilder(getApplication(),
//                FoodRecipeDatabase::class.java, "foodrecipedb").build()
////            db.recipeDao().insertAll()
//
//
//            loadingLD.value = false
//        }
    }

    fun refresh(cari: String): String{
        loadingErrorLD.value = false
        loadingLD.value = true

        queue = Volley.newRequestQueue(getApplication())
        var url = "https://ubaya.fun/hybrid/160419075/discoverrecipe.php"
        var stringRequest = object : StringRequest(
            Request.Method.POST,
            url,
            { response ->
                val sType = object : TypeToken<List<Recipes>>() { }.type
                val result = Gson().fromJson<List<Recipes>>(response, sType )
                recipessLD.value = result

//                launch {
//                    val db = Room.databaseBuilder(getApplication(),
//                        FoodRecipeDatabase::class.java, "foodrecipedb").build()
//                    recipesLD.value = db.recipeDao().selectAllRecipePublic()
//                }

                loadingLD.value = false
                Log.d("showvolley", response.toString())

            },
            {
                loadingErrorLD.value = true
                loadingLD.value = false
                Log.d("showvolley", it.toString())
            }
        ){
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
//                Log.d("AmbilParam", "Dapat")
                params["cari"] = cari
                return params
            }
        }
        stringRequest.tag = TAG
        queue?.add(stringRequest)
        return recipessLD.value.toString()
    }

    fun refreshMyRecipes() {
        loadingErrorLD.value = false
        loadingLD.value = true

//        queue = Volley.newRequestQueue(getApplication())
//        var url = "https://ubaya.fun/hybrid/160419075/recipelist3.php"
//
//        val stringRequest = StringRequest(
//            Request.Method.GET, url,
//            { response ->
//                val sType = object : TypeToken<List<Recipes>>() { }.type
//                val result = Gson().fromJson<List<Recipes>>(response, sType )
//                recipessLD.value = result
//
//                launch {
//                    val db = Room.databaseBuilder(getApplication(),
//                        FoodRecipeDatabase::class.java, "foodrecipedb").build()
//                    db.recipeDao().deleteRecipe(result)
//                    db.recipeDao().insertAllRecipes(result)
//                }
//                loadingLD.value = false
//                Log.d("showvolley", response.toString())
//
//            },
//            {
//                loadingErrorLD.value = true
//                loadingLD.value = false
//                Log.d("showvolley", it.toString())
//            })
//
//        stringRequest.tag = TAG
//        queue?.add(stringRequest)

        launch {
            val db = Room.databaseBuilder(getApplication(),
               FoodRestaurantDataBase::class.java, "foodrecipedb").build()
//            db.recipeDao().insertAll()
            recipessLD.value = db.recipeDao().selectAllRecipePublic()

            loadingLD.value = false
        }
    }

    fun refreshDraft() {
        loadingErrorLD.value = false
        loadingLD.value = true

//        queue = Volley.newRequestQueue(getApplication())
//        var url = "https://ubaya.fun/hybrid/160419075/recipelist3.php"
//
//        val stringRequest = StringRequest(
//            Request.Method.GET, url,
//            { response ->
//                val sType = object : TypeToken<List<Recipes>>() { }.type
//                val result = Gson().fromJson<List<Recipes>>(response, sType )
//                recipessLD.value = result
//
//                launch {
//                    val db = Room.databaseBuilder(getApplication(),
//                        FoodRecipeDatabase::class.java, "foodrecipedb").build()
//                    db.recipeDao().deleteRecipe(result)
//                    db.recipeDao().insertAllRecipes(result)
//                }
//                loadingLD.value = false
//                Log.d("showvolley", response.toString())
//
//            },
//            {
//                loadingErrorLD.value = true
//                loadingLD.value = false
//                Log.d("showvolley", it.toString())
//            })
//
//        stringRequest.tag = TAG
//        queue?.add(stringRequest)

        launch {
            val db = Room.databaseBuilder(getApplication(),
                FoodRestaurantDataBase::class.java, "foodrecipedb").build()
//            db.recipeDao().insertAll()
            recipesDraftLD.value = db.recipeDao().selectAllRecipeUnPublic()

            loadingDraftLD.value = false
        }
    }

    fun refreshIng(id: Int){
        loadingErrorLD.value = false
        loadingLD.value = true
        launch {
            //            val db = Room.databaseBuilder(getApplication(),
            //                TodoDatabase::class.java, "tododb").build()
            val db = buildDB(getApplication())
            ingredentsLD.value = db.recipeDao().selectIngredient(id)
            loadingLD.value = false
        }
//        return ingredentsLD.value.toString()
        /*queue = Volley.newRequestQueue(getApplication())
        var url = "https://ubaya.fun/hybrid/160419075/listingredient.php"

        var stringRequest = object : StringRequest(
            Request.Method.POST,
            url,
            { response ->
                val sType = object : TypeToken<List<Ingredients>>() { }.type
                val result = Gson().fromJson<List<Ingredients>>(response, sType )
                ingredentsLD.value = result


                Log.d("showvolley", response.toString())

            },
            {
                loadingErrorLD.value = true
                loadingLD.value = false
                Log.d("showvolley", it.toString())
            }
        ){
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
//                Log.d("AmbilParam", "Dapat")
                params["id"] = id
                return params
            }
        }
        stringRequest.tag = TAG
        queue?.add(stringRequest)
        return ingredentsLD.value.toString()*/
    }

    fun refreshIng(){
        loadingErrorLD.value = false
        loadingLD.value = true

        queue = Volley.newRequestQueue(getApplication())
        var url = "https://ubaya.fun/hybrid/160419075/listallingredient.php"

        var stringRequest = StringRequest(
            Request.Method.GET,
            url,
            { response ->
                val sType = object : TypeToken<List<Ingredients>>() { }.type
                val result = Gson().fromJson<List<Ingredients>>(response, sType )


                launch {
                    //            val db = Room.databaseBuilder(getApplication(),
                    //                TodoDatabase::class.java, "tododb").build()
                    val db = buildDB(getApplication())
                    db.recipeDao().deleteAllIngredients()
                    db.recipeDao().insertAllIngredients(result)
                }

                loadingLD.value = false
                Log.d("showvolley", response.toString())

            },
            {
                loadingErrorLD.value = true
                loadingLD.value = false
                Log.d("showvolley", it.toString())
            }
        )
        stringRequest.tag = TAG
        queue?.add(stringRequest)
//        return ingredentsLD.value.toString()
    }

    fun clearRecipe(recipes: Recipes) {
        launch {
            val db = Room.databaseBuilder(getApplication(),
               FoodRestaurantDataBase::class.java, "foodrecipedb").build()
            recipessLD.value = db.recipeDao().selectAllRecipePublic()
        }
    }

    fun refreshPrep(id: Int){
        loadingErrorLD.value = false
        loadingLD.value = true
        launch {
            //            val db = Room.databaseBuilder(getApplication(),
            //                TodoDatabase::class.java, "tododb").build()
            val db = buildDB(getApplication())
            preparationsLD.value = db.recipeDao().selectPreparation(id)
            loadingLD.value = false
        }

        /*queue = Volley.newRequestQueue(getApplication())
        var url = "https://ubaya.fun/hybrid/160419075/listpreparation.php"
        var stringRequest = object : StringRequest(
            Request.Method.POST,
            url,
            { response ->
                val sType = object : TypeToken<List<Preparations>>() { }.type
                val result = Gson().fromJson<List<Preparations>>(response, sType )
                preparationsLD.value = result

//                launch {
////            val db = Room.databaseBuilder(getApplication(),
////                TodoDatabase::class.java, "tododb").build()
//                    val db = buildDB(getApplication())
//                    db.recipeDao().insertAllPreparations(result)
//                }

                loadingLD.value = false
                Log.d("showvolley", response.toString())

            },
            {
                loadingErrorLD.value = true
                loadingLD.value = false
                Log.d("showvolley", it.toString())
            }
        ){
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
//                Log.d("AmbilParam", "Dapat")
                params["id"] = id
                return params
            }
        }
        stringRequest.tag = TAG
        queue?.add(stringRequest)*/
//        return preparationsLD.value.toString()
    }

    fun refreshPrep(){
        loadingErrorLD.value = false
        loadingLD.value = true

        queue = Volley.newRequestQueue(getApplication())
        var url = "https://ubaya.fun/hybrid/160419075/listallpreparation.php"
        var stringRequest = StringRequest(
            Request.Method.GET,
            url,
            { response ->
                val sType = object : TypeToken<List<Preparations>>() { }.type
                val result = Gson().fromJson<List<Preparations>>(response, sType )


                launch {
//            val db = Room.databaseBuilder(getApplication(),
//                TodoDatabase::class.java, "tododb").build()
                    val db = buildDB(getApplication())
                    db.recipeDao().deleteAllPreparation()
                    db.recipeDao().insertAllPreparations(result)
                }

                loadingLD.value = false
                Log.d("showvolley", response.toString())

            },
            {
                loadingErrorLD.value = true
                loadingLD.value = false
                Log.d("showvolley", it.toString())
            }
        )

        stringRequest.tag = TAG
        queue?.add(stringRequest)
//        return preparationsLD.value.toString()
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
}