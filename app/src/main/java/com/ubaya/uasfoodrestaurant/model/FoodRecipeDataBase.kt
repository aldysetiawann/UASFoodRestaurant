package com.ubaya.uasfoodrestaurant.model

import android.content.Context
import androidx.room.*
import kotlinx.android.parcel.Parcelize
import.com.ubaya.uasfoodrestaurant.util.MIGRATION_1_2
import.com.ubaya.uasfoodrestaurant.util.MIGRATION_2_3

@Database(entities = arrayOf(Recipes::class, MyRecipes::class, RecipesDraft::class, Ingredients::class, Preparations::class), version = 3)
abstract class FoodRecipeDatabase: RoomDatabase() {
    abstract fun recipeDao():FoodRecipeDao

    companion object {
        @Volatile private var instance:FoodRecipeDatabase ?= null
        private val LOCK = Any()

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                FoodRecipeDatabase::class.java,
                "foodrecipedb"
            )
                .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
                .build()

        operator fun invoke(context: Context) {
            if(instance!=null) {
                synchronized(LOCK) {
                    instance ?: buildDatabase(context).also {
                        instance = it
                    }
                }
            }
        }
//        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
//            instance ?: buildDatabase(context).also {
//                instance = it
//            }
//        }
    }
}


