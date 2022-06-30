package com.ubaya.uasfoodrestaurant.model

import androidx.room.*

@Dao
interface FoodRestaurantDao {
    @Insert
    suspend fun insertAll(recipes: Recipes)

    @Insert
    suspend fun insertAllRecipes(recipes:List<Recipes>)

    @Query("SELECT * FROM recipes WHERE public_stat = 1")
    suspend fun selectAllRecipePublic():List<Recipes>

    @Query("SELECT * FROM recipes WHERE public_stat = 0")
    suspend fun selectAllRecipeUnPublic():List<Recipes>

    @Query("SELECT * FROM recipes WHERE recipe_id = :id")
    suspend fun selectRecipe(id:Int):Recipes

    @Query("SELECT * FROM recipes ORDER BY recipe_id DESC LIMIT 1 ")
    suspend fun selectLastRecipe():Recipes

    @Query("UPDATE recipes SET public_stat=1 WHERE recipe_id=:uuid")
    suspend fun setPublicRecipe(uuid: Int)

    @Query("UPDATE recipes SET name=:name, category=:category, poster=:poster WHERE recipe_id=:uuid")
    suspend fun updateRecipe(name:String, category: String, poster: String, uuid: Int)

    @Delete
    suspend fun deleteRecipe(recipes: Recipes)

    @Query("DELETE FROM recipes WHERE recipe_id = :id")
    suspend fun deleteRecipe(id: Int)

    @Query("DELETE FROM recipes")
    suspend fun deleteAllRecipe()

    @Delete
    suspend fun deleteRecipe(recipes:List<Recipes>)

    //

    @Insert
    suspend fun insertAllMyRecipe(myRecipes: MyRecipes)

    @Insert
    suspend fun insertAllMyRecipes(myRecipes:List<MyRecipes>)

    @Query("SELECT * FROM myrecipes")
    suspend fun selectAllMyRecipes():List<MyRecipes>

    @Query("SELECT * FROM myrecipes WHERE recipe_id = :id")
    suspend fun selectMyRecipes(id:Int):MyRecipes

    @Query("UPDATE myrecipes SET name=:name, category=:category, poster=:poster WHERE recipe_id=:uuid")
    suspend fun updateMyRecipe(name:String, category:String, poster:String, uuid: Int)

    @Query("DELETE FROM myrecipes")
    suspend fun deleteAllMyRecipe()

    @Delete
    suspend fun deleteMyRecipes(myRecipes: MyRecipes)

    @Delete
    suspend fun deleteMyRecipes(myRecipes: List<MyRecipes>)

    //

    @Insert
    suspend fun insertAllRecipeDraft(recipesDraft: RecipesDraft)

    @Insert
    suspend fun insertAllRecipesDraft(recipesDraft: List<RecipesDraft>)

    @Query("SELECT * FROM recipesdraft")
    suspend fun selectAllRecipesDraft():List<RecipesDraft>

    @Query("SELECT * FROM recipesdraft WHERE recipe_id = :id")
    suspend fun selectRecipesDraft(id:Int):RecipesDraft

    @Query("UPDATE recipesdraft SET name=:name, category=:category, poster=:poster WHERE recipe_id=:uuid")
    suspend fun updateRecipeDraft(name:String, category:String, poster:String, uuid: Int)

    @Delete
    suspend fun deleteRecipesDraft(recipesDraft: RecipesDraft)

    @Delete
    suspend fun deleteRecipesDraft(recipesDraft: List<RecipesDraft>)

    //

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllPreparation(preparations: Preparations)

    @Insert
    suspend fun insertAllPreparations(preparations: List<Preparations>)

    @Query("SELECT * FROM preparations")
    suspend fun selectAllPreparation():List<Preparations>

    @Query("SELECT * FROM preparations WHERE recipe_id_prep = :id ORDER BY step")
    suspend fun selectPreparation(id:Int):List<Preparations>

    @Query("DELETE FROM preparations WHERE preparation_id = :id")
    suspend fun deletePreparation(id: Int)

    @Query("DELETE FROM preparations")
    suspend fun deleteAllPreparation()

    //

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllIngredient(ingredients:Ingredients)

    @Insert
    suspend fun insertAllIngredients(ingredients:List<Ingredients>)

    @Query("SELECT * FROM ingredients")
    suspend fun selectAllIngredient():List<Ingredients>

    @Query("UPDATE ingredients SET item=:category, amount=:poster WHERE ingredient_id=:uuid")
    suspend fun updateIngredient(category:String, poster:String, uuid: Int)

    @Query("SELECT * FROM ingredients WHERE recipe_id_ing = :id")
    suspend fun selectIngredient(id:Int):List<Ingredients>

    @Query("DELETE FROM ingredients")
    suspend fun deleteAllIngredients()

    @Query("DELETE FROM ingredients WHERE ingredient_id = :id")
    suspend fun deleteIngredient(id: Int)
}