package com.ubaya.uasfoodrestaurant.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ubaya.uasfoodrestaurant.util.MIGRATION_1_2
import com.ubaya.uasfoodrestaurant.util.MIGRATION_2_3

@Database(entities = arrayOf(Recipes::class, MyRecipes::class, RecipesDraft::class, Ingredients::class, Preparations::class), version = 3)
abstract class FoodRestaurantDataBase : RoomDatabase(){
    abstract fun recipeDao():FoodRestaurantDao

    companion object {
        @Volatile private var instance:FoodRestaurantDataBase ?= null
        private val LOCK = Any()

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                FoodRestaurantDataBase::class.java,
                "foodrecipedb"
            )
                .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
                .build()

        operator fun invoke(context:Context) {
            if(instance!=null) {
                synchronized(LOCK) {
                    instance ?: buildDatabase(context).also {
                        instance = it
                    }
                }
            }
        }
    }

}